Spark Notes
Internally, Spark uses five main properties to represent an RDD. The three required properties are the list of partition objects that make up the RDD, a function for com‐ puting an iterator of each partition, and a list of dependencies on other RDDs. Optionally, RDDs also include a partitioner (for RDDs of rows of key/value pairs rep‐ resented as Scala tuples) and a list of preferred locations (for the HDFS file). 

a job is defined by calling an action. - transformations till 1 action

In other words, a new stage begins whenever network communication between workers is required; for instance, in a shuffle . things till we need the driver again

Because the stage boundaries require communication with the driver, the stages asso‐ ciated with one job generally have to be executed in sequence rather than in parallel. 

Dataframe and Dataset - RDD with Schema Info. Dataset API is strongly typed. They have the magical Tungsten storage and Catalyst optimiser underneath thanks to Schema Inference. Both are Row Objects, no compile-time check

enableHiveSupport(), which will give you access to Hive UDFs and does not require a Hive installation—but does require certain extra JARs . Does eager checks also

SparkSession combines sqlContect n hiveContext. hiveContext doesn’t need hive again. HiveSontext is better since it has a better parser n UDF support. But prefer SparkSession 

sbt-spark-package plug-in can simplify managing Spark dependencies. 

No issues with spark dataframe/dataset groupby

Dataframe and Dataset are not just RDD of Row objects. DataFrames and Datasets have a specialized representation and columnar cache format. Dataframe also have a logical plan that has more info than RDD dependency

Tungsten includes specialized in-memory data structures tuned for the types of operations required by Spark, improved code generation, and a specialized wire protocol. 
Even without Tungsten, Spark SQL uses a columnar storage format with Kryo serialization to minimize storage cost. 

pache Parquet’s popularity comes from a number of features, including the ability to easily split across multiple files, compression, nested types, 

Datasets can be easily converted to/from DataFrames and RDDs, but in the initial version they do not directly extend either. Converting to/from RDDs involves encod‐ ing/decoding the data into a different form. Converting to/from DataFrames is almost “free” in that the underlying data does not need to be changed; only extra compile-time type information is added/removed. 

The logical plan you construct through transformations on DataFrames/Datasets (or SQL queries) starts out as an unresolved logical plan. Spark applies a number of simplifications directly on the logical plan, producing an optimized logical plan. These simplifications can be written using pattern matching on the tree, such as the rule for simplifying additions between two literals. 

Spark may also apply code generation for the components. Code generation is done using Janino to compile Java code 

While the Catalyst optimizer is quite powerful, one of the cases where it currently runs into challenges is with very large query plans. One simple workaround for this is converting the data to an RDD and back to Data Frame/Dataset at the end of each iteration, 

Spark SQL provides a JDBC server to allow external tools, such as business intelli‐ gence GUIs like Tableau, to work with data accessible in Spark and to share resour‐ ces. Spark SQL’s JDBC server requires that Spark be built with Hive support. 

The default implementation of a join in Spark is a shuffled hash join.  - so shuffle till you get same key in same partition

Joins can be optimised by known partitioner and broadcasting smaller dataset to join. Spark SQL will be smart enough to configure the broadcast join itself; in Spark SQL this is controlled with spark.sql.autoBroadcastJoinThres hold and spark.sql.broadcastTimeout. You can hint to Spark SQL that a given DF should be broadcast for join by calling broadcast on the DataFrame before joining it (e.g., df1.join(broadcast(df2), "key")). Spark also automatically uses the spark.sql.conf.autoBroadcastJoinThreshold to determine if a table should be broadcast. 


Spark has two types of shared variables—broadcast variables and accumulators—each of which can only be written in one context (driver or worker, respectively) and read in the other. Broadcast variables can be written in the driver program and read on the executors, whereas accumulators are written onto the executors and read on the driver. 

Some sort of  a golden rule with RDDs is to use mapPartition . Create Iter-to-iter transformations in that mapPartition inner function. You can use mapPartition for e.g., for DB connection or worker setup stuff. If the setup work can be serialized, a broadcast variable can distribute the object . If the setup work can’t be serialized, a broadcast variable with a transient lazy val can be used as well. 
```
class LazyPrng { @transient lazy val r = new Random() 
} def customSampleBroadcast[T: ClassTag](sc: SparkContext, rdd: RDD[T]): RDD[T]= { 
val bcastprng = sc.broadcast(new LazyPrng()) 
rdd.filter(x => bcastprng.value.r.nextInt(10) == 0) } 
#COOL!!
```

The value for a broadcast variable must be a local, serializable value: no RDDs or other distributed data structures. 

Call persist() and cache() before actions, since Spark builds a lineage graph from either an RDD’s creation or a persisted/check-pointed RDD. 

Persisted RDDs only survive for the duration of a Spark application. To reuse data between Spark applications, use checkpointing with the same directory. 

As of Spark SQL 2.2, structured queries can be further optimized using Hint Framework.

```
COMPUTE STATISTICS of Tables Before Processing
	spark.sql("ANALYZE TABLE dbName.tableName COMPUTE STATISTICS")
	spark.sql("ANALYZE TABLE dbName.tableName COMPUTE STATISTICS FOR COLUMNS joinColumn, filterColumn")
```

Spark Streaming graceful shutdown
```
	ssc.start()
	ssc.awaitTermination()  <--- REMOVE THIS LINE
```
Use logic instead
```
	var TRIGGER_STOP = false
	var ssc: StreamingContext = …
	// Define Stream Creation, Transformations and Actions here.
	ssc.start()
	var isStopped = false
	while (!isStopped) {
     		isStopped = ssc.awaitTerminationOrTimeout(SPARK_SHUTDOWN_CHECK_MILLIS)
     		if (isStopped)
          		LOGGER.info("The Spark Streaming context is stopped. Exiting application...")
     		else 
          		LOGGER.info("Streaming App is still running. Timeout...")
     		checkShutdownMarker(ssc, SPARK_SHUTDOWN_RUNNING_MARKER_TOUCH_FILE_LOCATION)
     		if (!isStopped && TRIGGER_STOP) {
          		LOGGER.info("Stopping the ssc Spark Streaming Context...")
          		ssc.stop(stopSparkContext = true, stopGracefully = true)
          		LOGGER.info("Spark Streaming Context is Stopped!")
     	}
	}
```
Spark Streaming for stateful operations needs metadata checkpointing. But there are issues with it 
	Checkpoints can’t survive Spark Version Upgrades
	Checkpoints need to be cleared between Code Upgrades




 













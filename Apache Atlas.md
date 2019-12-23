## Atlas Notes
Metacat VS WhereHows VS Atlas

### Metacat
Supports Hive, RDS, Teradata, Redshift, S3 and Cassandra. 
Limited
No versioning
No Lineage

### WhereHows
Play framework. Gradle, No cross-data center read and write support 
11 Gig Setup so not played around yet
Security - LDAP integration available
Uses ElasticSearch


### Atlas
Supports directly
	Hive, Sqoop, Falcon and Storm
Hbase + Solr + Titan + Kafka (Hence zookeeper)
Classifications — like pii, expires_on, data_quality.
Create type not supported in UI
Create entity except most not supported in UI

```
export ATLAS_BASE_URL=http://sim-nn.c.data-engineering-230006.internal:21000/api/atlas/v2
export ATLAS_BASE_URL_V1=http://sim-nn.c.data-engineering-230006.internal:21000/api/atlas
```

Delete some entities

```
	curl -negotiate -u admin:Qwerty@123 -X DELETE -H 'Content-Type: application/json' -H 'Accept: application/json' "$ATLAS_BASE_URL/entity/guid/19e58699-0b91-4789-8bda-911962c47414"
	curl -negotiate -u admin -X DELETE -H 'Content-Type: application/json' -H 'Accept: application/json' "$ATLAS_BASE_URL/entity/guid/19e58699-0b91-4789-8bda-911962c47414"
```
Delete a type 

```
curl -negotiate -u admin -X DELETE -H 'Content-Type: application/json' -H 'Accept: application/json' "$ATLAS_BASE_URL/types/typedef/name/hdfs_column"
curl -negotiate -u admin -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' "$ATLAS_BASE_URL/types/typedefs" -d "@./typedef_hdfs_file.json" 
curl -negotiate -u admin -X POST -H 'Content-Type: application/json' -H 'Accept: application/json' "$ATLAS_BASE_URL/types/typedefs" -d “@./typedef_hdfs_file.json" 
```

### Databricks Delta
Entirely outside of Hadoop

### Pachyderm 
A “different” Hadoop

### Objective
Schema Discovery
	Schema in 
		HDFS
			CSV
			Parquet
			Orc
		Hive
			Any of the above
		Kafka
		RDBMS
		S3

### Security
	Ranger Integration
	LDAP
	Auth n Auth
### Schema Change
	Versioning
	Compatibility
### Data Lineage
	WTF does it even mean
		

https://www.ebayinc.com/stories/blogs/tech/bigdata-governance-hive-metastore-listener-for-apache-atlas-use-cases/

https://medium.com/hashmapinc/apache-atlas-using-the-v2-rest-api-6f9be1c256ae

https://blog.octo.com/en/how-to-hack-spark-to-do-some-data-lineage/

https://github.com/hortonworks-spark/spark-atlas-connector/tree/ce20e0fda4384bb652dbd1316ab49347b3e7de93

https://atlas.apache.org/0.7.1-incubating/AtlasTechnicalUserGuide.pdf

https://atlas.apache.org/api/v2/resource_EntityREST.html#resource_EntityREST_createOrUpdate_POST

https://www.ebayinc.com/stories/blogs/tech/bigdata-governance-hive-metastore-listener-for-apache-atlas-use-cases/

http://www.datanucleus.org/

https://github.com/linkedin/WhereHows

https://github.com/SnappyDataInc/snappydata

### Spark-atlas-connector
- Use fat jar
- Don’t use master, use from release https://github.com/hortonworks-spark/spark-atlas-connector/releases/tag/v0.1.0-2.3-1.0.0 since master is with 2.4; it fails for class ExternalCatalogWithListener
- regular spark-submit
```
spark-submit --master yarn --class com.trustingsocial.processor.spark.app.Processor --jars hdfs://sim-nn/apps/trust-data/lib/spark-trusting-assembly-0.1.0-SNAPSHOT-deps.jar,hdfs://sim-nn/apps/trust-data/lib/spark-extension-assembly-0.1.0-SNAPSHOT.jar --files hdfs://sim-nn/apps/trust-data/conf/disconnection_static.conf --deploy-mode cluster --conf spark.yarn.am.waitTime=300s --conf spark.app.name=airtel.disconnection.static hdfs://sim-nn/apps/trust-data/lib/processor-assembly-0.1.0-SNAPSHOT.jar --app-name airtel.disconnection_static --config disconnection_static.conf -Detl.input.path=/data/encrypted/disconnection_static/trx_date\\=20190620  -Detl.output.path=/data/clean/disconnection_static/processed_date\\=20160620
-command that seems to work  spark-submit --master yarn --class com.trustingsocial.processor.spark.app.Processor \
--jars hdfs://sim-nn/apps/trust-data/lib/spark-trusting-assembly-0.1.0-SNAPSHOT-deps.jar,hdfs://sim-nn/apps/trust-data/lib/spark-extension-assembly-0.1.0-SNAPSHOT.jar,hdfs://sim-nn/apps/trust-data/lib/spark-atlas-connector-assembly_2.11-0.1.0-SNAPSHOT.jar \
—files hdfs://sim-nn/apps/trust-data/conf/disconnection_static.conf,hdfs://sim-nn/apps/trust-data/resources/atlas-application.properties \
--deploy-mode cluster \
--conf spark.extraListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker \
—conf spark.sql.queryExecutionListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker \
—conf spark.sql.streaming.streamingQueryListeners=com.hortonworks.spark.atlas.SparkAtlasStreamingQueryEventTracker \
--conf spark.yarn.am.waitTime=300s --conf spark.app.name=airtel.disconnection.static hdfs://sim-nn/apps/trust-data/lib/processor-assembly-0.1.0-SNAPSHOT.jar \
—app-name airtel.disconnection_static --config disconnection_static.conf \
-Detl.input.path=/data/encrypted/disconnection_static/trx_date\\=20190620  
-Detl.output.path=/data/clean/disconnection_static/processed_date\\=20160620
```
- But I am in a secured cluster
These settings are relevant, either  add or find
```
atlas.jaas.KafkaClient.loginModuleControlFlag=required
atlas.jaas.KafkaClient.loginModuleName=com.sun.security.auth.module.Krb5LoginModule
atlas.jaas.KafkaClient.option.keyTab=/etc/security/keytabs/atlas.service.keytab
atlas.jaas.KafkaClient.option.principal=atlas/_HOST@C.DATA-ENGINEERING-230006.INTERNAL
atlas.jaas.KafkaClient.option.serviceName=kafka
atlas.jaas.KafkaClient.option.storeKey=true
atlas.jaas.KafkaClient.option.useKeyTab=true
```
The command that worked for me
```
spark-submit --master yarn --class com.trustingsocial.processor.spark.app.Processor 
--jars hdfs://sim-nn/apps/trust-data/lib/spark-trusting-assembly-0.1.0-SNAPSHOT-deps.jar,hdfs://sim-nn/apps/trust-data/lib/spark-extension-assembly-0.1.0-SNAPSHOT.jar,hdfs://sim-nn/apps/trust-data/lib/spark-atlas-connector-assembly_2.11-0.1.0-SNAPSHOT.jar \ 
--files hdfs://sim-nn/apps/trust-data/conf/disconnection_static.conf,hdfs://sim-nn/apps/trust-data/resources/atlas-application.properties \ copied from atlas config
- -deploy-mode cluster \
--conf spark.extraListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker \
--conf spark.sql.queryExecutionListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker \
--conf spark.sql.streaming.streamingQueryListeners=com.hortonworks.spark.atlas.SparkAtlasStreamingQueryEventTracker \
—conf spark.yarn.am.waitTime=300s --conf spark.app.name=airtel.disconnection.static 
--principal atlas/sim-nn.c.data-engineering-230006.internal@C.DATA-ENGINEERING-230006.INTERNAL 
--keytab /home/spark/atlas.service.keytab hdfs://sim-nn/apps/trust-data/lib/processor-assembly-0.1.0-SNAPSHOT.jar 
--app-name airtel.disconnection_static --config disconnection_static.conf -Detl.input.path=/data/encrypted/disconnection_static/trx_date\\=20190620  -Detl.output.path=/data/clean/disconnection_static/processed_date\\=20160620
```
Use these settings, copy keytab /etc/security/keytabs/atlas.service.keytab to common spark accessible , local location on each node ,like ~spark. It might fail on _HOST in principal . So get the real principal using o/p of 
```
klist -kt /etc/security/keytabs/atlas.service.keytab
kinit -V -k -t /etc/security/keytabs/atlas.service.keytab <output of above> 
```
Shows authenticated, tested same thing with spark user. Change principal to reflect the principal coming in output. 
Same thing with spark user, basically , n not right though, I impersonated spark into atlas user to run it. The better way would be 
```
atlas.jaas.MyClient.1.loginModuleName = com.sun.security.auth.module.Krb5LoginModule
atlas.jaas.MyClient.1.loginModuleControlFlag = optional
atlas.jaas.MyClient.1.option.useKeyTab = true
atlas.jaas.MyClient.1.option.storeKey = true
atlas.jaas.MyClient.1.option.serviceName = kafka
atlas.jaas.MyClient.1.option.keyTab = /etc/security/keytabs/kafka_client.keytab
atlas.jaas.MyClient.1.option.principal = kafka-client-1@EXAMPLE.COM
```
And add atlas.client.type=my in atlas-application.properties  (Something like this). Then use relevant user (principal/keytab) in spark-submit command.Will check this later. 

Check result on ATLAS UI. 
We see spark_db, spark_column etc. But these are atlas spark models, not Spark models as they are coming in new atlas releases, which are with spark 2.4 

Now we want to see actual entries in Atlas’ Spark Entities

Tries

`spark-shell --jars hdfs://sim-nn/apps/trust-data/lib/spark-atlas-connector-assembly_2.11-0.1.0-SNAPSHOT.jar --files hdfs://sim-nn/apps/trust-data/resources/atlas-application.properties --conf spark.extraListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker --conf spark.sql.queryExecutionListeners=com.hortonworks.spark.atlas.SparkAtlasEventTracker --conf spark.sql.streaming.streamingQueryListeners=com.hortonworks.spark.atlas.SparkAtlasStreamingQueryEventTracker `

Read a df
```
df=<something>
 df.createTempView("disconnection_static")
 ```
 
Now this again failed because Spark 2.3 uses AnalysisBarrier which collied with java.lang.ClassCastException: org.apache.spark.sql.catalyst.plans.logical.AnalysisBarrier cannot be cast to org.apache.spark.sql.catalyst.plans.logical.Project No fix for this  (https://github.com/hortonworks-spark/spark-atlas-connector/issues/75), since Atlas is not supporting 2.3 anymore. (https://github.com/hortonworks-spark/spark-atlas-connector/issues/65)


### TODO
hdfs_path - check on deletes
Add user in spark command via new JAAS config


### Atlas latest + with Spark 2.4
- AnalysisBarrier
- SparkModels
- QueryExecutionListener
- Others are anyway tech preview releases, should not be relied
- Stopped support



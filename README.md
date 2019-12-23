## Notes from a small learning

### Java n Scala
`https://medium.com/swlh/tools-for-launching-and-developing-products-on-jvm-94a29d502d11`

### Massive Ambitious TODOS
- Atlas
    - Behind Knox
- Holochain with RUST
- Coding
- ML
- GraphQL
- Deequ
- Delta Lake
- Terraform
- Neo4j on Spark
- Apache Beam
- Apache Arrow
- ScyllaDB
- OpenFaas
- Cloudbreak
- Kafka Lens - for visualising Kafka cluster
- Nifi
- MLFlow
- Uber Hudi
- CEPH

Blockchain
Distributed Open Knowledge App
	Behind:
		Holochain
		Your own blockchain ( :O ) (or Fabri. with diff consensus, read below)
		Code in python for ease ( :O ) ?? (not if fabric)
		Proof of reputation like Sumeragi (or actual Sumeragi) for writers
		For readers not so much
		Store using IPFS if txn accepted
		Speed, scale not important for now
		Security use existing, not sure for self implementation
		Network building ???
		Pub/Sub ???
		Easy to host for read, can host only parts interested  in sharing
			Prevent tamper
				File modif time record on all nodes
				if you modif your file txn history will prevent from reading it
				or could this be on blockchain itself -
		Not for write, u register as a writer only by actual writeup and its acceptance

Using Holochain :
1. in javascript and lisp, trying go but not sure
2. uses interpreted, scripting languages inside go-shell
3. agent init is very lame??
4. not anon- pick that later
5. why does it not use my agent identity

### Data Management
	Pachyderm - data versioning
	Apache Atlas, Netflix Metacat, LinkedIn WhereHows, Hive Metastore , Databricks Delta - Data governance n lineage
	Interact - DS tool for writing common models (better than Github, how?)
	Data validation - Target’s code

### Back to ML
	Tensorflow
	Check out OpenMined

`https://medium.freecodecamp.org/9-books-for-junior-developers-in-2019-e41fc7ecc586`
`designing data-intensive apps`

ElasticSearch
I’m currently handling 60GB of data and 100 million documents, I’m doing it using two virtual servers provided by GCP, each has 2 vCPU’s and 8GB of memory. For my use case of querying the data to do analytics this is absolutely fine.

Hadoop Capacity Planning
Care for the master nodes, keep an eye on the slave nodes
1. https://www.oreilly.com/library/view/hadoop-operations/9781449327279/ch04.html
2. https://www.ibm.com/support/pages/configuring-namenode-heap-size
3. https://docs.cloudera.com/HDPDocuments/HDP3/HDP-3.1.4/cluster-planning/content/other-issues.html

### Random  Troubleshoots:
Snappy from CMD line, note java/hadoop argument its important
  ```
	snzip -t snappy-in-java disconnection_static.txt
  ```

For getting group of a user in hdfs
hdfs groups <user_name>

If Ambari UI tasks are not getting killed
- Get their PID from progress bar’s /var/log/error_<pid>.txt
- Go to Ambari’s Postgres, generally U/P is ambari/bigdata
- And fire this query
`select task_id, role, role_command, status from host_role_command where task_id = 1491<the pid>;`
- If the status in PENDING or ‘IN PROGRESS’ then set to abort
`update host_role_command set status = 'ABORTED' where status = 'IN_PROGRESS';`
`(SELECT * FROM information_schema.columns WHERE table_schema = 'public'`
`SELECT * FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'`

To clean up
```
_apt-get purge --auto-remove packagename_
_dpkg --list_
_apt-get remove package_name_
_apt-get purge package_name_
```
Ubuntu 16.04 install Postgres10

`https://websiteforstudents.com/installing-postgresql-10-on-ubuntu-16-04-17-10-18-04/`

PG backup as Postgres user
```
_pg_dump -C -h 172.20.0.9 -U dereaduser -p 55432 testdb > local_blink_backup.sql_

_pg_dump -C -h 172.20.0.9 -U dereaduser -p 55432 -t accounts testdb > local_blink_backup.sql_
```

There could be some diffs; for e.g. do this
```
_CREATE EXTENSION pgcrypto;
SELECT gen_random_bytes(1)
```
And check this
```
select pg_get_functiondef(to_regproc('gen_random_bytes'));
select pg_get_functiondef(to_regproc('gen_random_uuid'));_
```

Then dump
```
_psql -d testdb -f testtable.sql
rm *
aws s3 cp --recursive --exclude="*" --include="VT_LEAD1_2019040[4-9].xlsx" s3://ts.leadgen.funnel.reports/fe_credit/vt_leads/ ._
```
Or
```
aws s3 cp --recursive --exclude="*" --include="ocb_lead_by_report_201905*.xlsx" s3://ts.leadgen.funnel.reports/ocb/lead_reports/ .

find *.xlsx -exec xlsx2csv {} {}.csv \;

for i in *.xlsx.csv; do mv -v $i `basename $i .xlsx.csv`.csv; done

aws s3 cp vt_leads/ s3://ts.leadgen.funnel.reports/fe_credit/vt_leads/ --recursive --exclude "*" --include "*.csv"
```
Or
```
feCreditCCReportsDF.coalesce(5).withColumn("trx_date", date_format($"REPORT_DATE", "yyyyMMdd")).write.mode("append").partitionBy("trx_date").parquet(existingDFPath)
```

Zeppelin with s3, 
- now use s3a only, set access and secret in hdfs configurations, 
- use only HDFS version hadoop-aws and aws-sdk jars,
- Test this with spark-shell may be, if works copy same jars, or add only those jars explicitly to zeppelin

Zeppelin with jdbc

Interpreter

  not needed, but may be for direct viewing and visualisation

Add jar

  Add in new para
  
```

%dep

val existingDFPath = "/data/non_fb/risk_reports"

var fromDate = if (dirExists(existingDFPath)) spark.read.parquet(existingDFPath).agg(date_format(max($"report_date"), "yyyy-MM-dd HH:mm:ss")).collect()(0).get(0).toString() else new Date(0)

val selectStr = s"(SELECT * FROM risk_reports WHERE report_date > '$fromDate') AS risk_reports"

val driver = "org.postgresql.Driver"

val riskReportsDF = spark.read.format("jdbc").option("url", "jdbc:postgresql://172.31.4.163:5432/non_fb").option("dbtable", selectStr).option("user", "***").option("password","***").option("driver", driver).load()

riskReportsDF.withColumn("trx_date", date_format($"report_date", "yyyyMMdd")).write.mode("append").partitionBy("trx_date").parquet(existingDFPath)

val existingDFPath = "/data/non_fb/risk_reports"

```

```
diff <(hdfs dfs -ls /ts-de/etl/raw/report/funnel/fe_credit/cc_reports/ | awk -F"/" '{print $NF}') <(hdfs dfs -ls /wh/report/funnel/fe_credit/cc_reports/ | awk -F"/" '{print $NF}') | awk -F"< " '$NF ~ /^ *trx_date=/{print $NF}' | xargs -i echo "/ts-de/etl/raw/report/funnel/fe_credit/cc_reports/{}"

```

```
rsync --progress --stats --times --perms -e " ssh -i ~/.ssh/id_rsa"
```



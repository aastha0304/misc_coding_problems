## HA

### For airflow ha

  `http://site.clairvoyantsoft.com/making-apache-airflow-highly-available/`
    
  `https://medium.com/wbaa/airflow-ha-environment-c60ddca825a9`
    
### For airflow
  `http://site.clairvoyantsoft.com/installing-and-configuring-apache-airflow/`
  
  ```
	csshx -login ec2-user -ssh_args "-i /Users/nik/.ssh/bigdata_sandbox.pem" 13.233.12.126 52.66.249.38
	yum install python-pip
	export AIRFLOW_GPL_UNIDECODE=yes
	export AIRFLOW_HOME=~ec2-user/airflow
	yum install python-devel mysql-devel
	pip install --user "apache-airflow[mysql,celery,rabbitmq,postgres]"
  ```
  
	edits in airflow.cfg
	master 
		initdb
		webserver
		schedule
	worker
		
### For postgres  HA
```
csshx -login ec2-user -ssh_args "-i /Users/nik/.ssh/bigdata_sandbox.pem" 35.154.166.0 13.232.86.16
```
    
On both
```
    sudo yum install postgresql postgresql-server postgresql-devel postgresql-contrib postgresql-docs
    sudo /usr/bin/postgresql-setup initdb
```
		Follow instructions from
```
    https://www.howtoforge.com/tutorial/how-to-install-and-configure-master-slave-replication-with-postgresql-96-on-centos-7/	
```
    But change this command
```
pg_basebackup -h 172.31.21.79 -U postgres -D /var/lib/pgsql/data
```
For airflow nodes to connect , add to pg_hba.conf
```
host    all     all     all     md5
```
### Rabbitmq. - HA no LB
  
		`http://site.clairvoyantsoft.com/installing-rabbitmq/`
		13.233.12.126 52.66.249.38
		`http://linuxpitstop.com/rabbitmq-cluster-on-centos-7/`
    
```
rabbitmqctl add_user test test
rabbitmqctl set_user_tags test administrator
rabbitmqctl set_permissions -p / test ".*" ".*" ".*"
		http://13.233.12.126:15672/ admin admin1234
```

Note 
Postgres logs - /var/lib/pgsql/data/pg_log/postgresql

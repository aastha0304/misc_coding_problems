## Rundeck
### Installation
on open machine
```
			rpm -Uvh http://repo.rundeck.org/latest.rpm 
			yum install rundeck
			sudo /etc/init.d/rundeckd start
```      
Now comes on $HOST:4440 with admin/admin crews
Behind Nginx
add this snippet in http { server{}}
```
location /rundeck/ {
proxy_pass http://localhost:4440;
proxy_set_header X-Forwarded-Host $host:$server_port;
proxy_set_header X-Forwarded-Server $host;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
}	
```
also need to change runback configs
```
export RUNDECK_URL=https://35.247.133.250/rundeck
sed -i "/^grails.serverURL/c grails.serverURL = ${RUNDECK_URL}" /etc/rundeck/rundeck-config.properties
sed -i "/^framework.server.url/c framework.server.url = ${RUNDECK_URL}" /etc/rundeck/framework.properties
sed -i '/^RDECK_JVM="$RDECK_JVM/ s/"$/ -Dserver.web.context=\/rundeck"/' /etc/rundeck/profile
	
sudo /etc/init.d/rundeckd restart
sudo service nginx restart
```
Go to https://35.247.133.250/rundeck with admin/admin
Of First page, create New Project
Then add source
For adding source, use File Source, in json format (or yams or xml)
This is a sample Json file
```
{
  "sim-ipa": {
    "tags": "server",
    "osFamily": "unix",
    "username": "nik",
    "osArch": "x86_64",
    "hostname": "sim-ipa-server.c.data-engineering-230006.internal",
    "nodename": "sim-ipa"
  },
  "sim-nn": {
    "tags": "server",
    "osFamily": "unix",
    "username": "nik",
    "osArch": "x86_64",
    "hostname": "sim-nn.c.data-engineering-230006.internal",
    "nodename": "sim-nn"
  },
  "sim-dn": {
    "tags": "server",
    "osFamily": "unix",
    "username": "nik",
    "osArch": "x86_64",
    "hostname": "sim-dn.c.data-engineering-230006.internal",
    "nodename": "sim-dn"
  },
  "sim-dn-1": {
    "tags": "server",
    "osFamily": "unix",
    "username": "nik",
    "osArch": "x86_64",
    "hostname": "sim-dn-1.c.data-engineering-230006.internal",
    "nodename": "sim-dn-1"
  },
  "sim-bastion": {
    "tags": "local,server",
    "osFamily": "unix",
    "username": "nik",
    "osArch": "x86_64",
    "hostname": "sim-bastion.c.data-engineering-230006.internal",
    "nodename": "sim-bastion"
  }
} 
```

Then you can see new nodes added in the system.
Back to new Project Settings
	Fill in details in Default Node Executor for SSH
	You can copy same details in Default File Copier for SCP
Following are my values in 
SSH Key File path /var/lib/rundeck/.ssh/id_rsa`
SSH Authentication `privateKey`

### Commands
	You can run commands 
	in Nodes form field, select 1 or all nodes and give a command like ls, etc

Jobs
	Sample File copy command
```
- defaultTab: nodes
  description: ''
  executionEnabled: true
  group: FileTransfer
  id: 303053d4-4c31-4fde-bfc4-68e6a20e0823
  loglevel: INFO
  name: copy_file
  nodeFilterEditable: false
  scheduleEnabled: true
  sequence:
    commands:
    - configuration:
        backupDestinationFile: 'false'
        backupSourceFile: 'false'
        deleteFile: 'false'
        destURLString: ftp://sim-dn/home/nik
        destUsername: nik
        echo: 'true'
        recursive: 'false'
        sourceURLString: file:///var/lib/rundeck/projects/cluster_files.json
      nodeStep: true
      type: copyfile
    keepgoing: false
    strategy: node-first
  uuid: 303053d4-4c31-4fde-bfc4-68e6a20e0823
```	
	
From UI you can give File Copy in workflow
	choose target node in nodes tab 
	and give required fields like source n destination paths, it creates a YAML/XML/JSON as above.

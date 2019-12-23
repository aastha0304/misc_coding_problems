Kafka Notes

https://kafka.apache.org/documentation/#design

A tunic sample : 6.4 Java Version

Log compaction
kafka-topics --create --zookeeper zookeeper:2181 --topic latest-product-price --replication-factor 1 --partitions 1 --config "cleanup.policy=compact" --config "delete.retention.ms=100"  --config "segment.ms=100" --config "min.cleanable.dirty.ratio=0.01"

In reality, however, the partition log is divided by Kafka broker into segments. Segments are files stored in the file system (inside data directory and in the directory of the partition), which their name ends with .log.

https://towardsdatascience.com/log-compacted-topics-in-apache-kafka-b1aa1e4665a7

https://thehoard.blog/how-kafkas-storage-internals-work-3a29b02e026

On disk, a partition is a directory and each segment is an index file and a log file.

Kafka has a leader per partition, irrespective of replica number

https://medium.com/swlh/understanding-kafka-a-distributed-streaming-platform-9a0360b99de8

Disk seek - sequential, are not slow. OS does a lot of disk caching if memory available, which can be really useful for Kafka. Also, Using Java, if in-memory object route is used then it will be very costly you can tell

rather than maintain as much as possible in-memory and flush it all out to the filesystem in a panic when we run out of space, we invert that. All data is immediately written to a persistent log on the filesystem without necessarily flushing to disk. In effect this just means that it is transferred into the kernel's pagecache.


The persistent data structure used in messaging systems are often a per-consumer queue with an associated BTree or other general-purpose random access data structures to maintain metadata about messages.

Intuitively a persistent queue could be built on simple reads and appends to files as is commonly the case with logging solutions. This structure has the advantage that all operations are O(1) and reads do not block writes or each other. 

To avoid this, Kafka protocol is built around a "message set" abstraction that naturally groups messages together. This allows network requests to group messages together and amortize the overhead of the network roundtrip rather than sending a single message at a time. The server in turn appends chunks of messages to its log in one go, and the consumer fetches large linear chunks at a time.

Modern unix operating systems offer a highly optimized code path for transferring data out of pagecache to a socket; in Linux this is done with the sendfile system call.
To understand the impact of sendfile, it is important to understand the common data path for transfer of data from file to socket:
1. The operating system reads data from the disk into pagecache in kernel space
2. The application reads the data from kernel space into a user-space buffer
3. The application writes the data back into kernel space into a socket buffer
4. The operating system copies the data from the socket buffer to the NIC buffer where it is sent over the network

Kafka does at-least once, also exactly-once is provided. (Used in Kafka Streams)

that a message is considered committed when all in sync replicas for that partition have applied it to their log

For Kafka node liveness has two conditions
1. A node must be able to maintain its session with ZooKeeper (via ZooKeeper's heartbeat mechanism)
2. If it is a follower it must replicate the writes happening on the leader and not fall "too far" behind
We refer to nodes satisfying these two conditions as being "in sync" 

This is not what Kafka does, but let's explore it anyway to understand the tradeoffs. Let's say we have 2f+1 replicas. If f+1 replicas must receive a message prior to a commit being declared by the leader, and if we elect a new leader by electing the follower with the most complete log from at least f+1 replicas, then, with no more than f failures, the leader is guaranteed to have all committed messages. This is because among any f+1 replicas, there must be at least one replica that contains all committed messages. 


What do I do if my brokers die?
1. Wait for a replica in the ISR to come back to life and choose this replica as the leader (hopefully it still has all its data).
2. Choose the first replica (not necessarily in the ISR) that comes back to life as the leader.
This is unclean leader election

By default, when acks=all, acknowledgement happens as soon as all the current in-sync replicas have received the message. For example, if a topic is configured with only two replicas and one fails (i.e., only one in sync replica remains), then writes that specify acks=all will succeed.  (In my case)


we provide two topic-level configurations that can be used to prefer message durability over availability:
Disable unclean leader election
Specify a minimum ISR size 


Leader election when failed - We elect one of the brokers as the "controller". This controller detects failures at the broker level and is responsible for changing the leader of all affected partitions in a failed broker. If the controller fails, one of the surviving brokers will become the new controller.

Kafka cluster has the ability to enforce quotas on requests to control the broker resources used by clients. Two types of client quotas can be enforced by Kafka brokers for each group of clients sharing a quota:
1. Network bandwidth quotas define byte-rate thresholds (since 0.9)
2. Request rate quotas define CPU utilization thresholds as a percentage of network and I/O thread
By default, each client-id receives an unlimited quota. The following sets the default quota per producer and consumer client-id to 10MB/sec.
1
2	quota.producer.default=10485760
quota.consumer.default=10485760
Note that these properties are being deprecated and may be removed in a future release. Defaults configured using kafka-configs.sh take precedence over these properties.

The network layer is a fairly straight-forward NIO server

Since a typical folder name can not be over 255 characters long, there will be a limitation on the length of topic names. We assume the number of partitions will not ever be above 100,000. Therefore, topic names cannot be longer than 249 characters. This leaves just enough room in the folder name for a dash and a potentially 5 digit long partition id.

Set controlled.shutdown.enable=true, for maintenance, rebalance etc


Kafka consumer tracks the maximum offset it has consumed in each partition and has the capability to commit offsets so that it can resume from those offsets in the event of a restart. Kafka provides the option to store all the offsets for a given consumer group in a designated broker (for that group) called the group coordinator. 

Kafka has a notion of preferred replicas. If the list of replicas for a partition is 1,5,9 then node 1 is preferred as the leader to either node 5 or 9 because it is earlier in the replica list.s You can do this manually or better set auto.leader.rebalance.enable=true

Rebalancing : The process of migrating data is manually initiated but fully automated. Under the covers what happens is that Kafka will add the new server as a follower of the partition it is migrating and allow it to fully replicate the existing data in that partition. When the new server has fully replicated the contents of this partition and joined the in-sync replica one of the existing replicas will delete their partition's data. Entire partition is moved There can be specific rebalancing of certain topics to certain brokers as well. If you were to execute a rebalance, with the below command, it would move partitions at no more than 50MB/s. You can throttle bandwidth usage during data migration Revert throttling after so that regular replication is not affected. Also ensure that incoming stream rate is lesser than migration rate otherwise you’ll be stuck

Check and set this auto.create.topics.enable=false

there are three potentially important OS-level configurations:
Kafka uses file descriptors for log segments and open connections. If a broker hosts many partitions, consider that the broker needs at least (number_of_partitions)*(partition_size/segment_size). We recommend at least 100000 allowed file descriptors for the broker processes as a starting point. 
Max socket buffer size
Maximum number of memory map areas a process may have (aka vm.max_map_count). By default, on a number of Linux systems, the value of vm.max_map_count is somewhere around 65535. Each log segment, allocated per partition, requires a pair of index/timeindex files, and each of these files consumes 1 map area. In other words, each log segment uses 2 map areas. So creating 50000 partitions on a broker will result allocation of 100000 map areas and likely cause broker crash with OutOfMemoryError (Map failed) on a system with default vm.max_map_count

We recommend using the default flush settings which disable application fsync entirely
In Linux, data written to the filesystem is maintained in pagecache until it must be written out to disk (due to an application-level fsync or the OS's own flush policy). The flushing of data is done by a set of background threads called pdflush (or in post 2.6.32 kernels "flusher threads”).Try not to use app-level sync

For any filesystem used for data directories, on Linux systems, the following options are recommended to be used at mount time:
* noatime: This option disables updating of a file's atime (last access time) attribute when the file is read. This can eliminate a significant number of filesystem writes, especially in the case of bootstrapping consumers. Kafka does not rely on the atime attributes at all, so it is safe to disable this.

Kafka Connect can be paused!!!!
```
https://docs.confluent.io/current/connect/managing/community.html
```





 

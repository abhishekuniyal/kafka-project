Steps to run this project::::
A very old producer-consumer IT problem could be easily solved using apache kafka, a highly throughput distributed streaming platform.  

1.) download apache kafka
2.) create a user kafka --optional step but recommended
	on linux ... sudo adduser kafka => passwd kafka ==> useradd sudo ==> su -l kafka
3.) start the zookeeper instance
	./bin/zookeeper-server-start.sh config/zookeeper.properties 
	by deafult zookeeper instance will run at 0.0.0.0:2181
4.) start the kafka instance
	./bin/kafka-server-start.sh config/server.properties
	deafult : localhost:9092

5.) create two new TOPIC
	./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TOPIC
	Created topic TOPIC.
	&&&&
	./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic JSON_TOPIC
	  	Created topic JSON_TOPIC.
6.) Run the spring project kafka-project
7.) Hit the below endpoints
	http://localhost:8080/profile/abhishek
	http://localhost:8080/post/abhishek
	Above endpoints will produce a message in the corresponding topics. 
8.) Consumer will pick up the messages on Topic and will be visinle in the console output.

********************************Fault tolerance ******************************************************
Producer config acks flag ==> 0,    ==> producer will not wait for ACK , highest throughput, chances of data lost
							  1,	==> producer will wait for ACK , slightly lower throughput, lower chances of data lost(If leader sends ACK but leader crashes before passing data to followeres , Data is lost even after the ACK)
							  all   ==> producer will wait for ACK , slightly lower throughput, No chance of data lost 

********************************Fault tolerance ******************************************************
replication factor =3 i.e 3 copies will be created and distributed across 3 broker in a cluster.
how can we achieve this in kafka? ==> Leader will have main copy - follower will copy the data.

1.) create 3 broker list 
2.) create 2 more server.properties for the new brokers
	server1.properties & server2.properties
3.) change below properties in properties file
	broker.id =1
	listners = PLAINTEXT://9093 and listners = PLAINTEXT://9094
	log.dirs = /tmp/kafka-logs2
4.) start the 2 new brokers 
./bin/kafka-server-start.sh config/server1.properties
./bin/kafka-server-start.sh config/server1.properties
5.) Now we have 3 node clusters with 1 Leader and 2 Followers (based on selection )
6.) Now create a topic with replication factor 
	./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 3 --partitions 2 --topic TOPIC
	Created topic TOPIC.
7.) you can check the TOPIC using describe command, 
./bin/kafka-topics.sh --create --zookeeper localhost:2181 --describe --topic TOPIC   which will show you below,
	Topic: TOPIC Partition:0, Leader: 1, Replicas:1,2,0 , ISR: 1,2,0 (In-sync-Replicas i.e all broker is in sync with leader)
	Topic: TOPIC Partition:1, Leader: 2, Replicas:2,0,1 , ISR: 2,0,1


							  
							  
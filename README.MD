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
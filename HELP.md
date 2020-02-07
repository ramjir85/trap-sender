# Camel SNMP Trap Component

### Objective
This is a plug and play component using Springboot for getting SNMP traps from any host over to resilient Kafka broker.


### Pre-Requisite
This component relies on SNMP Traps being sent over the UDP port 162 and moving them over to resilient kafka
broker for further processing. 


### Configuration and Running the Component

* Set Up Kafka (Zookeeper/Broken and wanted Topics)
* Update the respective configuration to the application.yml file
* Run - ./gradlew clean build run


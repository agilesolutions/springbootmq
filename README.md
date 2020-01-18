# Springboot and IBM MQ and Oracle
This page shows how easy you can develop and test Springboot against IBM MQ messaging by letting [skaffold](https://itnext.io/continuous-spring-boot-deployment-in-kubernetes-using-jib-and-skaffold-11fd3c71d941) launch a full capable [IBM MQ server container](https://github.com/ibm-messaging/mq-container), allowing you to run a full integration test.

Read more about [MQ JMS application development with Spring Boot](https://developer.ibm.com/tutorials/mq-jms-application-development-with-spring-boot/)

## MQ integration

* [Running Oracle DB image](https://github.com/ibm-messaging/mq-container)

```
docker run ‑‑env LICENSE=accept ‑‑env MQ_QMGR_NAME=QM1
           ‑‑publish 1414:1414
           ‑‑publish 9443:9443
           ‑‑detach
           ibmcom/mq
```

## Oracle integration

* [Running Oracle DB image](https://github.com/oracle/docker-images/blob/master/OracleDatabase/SingleInstance/README.md)

```
docker run --name <container name> \
-p <host port>:1521 -p <host port>:5500 \
-e ORACLE_SID=<your SID> \
-e ORACLE_PDB=<your PDB name> \
-e ORACLE_PWD=<your database passwords> \
-e ORACLE_CHARACTERSET=<your character set> \
-v [<host mount point>:]/opt/oracle/oradata \
oracle/database:19.3.0-ee

```


Abiquo Commons AMQP
===================

One library to use RabbitMQ in an unified way between Abiquo's modules. It includes producers, consumers, DTOs, and exchange/binding/queue configurations for each module.

Maven coordinates
-----------------

Add abiquo's maven repo

```xml
<repositories>
    <repository>
        <id>abiquo-repo</id>
        <name>Abiquo Maven Repository</name>
        <url>http://repo.community.abiquo.com/repo</url>
    </repository>
</repositories>
```

Add maven dependency

```xml
<dependency>
    <groupId>com.abiquo</groupId>
    <artifactId>commons-amqp</artifactId>
    <version>1.0-SNAPSHOT</version>
    </dependency>
</dependency>
```

Configuration properties
------------------------

* **abiquo.rabbitmq.host** where RabbitMQ is listening, by default *localhost*
* **abiquo.rabbitmq.port** where RabbitMQ is binded, by default *5672*
* **abiquo.rabbitmq.username** the username to use, by default *guest*
* **abiquo.rabbitmq.password** the password to use, by default *guest*
* **abiquo.rabbitmq.virtualHost** virtual host to use, by default */*
* **abiquo.rabbitmq.connectionTimeout** connection establishment timeout in milliseconds, by default 0 (wait indefinitely until connection established or error occurs) */*
* **abiquo.rabbitmq.requestedHeartbeat** the initially requested heartbeat interval, in seconds, by default 0 (none) */*

The following properties are used when the connection with RabbitMQ has been lost

* **abiquo.rabbitmq.retry.retries** number of retries to perform (0 for infinite), by default *0* 
* **abiquo.rabbitmq.retry.mstosleep** number of milliseconds to sleep beetwen retry attempts, by default *10000*


Intro-to-Spring-Hadoop
======================

Examples and Slides for "Introduction to Spring for Apache Hadoop" at SpringOne2GX 2014

Before starting [Spring XD](http://projects.spring.io/spring-xd/) or running any of the examples, start [Redis](http://redis.io/download) and your [Hadoop VM](https://github.com/SpringOne2GX-2014/Intro-to-Spring-Hadoop/blob/master/InstallingHadoop.asciidoc). You should also set the following environment variables:

```
export XD_HOME=~/SpringOne/spring-xd-1.0.0.RELEASE/xd
export spring_hadoop_fsUri=hdfs://borneo:8020
export spring_hadoop_resourceManagerHost=borneo
export spring_hadoop_jobHistoryAddress=borneo:10020
```

**Note:** Adjust your path and host name to what you are using.
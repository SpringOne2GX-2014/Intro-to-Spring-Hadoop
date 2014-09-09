Intro-to-Spring-Hadoop
======================

Examples and [Slides for "Introduction to Spring for Apache Hadoop"](https://github.com/SpringOne2GX-2014/Intro-to-Spring-Hadoop/blob/master/SpringOne2GX_2014_Spring_for_Apache_Hadoop.pdf) at SpringOne2GX 2014

If you need a basic Hadoop installation in a VM to run on your development system, we provide a basic step for step document for ["Installing Hadoop"](https://github.com/SpringOne2GX-2014/Intro-to-Spring-Hadoop/blob/master/InstallingHadoop.asciidoc). 

Before starting [Spring XD](http://projects.spring.io/spring-xd/) or run any of the examples, build and install the [tweets-mapreduce](https://github.com/SpringOne2GX-2014/Intro-to-Spring-Hadoop) project and start your [Hadoop VM](https://github.com/SpringOne2GX-2014/Intro-to-Spring-Hadoop/blob/master/InstallingHadoop.asciidoc). You should also set the following environment variables:

```
export XD_HOME=~/SpringOne/spring-xd-1.0.0.RELEASE/xd
export spring_hadoop_fsUri=hdfs://borneo:8020
export spring_hadoop_resourceManagerHost=borneo
export spring_hadoop_jobHistoryAddress=borneo:10020
```

**Note:** Adjust your path and host name to what you are using.

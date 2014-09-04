xd-batch-mapreduce
==================

Spring XD Batch Job MapReduce example.

Requires that a tweets-hadoop*.txt file is available in HDFS under /twets/input/batch dir.

From the XD shell run this:

```
xd:>hadoop config fs --namenode hdfs://borneo:8020
xd:>hadoop fs mkdir /tweets/input
xd:>hadoop fs mkdir /tweets/input/batch
xd:>hadoop fs copyFromLocal --from /Users/trisberg/Projects/Intro-to-Spring-Hadoop/data/hadoop-tweets_2014-08-11.txt --to /tweets/input/batch
```

Build with:

    $ mvn clean package install

Run from XD Shell with:

    xd>job create --name hashtags --definition "tweets-hashtags" --deploy
    xd>job launch --name hashtags


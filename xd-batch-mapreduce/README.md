xd-batch-mapreduce
==================

Spring XD Batch Job MapReduce example.

**Note:** This example depends on the `tweets-mapreduce` project, so you must first build and install that.

**Note:** You need to set the `XD_HOME` environment variable to point to your XD installation.

Requires that a tweets-hadoop*.txt file is available in HDFS under /twets/input/data dir.

From the XD shell run this:

```
xd:>hadoop config fs --namenode hdfs://borneo:8020
xd:>hadoop fs mkdir /tweets/input
xd:>hadoop fs mkdir /tweets/input/batch
xd:>hadoop fs copyFromLocal --from /Users/trisberg/Projects/Intro-to-Spring-Hadoop/data/hadoop-tweets_2014-08-11.txt --to /tweets/input/data
```

**Note:** Adjust the `--from` path to what you are using.

Build with:

    $ mvn clean package install

Run from XD Shell with:

    xd>job create --name hashtags --definition "tweets-hashtags" --deploy
    xd>job launch --name hashtags


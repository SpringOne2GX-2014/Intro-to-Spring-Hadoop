xd-batch-mapreduce
==================

Spring XD Batch Job MapReduce example.

Requires that a tweets-hadoop*.txt file is available in HDFS under /twets/input/batch dir.

From the XD shell run this:

xd:>hadoop config fs --namenode hdfs://borneo:8020
xd:>hadoop fs mkdir /tweets/input
xd:>hadoop fs mkdir /tweets/input/simple
xd:>hadoop fs copyFromLocal --from /Users/trisberg/Projects/Intro-to-Spring-Hadoop/data/hadoop-tweets_2014-08-11.txt --to /tweets/input/batch

Build with:

$ mvn clean package install

Run from XD Shell with:

xd>job create --name workflow --definition "tweets-workflow" --deploy
xd>job launch --name workflow --params {"local.file":"/Users/trisberg/Test/input/hadoop-tweets_2014-09-02.txt"}

Note: Adjust the path for the input file to what you are using. 
      Remember that the input file will be deleted after it is copied to HDFS.

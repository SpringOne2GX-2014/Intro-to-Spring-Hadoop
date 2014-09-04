simple-workflow
===============

Simple Workflow MapReduce example.

**Note:** This example depends on the `tweets-mapreduce` project, so you must first build and install that.

Copies a tweets-hadoop*.txt file to HDFS under /tweets/input/workflow dir.

Build with:

    $ mvn clean package

Run with:

    $ java -jar ./target/simple-workflow-0.1.0.jar

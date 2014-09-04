xd-batch-workflow
=================

Spring XD Batch Job Workflow example.

**Note:** This example depends on the `tweets-mapreduce` project, so you must first build and install that.

Build with:

    $ mvn clean package install

Run from XD Shell with:

    xd>job create --name workflow --definition "tweets-workflow" --deploy
    xd>job launch --name workflow --params {"local.file":"/Users/trisberg/Test/input/hadoop-tweets_2014-09-02.txt"}

Note: Adjust the path for the `local.file` input file parameter to what you are using. 
      Remember that the input file will be deleted after it is copied to HDFS.

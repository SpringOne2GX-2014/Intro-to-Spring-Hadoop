xd-batch-workflow
=================

Spring XD Batch Job Workflow example.

**Note:** This example depends on the `tweets-mapreduce` project, so you must first build and install that.

**Note:** You need to set the `XD_HOME` environment variable to point to your XD installation.

Build with:

    $ mvn clean package install

Run from XD Shell with:

    xd>job create --name workflow --definition "tweets-workflow" --deploy
    xd>job launch --name workflow --params {"local.file":"/Users/trisberg/Test/input/hadoop-tweets_2014-09-02.txt"}

Alternatively, launch job with a stream looking for files copied to a directory:

    xd>stream create tweetFile --definition "file --ref=true --dir=/Users/trisberg/Test/input --pattern='*.txt' | transform --expression='{\"local.file\":\"'+#{'payload.getAbsolutePath()'}+'\"}' > queue:job:workflow" --deploy


Note: Adjust the path for the `local.file` parameter or `--dir` option to what you are using. 
      Remember that the input file will be deleted after it is copied to HDFS.

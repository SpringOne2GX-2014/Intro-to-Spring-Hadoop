@Grab("org.springframework.data:spring-data-hadoop:2.0.2.RELEASE-hadoop24")

import org.apache.hadoop.fs.FileStatus
import org.springframework.data.hadoop.fs.FsShell

public class Application implements CommandLineRunner {

    @Autowired FsShell fsShell;

    void run(String... strings) throws Exception {
        println "*** HDFS content:" 
        for (FileStatus fs : fsShell.ls("/")) {
          println "${fs.owner} ${fs.group} : /${fs.path?.name}"
        }
    }

    @Bean FsShell fsShell() {
        org.apache.hadoop.conf.Configuration hadoopConfiguration = 
                new org.apache.hadoop.conf.Configuration()
        hadoopConfiguration.set("fs.defaultFS", "hdfs://borneo:8020")
        return new FsShell(hadoopConfiguration);
    }
}

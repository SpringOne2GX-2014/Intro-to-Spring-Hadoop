package com.springdeveloper.hadoop.batch.hive;

import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class HiveInitializerTasklet implements Tasklet, InitializingBean {
	
	private String hiveTableName;
	
	private String dataPath;

	private String createDdl;

	private String dropDdl;

	private JdbcTemplate hive;

	public void setHiveTableName(String hiveTableName) {
		this.hiveTableName = hiveTableName;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.hive = new JdbcTemplate(dataSource);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.state(hiveTableName != null, "The Hive table name must be provided");
		Assert.state(dataPath != null , "The data path must be provided");
		dropDdl = "drop table if exists " + hiveTableName;
		createDdl = "create external table " + hiveTableName + 
				" (value STRING) LOCATION '" + dataPath + "'";
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
			throws Exception {
		hive.execute(dropDdl);
		hive.execute(createDdl);
		return null;
	}

}

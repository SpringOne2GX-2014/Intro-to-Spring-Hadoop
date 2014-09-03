/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springdeveloper.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;

import javax.inject.Inject;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@EnableAutoConfiguration
@Configuration
public class Application implements CommandLineRunner {

	@Inject
	JdbcTemplate hive2;

	@Value("${tweets.input}")
	String input;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	public void run(String... strings) throws Exception {
		System.out.println("Running Hive task using data from '" + input + "' ...");
		String query =
				"select tweets.username, tweets.followers " +
				"from " +
				"  (select distinct " +
				"    get_json_object(t.value, '$.user.screen_name') as username, " +
				"    cast(get_json_object(t.value, '$.user.followers_count') as int) as followers " +
				"    from tweetdata t" +
				"  ) tweets " +
				"order by tweets.followers desc limit 10";
		List<Map<String, Object>> results = hive2.queryForList(query);
		System.out.println("Results: ");
		for (Map<String, Object> r : results) {
			System.out.println(r.get("tweets.username") + " : " + r.get("tweets.followers"));
		}
	}

	@Bean
	DataSourceInitializer hiveInitializer(final DataSource dataSource) {
		final String ddl = "create external table if not exists tweetdata (value STRING) LOCATION '" + input + "'";
		final DataSourceInitializer dsi = new DataSourceInitializer();
	    dsi.setDataSource(dataSource);
		dsi.setDatabasePopulator(new DatabasePopulator() {
			@Override
			public void populate(Connection conn) throws SQLException,
					ScriptException {
				Statement st = conn.createStatement();
				st.execute(ddl);
				st.close();
			}
		});
		return dsi;
	}
	
}
/*
 * Copyright 2013-2014 the original author or authors.
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
package com.springdeveloper.hadoop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


public class TweetCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final static IntWritable ONE = new IntWritable(1);
	private final ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());
	
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		super.setup(context);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		Map<String, Object> tweet = jsonMapper.readValue(value.toString(), 
				new TypeReference<HashMap<String,  Object>>(){});
		Map<String, Object> entities = (Map<String, Object>) tweet.get("entities");
		List<Map<String, Object>> hashTagEntries = null;
		
		if (entities != null) {
			hashTagEntries = (List<Map<String, Object>>) entities.get("hashtags");
		}
		
		if (hashTagEntries != null && hashTagEntries.size() > 0) {
			for (Map<String, Object> hashTagEntry : hashTagEntries) {
				String hashTag = hashTagEntry.get("text").toString();
				context.write(new Text(hashTag), ONE);
			}
		}
	}

}

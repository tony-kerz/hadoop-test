package com.kerz.test.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MortalityReducer extends IntSumReducer<Text>
{
	private static Logger log = LoggerFactory.getLogger(MortalityReducer.class);

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		log.debug("key={}, values={}, context={}", key, values, context);
		super.reduce(key, values, context);
	}
}

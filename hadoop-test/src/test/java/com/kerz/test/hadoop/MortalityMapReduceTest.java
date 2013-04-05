package com.kerz.test.hadoop;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class MortalityMapReduceTest
{
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	//MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp()
	{
		MortalityMapper mapper = new MortalityMapper();
		MortalityReducer reducer = new MortalityReducer();
		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		// mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

	@Test
	public void testMapper()
	{
		mapDriver.withInput(new LongWritable(), new Text("2005,,,1983,09A,B00,1,01,01,36,0,0,0,0,0,0,0,1,1,1,1,0,2,1,0,0,1,4,3,11,5,4,,,1,0,0,0,0"));
		mapDriver.withOutput(new Text("B00"), new IntWritable(36));
		mapDriver.runTest();
	}

	@Test
	public void testReducer()
	{
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text("B00"), values);
		reduceDriver.withOutput(new Text("B00"), new IntWritable(2));
		reduceDriver.runTest();
	}
}

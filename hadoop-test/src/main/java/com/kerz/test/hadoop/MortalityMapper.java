package com.kerz.test.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Class Mapper<KEYIN,VALUEIN,KEYOUT,VALUEOUT>
// record: locality, year, icd9-code, mortality-counts
public class MortalityMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
	private static Logger log = LoggerFactory.getLogger(MortalityMapper.class);

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
	{
		String line = value.toString();
		String tokens[] = line.split(",");
		String cause = tokens[fields.Cause.ordinal()];
		int deaths = Integer.parseInt(tokens[fields.Deaths1.ordinal()]);
		log.debug("cause={}, deaths={}", cause, deaths);
		context.write(new Text(cause),new IntWritable(deaths)) ;   
	}

}

enum fields
{
	Country, Admin1, SubDiv, Year, List, Cause, Sex, Frmat, IM_Frmat, Deaths1, Deaths2, Deaths3, Deaths4, Deaths5, Deaths6, Deaths7, Deaths8, Deaths9, Deaths10, Deaths11, Deaths12, Deaths13, Deaths14, Deaths15, Deaths16, Deaths17, Deaths18, Deaths19, Deaths20, Deaths21, Deaths22, Deaths23, Deaths24, Deaths25, Deaths26, IM_Deaths1, IM_Deaths2, IM_Deaths3, IM_Deaths4
}

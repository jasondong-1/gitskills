package com.ideal.mapreduce;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TagCountReducer extends Reducer<Text, Text, Text, Text> {
	static DecimalFormat   df   =new  DecimalFormat("#.00"); 
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// do something initialize
		super.setup(context);
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		// do something cleanup
		super.cleanup(context);
	}

	protected void reduce(Text key, Iterable<Text> value, Context context)
			throws IOException, InterruptedException {
		Set<String> set = new HashSet<String>();
		double hotvalue = 0.0;
		double sum =0.0;
		for (Text text : value) {
			String[] infos =text.toString().split("\t",-1);
			String ad = infos[0];
			double num = Double.parseDouble(infos[1]);
			set.add(ad);
			sum+=num;
		}
		hotvalue =sum/set.size();
		context.write(key,new Text(set.size()+"\t"+df.format(sum)+"\t"+df.format(hotvalue)));
	
	}
}

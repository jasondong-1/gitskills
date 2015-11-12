package com.ideal.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Main extends Configured implements Tool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Configuration conf = new Configuration();
		int res;
		try {
			res = ToolRunner.run(conf, new Main(), args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			System.out.println("USAGE : -i (input path ) -o  (output path) ");
		}
		Path input = null;
		Path output = null;
		for (int index = 0, L = args.length; index < L; index++) {
			if (args[index].equals("-i")) {
				input = new Path(args[++index]);
			} else if (args[index].equals("-o")) {
				output = new Path(args[++index]);
			}
		}
		if ((null != input) && (null != output)) {
			dump(input, output);

		}
		return 0;
	}

	public void dump(Path input, Path output) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = getConf();
		Job job = new Job(conf);
		job.setJobName("Calculate the numbers of tag and the heatvalue");
		job.setJarByClass(Main.class);
		FileSystem fs = input.getFileSystem(conf);
		fs.delete(output);
		FileStatus status[] = fs.listStatus(input);
		for (FileStatus statu : status) {
			Path path = statu.getPath();
			FileInputFormat.addInputPath(job, path);
		}
		FileOutputFormat.setOutputPath(job, output);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapperClass(TagCountMapper.class);
		job.setReducerClass(TagCountReducer.class);
		job.setNumReduceTasks(20);
		boolean bResult = job.waitForCompletion(true);

	}

}

package com.idea.tag.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ConvertData extends Configured implements Tool{
	/**
	 * 临时工具类
	 * 
	 * */
	private static final Log LOG = LogFactory.getLog(ConvertData.class);
	
	public static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			LOG.info("map任务开始执行。。。。");
		}
		@Override
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] arr = value.toString().split("\\|");
			if(arr.length == 12){//如果字段数不匹配，则过滤掉
				String ad = arr[0].trim();
				String ua = arr[9].trim();
				String uid = Encryption.SHA(ad + ua);
				String result = value.toString() + '|' + uid + '|' + 0;
				context.write(new Text(result),NullWritable.get());
			}
		}
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
//		conf.set("fs.defaultFS", "hdfs://localhost:9000");
		Job job = Job.getInstance(conf,"convert data");
		job.setJarByClass(ConvertData.class);
		job.setMapperClass(MyMapper.class);
		job.setNumReduceTasks(0);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputDirRecursive(job,true);
		FileInputFormat.addInputPath(job,new Path(conf.get("input")));
		HDFSUtil.delete(FileSystem.get(conf),conf.get("output"));//删除输出目录
		FileOutputFormat.setOutputPath(job, new Path(conf.get("output")));
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] arg0) throws Exception {
		String[] arg1 = new String[]{"-Dinput=input","-Doutput=output"};//模擬輸入參數
		String[] arg= ArraysUtil.concat(arg1, arg0);//合并外部参数和内部参数数组  
	    System.exit(ToolRunner.run(new Configuration(), new ConvertData(), arg));
	}
}

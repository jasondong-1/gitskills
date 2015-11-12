package com.idea.tag.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.idea.tag.constant.Constants;
import com.idea.tag.mapreduce.CommonCombiner;
import com.idea.tag.mapreduce.CommonMapper;
import com.idea.tag.mapreduce.CommonReducer;
import com.idea.tag.util.ArraysUtil;
import com.idea.tag.util.HDFSUtil;
import com.idea.tag.util.InitUtil;

public class TagAction extends Configured implements Tool{
	private static final Log LOG = LogFactory.getLog(TagAction.class);

	public int run(String[] args) throws Exception {
		if(true){//处理DPI数据
			int code = runCDPI(args);
			if(code !=0){//异常结束
				return code;
			}
		}
		return 0;
	}
	//处理DPI数据
	private int runCDPI(String[] args) throws Exception {
		LOG.info("标签程序开始处理DPI数据");
		Configuration conf = getConf();
		Job job = Job.getInstance(conf,conf.get(Constants.JOB_NAME.toString()) + " Do CDPI");
		job.setJarByClass(TagAction.class);
		job.setMapperClass(CommonMapper.class);
		job.setCombinerClass(CommonCombiner.class);
		job.setReducerClass(CommonReducer.class);
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setNumReduceTasks(20);
		
		FileInputFormat.setInputDirRecursive(job,true);
		Path input = new Path(conf.get("INPUT_PATH"));
		FileSystem fs = input.getFileSystem(conf);
	    FileStatus[] status = fs.listStatus(input);
	    for (FileStatus statu : status) {
	      Path path = statu.getPath();
	      if(path.toString().endsWith(".tmp")){
	    	  continue;
	      }
	      FileInputFormat.addInputPath(job, path);
	    }
		FileOutputFormat.setOutputPath(job, new Path(conf.get("OUTPUT_PATH")));
		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void main(String[] arg0) throws Exception {
		String[] arg1= new String[]{//模擬輸入參數
//				"-Dmapreduce.map.memory.mb=4096",
//				"-Dmapreduce.map.java.opts=-Xmx4096m",
//				"-Dmapreduce.reduce.memory.mb=4096",
//				"-Dmapreduce.reduce.java.opts=-Xmx4096m",
//				"-Dmapred.reduce.tasks=20",
//				"-DINPUT_PATH=private/tables/gdpi_test",
//				"-DSAVE_UNLABELLED=true",
//				"-DINPUT_PATH=hdfs://127.0.0.1/daas/bstl/dpiqixin/chongqing/20150718",
//				"-DOUTPUT_PATH=hdfs://127.0.0.1/daas/bstl/out/userrecord",
				};
		String[] arg= ArraysUtil.concat(arg1, arg0);//合并外部参数和内部参数数组  
		Configuration conf = new Configuration();
		//初始化配置參數(注意：外部輸入參數（args）會覆蓋配置文件中的相關參數)
		InitUtil.initConfiguration(conf);
	    System.exit(ToolRunner.run(conf, new TagAction(), arg));
	}
}

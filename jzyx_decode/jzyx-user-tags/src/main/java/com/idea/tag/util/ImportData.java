package com.idea.tag.util;//package com.idea.tag.util;
//
//import java.io.IOException;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.conf.Configured;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.compress.CompressionCodec;
//import org.apache.hadoop.io.compress.GzipCodec;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.Tool;
//import org.apache.hadoop.util.ToolRunner;
//
//import com.idea.tag.model.CDPI;
//import com.idea.tag.model.TbGDPI;
///**将gdpi.sada_gdpi_click表中的数据按制定要求格式化并转换输出到u_lx_test.gdpi_test表中*/
//public class ImportData extends Configured implements Tool{
//	private static final Log LOG = LogFactory.getLog(ImportData.class);
//	
//	public static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
//		
//		@Override
//		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
///*			CDPI record = ModelUtil.toCDPI(value.toString(),"\\|");
//			String userAccount = record.getUserAccount();
//			String protocolType = record.getProtocolType();
//			String sourceIp = record.getSourceIp();
//			String destintionIp = record.getDestintionIp();
//			String sourcePort = record.getSourcePort();
//			String destintionPort = record.getDestintionPort();
//			String domainName = Base64Util.base64Encrypt(URLUtil.getDomain(record.getDomainName()));
//			String url = Base64Util.base64Encrypt(record.getUrl());
//			String referer = Base64Util.base64Encrypt(record.getReferer());
//			String userAgent = Base64Util.base64Encrypt(record.getUserAgent());
//			String cookie = Base64Util.base64Encrypt(record.getCookie());
//			String accessTime = record.getAccessTime();
////			String delimter = context.getConfiguration().get("delimiter");//分隔符
//			String delimter = "\t";
//			String result = userAccount + delimter + protocolType + delimter+ sourceIp + delimter + destintionIp
//					+ delimter + sourcePort + delimter + destintionPort + delimter + domainName + delimter
//					+ url + delimter + referer + delimter + userAgent + delimter + cookie + delimter + accessTime;
////			context.write(new Text(), new Text(result));
//			//只輸出key或只輸出value（context.write(new Text(), new Text(result))方式輸出key和value會有分隔符，雖然可以自定義分隔符，但是不能設置爲空字符串）
//			context.write(new Text(result),NullWritable.get());
////			System.out.println(result);
//		}*/
//	}
//	
//	@Override
//	public int run(String[] args) throws Exception {
//		Configuration conf = getConf();
//		conf.set("fs.defaultFS", "hdfs://n1:9000");
//		//我们设定reduce输出结果使用gzip压缩的形式 
//		conf.setBoolean("mapreduce.output.fileoutputformat.compress", true); 
//		conf.setClass("mapreduce.output.fileoutputformat.compress.codec", GzipCodec.class, CompressionCodec.class); 
//		Job job = Job.getInstance(conf,"import data");
//		job.setJarByClass(ImportData.class);
//		job.setMapperClass(MyMapper.class);
////		job.setReducerClass(MyReducer.class);
//		job.setNumReduceTasks(0);
//		job.setOutputKeyClass(Text.class);
//		job.setOutputValueClass(Text.class);
//		FileInputFormat.setInputDirRecursive(job,true);
//		FileInputFormat.addInputPath(job,new Path(conf.get(args[0])));
////		HDFSUtil.delete(FileSystem.get(conf),conf.get(args[1]));//删除输出目录
////		System.out.println(conf.get(args[1]) + "目录已删除!");
//		FileOutputFormat.setOutputPath(job, new Path(conf.get(args[1])));
//		return job.waitForCompletion(true) ? 0 : 1;
//	}
//	
//	public static void main(String[] args) throws Exception {
////		args= new String[]{//"-Dinput=/user/hive/warehouse/lifeng/temp_6.24/","-Doutput=/user/hive/warehouse/lifeng/temp_out/",
////				"-Ddelimiter=\t"};//模擬輸入參數
////		args= new String[]{
////				"-D/user/gdpi/public/sada_gdpi_click.password=GWDPI-SH",
////				"-Dinput=/user/gdpi/public/sada_gdpi_click/20150409/10",
////				"-Doutput=/user/u_lx_test/private/tables/gdpi_test",
////				"-Ddelimiter=\t"
////				};
//	    System.exit(ToolRunner.run(new Configuration(), new ImportData(), args));
//	}
//}

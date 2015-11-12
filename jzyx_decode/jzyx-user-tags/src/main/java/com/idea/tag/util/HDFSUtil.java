package com.idea.tag.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSUtil {
//	private static FileSystem hdfs;
	private static List<Path> paths = new ArrayList<Path>();//用于列表现实子目录文件
//	static{
//		Configuration conf = new Configuration();
//		conf.set("fs.default.name", "hdfs://localhost:9000");
//		conf.set("mapred.job.tracker", "localhost:9001");
//		 try {
//			hdfs = FileSystem.get(conf);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} 
	
	/**以伪分布式运行任务*/

	/* 2015/11/3 注释
	public static void runOnPseudoDistributedMode(Configuration conf){
		conf.set("fs.defaultFS", "hdfs://localhost:9000");
		conf.set("mapreduce.framework.name", "yarn");
		conf.set("mapreduce.jobtracker.address", "localhost:9001");
		conf.set("mapreduce.job.jar",  "./" + Jar.compressToJar("tag.jar", execCmd("pwd") + "/bin", "."));
//		 conf.set("yarn.resourcemanager.hostname", "localhost");
//	     conf.set("yarn.resourcemanager.admin.address", "localhost:8033");
//	     conf.set("yarn.resourcemanager.address", "localhost:8032");
//	     conf.set("yarn.resourcemanager.resource-tracker.address", "localhost:8036");
//	     conf.set("yarn.resourcemanager.scheduler.address", "localhost:8030");
	}
	*/
	/**以本地模式运行任务(使用hdfs文件系统)*/
	public static void runOnLocalMode(Configuration conf){
		conf.set("fs.defaultFS", "hdfs://localhost:9000");
	}

	/**列出指定目录下的文件或目录*/
	public static List<Path> list(FileSystem hdfs,String path){
		try {
	        Path listPath = new Path(path);  
	        FileStatus files[] = hdfs.listStatus(listPath); 
	        if(hdfs.exists(listPath)){
	        	 for(FileStatus file : files){  
	        		 if(file.isDirectory()){
	        			 list(hdfs,file.getPath().toUri().toString());
	        		 }else{
	        			 paths.add(file.getPath());
//	        			 System.out.println(file.getPath().toUri().toString());  
	        		 }
	 	        }   
	        }else{
	        	System.out.println("该目录或文件不存在！");
	        }
//	        hdfs.close();
	        return paths;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paths;
	}
	
//	/**删除指定目录文件*/
//	public static void delete(FileSystem hdfs,String path){
//		try {
//	        Path delPath = new Path(path);  
//	        boolean ok = hdfs.exists(delPath);
//	        if(ok){
//	        	hdfs.delete(delPath,true);//false表示不递归删除，true表示递归删除 
//	        	System.out.println(path + "删除成功");
//	        }else{
//	        	System.out.println("该目录或文件不存在！");
//	        }
////	        boolean ok = hdfs.delete(delPath,true);//false表示不递归删除，true表示递归删除 
////	        System.out.println( ok ? "删除成功" : "删除失败");//待删除的目录不存在会导致删除失败  
////	        hdfs.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	/**删除指定目录文件*/
	public static void delete(FileSystem hdfs,String path){
		try {
	        Path delPath = new Path(path);  
	        boolean ok = hdfs.exists(delPath);
	        if(ok){
	        	hdfs.delete(delPath,true);//false表示不递归删除，true表示递归删除 
	        	System.out.println("输出目录：" + path + "已存在，删除成功");
	        }
//	        boolean ok = hdfs.delete(delPath,true);//false表示不递归删除，true表示递归删除 
//	        System.out.println( ok ? "删除成功" : "删除失败");//待删除的目录不存在会导致删除失败  
//	        hdfs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**重命名文件或目录*/
	public static void rename(FileSystem hdfs,String srcPathStr, String desPathStr){
		try {
	        Path srcPath = new Path(srcPathStr);  
	        Path desPath= new Path(desPathStr); 
	        hdfs.rename(srcPath, desPath);
	        hdfs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**上传文件到HDFS*/
	public static void upload(FileSystem hdfs,String srcPathStr, String desPathStr){
		try {
	        Path srcPath = new Path(srcPathStr);  
	        Path desPath= new Path(desPathStr); 
	        //如果目标路径desPathStr是一个目录会将原文件放入该目录中，如果目标路径不存在则会将原文件上传至值路径并重命名为目标路径最后一级的名字
	        hdfs.copyFromLocalFile(srcPath,desPath);
	        hdfs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		HDFSUtil.delete(args[1]);
//		HDFSUtil.rename("input","input2");
//		HDFSUtil.list("/user/zhu");
//		HDFSUtil.upload("/home/zhu/workspace/switch00/MapReduceJobs/data/test02.txt","/user/zhu/input2");
	}

}

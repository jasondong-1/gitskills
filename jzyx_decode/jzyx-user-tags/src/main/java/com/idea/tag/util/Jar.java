package com.idea.tag.util;

/**工具类，压缩class文件为jar文件*/
public class Jar {

        /**
         * 压缩class文件为jar文件,并返回压缩文件的位置
         * jarName为压缩后文件的名字、srcClassDir为源class文件所在目录、desDir为压缩后的文件的存放位置
         * */
		/* 2015/11/3 注释
	public static String compressToJar(String jarName, String srcClassDir, String desDir){
		execCmd("rm -rf " + desDir + "/" + jarName);
		String cmd = "jar cvf " + jarName + " -C " + srcClassDir + " " + desDir;//压缩至指定目录
//		execCmd(cmd ,"Y");//打印压缩过程输出
		execCmd(cmd ,"N");//不打印压缩过程输出
		System.out.println("压缩完成，文件位置为：" + desDir + "/" + jarName);
		return desDir + "/" + jarName;
//		String projectDir = execCmd("pwd");
//		String srcClassDir = projectDir + "/bin";
//		execCmd("rm -rf " + projectDir + "/" + jarName);
//      String cmd = "jar cvf " + jarName + " -C " + srcClassDir + " .";//压缩至当前目录
//		execCmd(cmd ,"Y");
	}
	*/
	/*
	public static void main(String[] args) {
		String jarName = "wordcount.jar";
		String srcClassDir = execCmd("pwd") + "/bin";
		String desDir = ".";
		Jar.compressToJar(jarName, srcClassDir, desDir);
	}
	*/
}

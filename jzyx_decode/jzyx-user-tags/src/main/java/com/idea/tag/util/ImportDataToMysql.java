package com.idea.tag.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 导标签数据至MySQL数据库
 * */
public class ImportDataToMysql {
	private static int sum = 0;
	private static  String host = "localhost";
	private static  String dbName = "u_lx_test";
	private static 	String userName = "u_lx_test";
	private static  String password = "123456";
	

	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + host + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
			Connection conn = DriverManager.getConnection(url, userName, password);
//			System.out.println(host + "," + userName + "," + password);
		    return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void createTables() throws SQLException{
		//create database test06 default charset utf8 COLLATE utf8_general_ci;
		String sql01 = "DROP TABLE IF EXISTS tb_commonTags;";
		PreparedStatement stmt1 = getConnection().prepareStatement(sql01);
		stmt1.execute();
		String sql02 = "CREATE TABLE tb_commonTags (url varchar(1000),tag varchar(2555))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		PreparedStatement stmt2 = getConnection().prepareStatement(sql02);
		stmt2.execute();
		String sql03 = "DROP TABLE IF EXISTS tb_eBusinessTags;";
		PreparedStatement stmt3 = getConnection().prepareStatement(sql03);
		stmt3.execute();
//		String sql04 ="CREATE TABLE tb_eBusinessTags (itemID varchar(255),tag varchar(2555),detail varchar(2555)) DEFAULT CHARSET=utf8;";
		String sql04 ="CREATE TABLE tb_eBusinessTags (itemID varchar(255),tag varchar(2555),detail varchar(2555))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		PreparedStatement stmt4 = getConnection().prepareStatement(sql04);
		stmt4.execute();
		System.out.println("已有的表中数据已清空");
	}
	
	/**导入CommonTagsLib.txt库至tb_commonTags表中*/
	public static void loadCommonTagsLib() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(ImportDataToMysql.class.getResourceAsStream("/CommonTagsLib.txt"),"UTF-8"));
		String line = null;
		Connection conn = getConnection();
		conn.prepareStatement("TRUNCATE TABLE tb_commonTags").executeUpdate();//清空表tb_commonTags
		String sql = "insert tb_commonTags values(?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		conn.setAutoCommit(false);
		int count=0;
		while((line = br.readLine())!= null){//一次输出文本中的一行内容
			String[] arr = line.split("\t");
			if(line.trim() != "" && arr.length >= 2){//通用标签库内容如（第一列表示表示url，第二列及以后表示标签，多个标签用/t分隔）hkbici.com/thread-566060-1-4.html	比思论坛	资讯,出行,航班
				String url = arr[0];
				String tagsStr = "";
				for(int i=1; i < arr.length; i++){
					if(i==arr.length-1){
						tagsStr += arr[i].trim();
					}else{
						tagsStr += arr[i].trim() + "\t";
					}
				}
//				System.out.println(url + "：" + tagsStr);
				stmt.setString(1, url);
//				stmt.setString(2, URLDecoder.decode(tagsStr, "UTF-8"));
				stmt.setString(2, tagsStr);
//				stmt.executeUpdate();
				stmt.addBatch();
				count++;
				if(count / 1000 == 1){
					stmt.executeBatch();
					conn.commit();
					count = 0;
					System.out.println("提交了第" + ++sum + "批！");
				}
			}
		}
		stmt.executeBatch();//最后不足1000次批量的部分进行提交
		conn.commit();
		br.close();
	}
	
	/**导入EBusinessTagsLib.txt库至tb_eBusinessTags表中*/
	public static void loadEBusinessTagsLib() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(ImportDataToMysql.class.getResourceAsStream("/EBusinessTagsLib.txt"),"UTF-8"));
		String line = null;
		Connection conn = getConnection();
		conn.prepareStatement("TRUNCATE TABLE tb_eBusinessTags").executeUpdate();//清空表tb_eBusinessTags
		String sql = "insert tb_eBusinessTags values(?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		conn.setAutoCommit(false);
		int count=0;
		while((line = br.readLine())!= null){//一次输出文本中的一行内容
			String[] arr = line.split("\t");
			if(line.trim() != "" && arr.length == 4){
				String itemID = arr[1];
				String cateStr = arr[2];
				String detail = arr[3];
//				System.out.println(itemID + "：" + cateStr + "||||" + detail);
				stmt.setString(1, itemID);
				stmt.setString(2, cateStr);
				stmt.setString(3, detail);
//				stmt.executeUpdate();
				stmt.addBatch();
				count++;
				if(count / 1000 == 1){
					stmt.executeBatch();
					conn.commit();
					count = 0;
					System.out.println("提交了第" + ++sum + "批！");
				}
			}
		}
		stmt.executeBatch();//最后不足1000次批量的部分进行提交
		conn.commit();
		br.close();
	}
	
	public static void main(String[] args) throws Exception {
		ImportDataToMysql.host=args[0];
		ImportDataToMysql.dbName=args[1];
		ImportDataToMysql.userName=args[2];
		ImportDataToMysql.password=args[3];
		createTables();
		loadCommonTagsLib();
//		loadEBusinessTagsLib();
	}

}

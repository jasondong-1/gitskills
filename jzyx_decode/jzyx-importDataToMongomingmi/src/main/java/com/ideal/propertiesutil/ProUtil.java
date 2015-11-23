package com.ideal.propertiesutil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
/**
 * 获取配置文件
 * @author Administrator
 *
 */
public class ProUtil {
	public static Properties getConf(){
		Properties conf=null;
		try{
			conf=new Properties();
			InputStream in=ProUtil.class.getClassLoader().getResourceAsStream("conf.properties");
			conf.load(in);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conf;
	}
}

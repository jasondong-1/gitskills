package com.idea.tag.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Logger;

import com.idea.tag.constant.Constants;

public class InitUtil {
	static Logger logger = Logger.getLogger(InitUtil.class);
	private static Set<String> fields;
	private static Properties prop;

	static {
		fields = new HashSet<String>();
		Class<Constants> cons = Constants.class;
		Field[] arr = cons.getFields();// 获取Constants类中所有的属性
		for (Field field : arr) {
			fields.add(field.getName());
		}
	}

	static {
		prop = new Properties();
		InputStream in = InitUtil.class.getResourceAsStream("/conf.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**初始化配置參數（从conf.properties中读取并在conf中进行绑定）*/
	public static void initConfiguration(Configuration conf) {
		for (String field : fields) {
//			String value = prop.getProperty(field.toLowerCase());
			String value = prop.getProperty(field);
//--------------------------------------------------------			
			logger.info("conf.properties中field&value:" + field +"***"+value);
			System.out.println("conf.properties中field&value:" + field +"***"+value);
			if(value != null){
				conf.set(field, value);
//				System.out.println(value);
			}else{
			//logger.error(field.toLowerCase() + "不能爲空值，請檢查conf.properties配置文件！！");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Properties prop = new Properties();
		// InputStream in =
		// Object.class.getResourceAsStream("/conf.properties");
		// prop.load(in);
		// String jobName =
		// prop.getProperty(Constants.JOB_NAME.toString().toLowerCase()).trim();
		// System.out.println(jobName);
	}

}

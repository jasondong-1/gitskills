package com.ideal.mongoUtil;

import java.io.FileInputStream;
import java.util.Properties;

import com.ideal.propertiesutil.ProUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoUtil {
	private static Properties conf;
	//��ȡ������
	private static String host;
	//��ȡ�˿�
	private static String port;
	
	static {
		try {
			conf = ProUtil.getConf();
			host = conf.getProperty("host");
			port=conf.getProperty("port");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡMongo����
	 * @return
	 * @throws Exception
	 */
	public static Mongo getMongo()throws Exception{
		Mongo mongo=null;
		try{
			int p=Integer.valueOf(port);
			mongo=new Mongo(host,p);

		}catch(Exception e){
			e.printStackTrace();
		}
		return mongo;
	}
	/**
	 * �ر�mongo
	 * @param mongo
	 */
	public static void closeMongo(Mongo mongo){
		if(mongo!=null)
		mongo.close();
	}
}

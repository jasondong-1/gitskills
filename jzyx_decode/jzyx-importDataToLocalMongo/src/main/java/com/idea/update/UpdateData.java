package com.idea.update;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import com.ideal.mongoUtil.MongoUtil;
import com.ideal.mongoUtil.TagInfo;
import com.ideal.propertiesutil.ProUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;


/**
 * ����mongo���ݿ�
 * @author Administrator
 *
 */
public class UpdateData {
	//���ݿ���
	private static String database;
	//������
	private static String collection;
	//������ļ�������·����
	private static String fileTop10000;
	//��ȡ�����ļ�
	private static Properties conf;
	//
	private static String filenameOut;
	
	//private static Log log=LogFactory.getLog(UpdateData.class);
	static{
		try{
			conf=ProUtil.getConf();
			database=conf.getProperty("database");
			collection=conf.getProperty("collection");
			fileTop10000=conf.getProperty("fileTop10000");
			filenameOut=conf.getProperty("filenameOut");
		}catch(Exception e){
			//log.error("properties ����ʧ��"+e);
			e.printStackTrace();
		}
	}
	public static void update(String city,String date){
		Mongo mongo=null;
		try{
			mongo=MongoUtil.getMongo();
			DB db=mongo.getDB(database);
			DBCollection coll=db.getCollection(collection);
			String sep=File.separator;
			String inpath=fileTop10000+sep+city+sep+date+sep+filenameOut;
			BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(inpath)));
			String info=null;
			while((info=in.readLine())!=null){
				String[] mes=info.split("\t");
				String tag=mes[0];
				String ads=mes[1];
				String[] taginfos=ads.split(",",-1);
				List<TagInfo> lists=new ArrayList<TagInfo>();
				for(String s:taginfos){
					TagInfo ti=new TagInfo();
					String[] s1=s.split(":",-1);
					if(s1.length!=2){
						continue;
					}else{
						ti.setNo(s1[1]);
						String adMeid=s1[0];
						String[] adMeidArr=adMeid.split("\\+",-1);
						ti.setAd(adMeidArr[0]);
						if(adMeidArr.length==2){

							ti.setMeid(adMeidArr[1]);
						}else{
							ti.setMeid("");
						}
					}
					lists.add(ti);
				}
				DBObject find=new BasicDBObject();
				DBObject update=new BasicDBObject();
				DBObject upsert=new BasicDBObject();
				find.put("city", city);
				find.put("tag", tag);
				update.put("city", city);
				update.put("tag", tag);
				update.put("ads", ads);
				upsert.put("$set", update);
				//���¼��ϣ������ҵ�find������£��������
				coll.update(find, upsert,true,true);
				lists=null;
			}
			System.out.println("over");
		}catch(Exception e){
			//log.error("���ݿ�����ʧ��"+e);
			e.printStackTrace();
		}finally{
			MongoUtil.closeMongo(mongo);
		}
	}


}

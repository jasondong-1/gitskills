package ocm.ideal.top10000;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Properties;

import com.ideal.propertiesutil.ProUtil;
/**
 * 对文件进行处理，获得top10000 ad+权重
 * @author Administrator
 *
 */
public class Top10000 {
	//获取配置文件
	private static Properties conf;
	//未取到top10000 ad的文件路径（不含城市，日期）
	private static String fileNotHandle;
	//获得top10000 ad  文件路径
	private static String fileTop10000;
	//要求的top数
	private static String top;
	//读取的文件名
	private static String filenameIn;
	//要写入的文件名
	private static String filenameOut;
	//读入编码格式
	private static String incode;
	//写出编码格式
	private static String outcode;
	static{
		try{
			conf=ProUtil.getConf();
			fileNotHandle=conf.getProperty("fileNotHandle");
			fileTop10000=conf.getProperty("fileTop10000");
			top=conf.getProperty("top");
			filenameIn=conf.getProperty("filenameIn");
			filenameOut=conf.getProperty("filenameOut");
			incode=conf.getProperty("incode");
			outcode=conf.getProperty("outcode");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void top10000(String city,String date){
		//定义top10000
				int no=Integer.valueOf(top);
				//获取输入流
				BufferedReader in = null;
				//获取输出流
				PrintWriter out = null;
				try {
					//获取分隔符
					String sep=File.separator;
					//获取输入路径
					String inPath=fileNotHandle+sep+city+sep+date+sep+filenameIn;
					//获取输出路径
					String outPath=fileTop10000+sep+city+sep+date+sep+filenameOut;
					in = new BufferedReader(new InputStreamReader(new FileInputStream(inPath), incode));
					out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outPath), outcode), true);
					String mes=null;
					//循环读取输入流内容
					while((mes=in.readLine())!=null){
						//将读入的每行分割
						String[] info=mes.split("\t");
						//获取tag
						String tag=info[0];
						//获取所有ad:权重值
						String ad=info[1];
						//获取每个用户ad和权重
						String[] ads=ad.split(","); 
						
						int len=ads.length-1;
						StringBuilder buffer=new StringBuilder();
						for(int i=0;i<no;i++){
							if(i>len){
								break;
							}
							buffer.append(ads[i]+",");
						}
						int index=buffer.lastIndexOf(",");
						buffer.delete(index, index+1);
						out.println(tag+"\t"+buffer.toString());
						
					}
					System.out.println("ok");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (in != null)
							in.close();
						if(out!=null)
							out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	}
}

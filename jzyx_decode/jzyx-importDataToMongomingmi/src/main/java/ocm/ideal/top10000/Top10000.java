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
 * ���ļ����д������top10000 ad+Ȩ��
 * @author Administrator
 *
 */
public class Top10000 {
	//��ȡ�����ļ�
	private static Properties conf;
	//δȡ��top10000 ad���ļ�·�����������У����ڣ�
	private static String fileNotHandle;
	//���top10000 ad  �ļ�·��
	private static String fileTop10000;
	//Ҫ���top��
	private static String top;
	//��ȡ���ļ���
	private static String filenameIn;
	//Ҫд����ļ���
	private static String filenameOut;
	//��������ʽ
	private static String incode;
	//д�������ʽ
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
	public static void top10000(String inputpath,String outputpath){
		//����top10000
				int no=Integer.valueOf(top);
				//��ȡ������
				BufferedReader in = null;
				//��ȡ�����
				PrintWriter out = null;
				try {
					//��ȡ�ָ���
					String sep=File.separator;
					//��ȡ����·��
					//String inPath=fileNotHandle+sep+city+sep+date+sep+filenameIn;
					//��ȡ���·��
					//String outPath=fileTop10000+sep+city+sep+date+sep+filenameOut;
					in = new BufferedReader(new InputStreamReader(new FileInputStream(inputpath), incode));
					out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(outputpath), outcode), true);
					String mes=null;
					//ѭ����ȡ����������
					while((mes=in.readLine())!=null){
						//�������ÿ�зָ�
						String[] info=mes.split("\t");
						//��ȡtag
						String tag=info[0];
						//��ȡ����ad:Ȩ��ֵ
						String ad=info[1];
						//��ȡÿ���û�ad��Ȩ��
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

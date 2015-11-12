package com.idea.tag.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;


public class FileUtil {

	//加载通用标签数据
		public static Map<String,Set<String>>loadCommonTagsLib(String filePath,String delimiter,String coding){
			Map<String,Set<String>>map = new HashMap<String,Set<String>>();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream("/" + filePath),coding));
//				BufferedReader br = new BufferedReader(new FileReader(filePath));
				String line = null;
				while((line = br.readLine())!= null){//一次输出文本中的一行内容
					String[] arr = line.split(delimiter);
					if(line.trim() != "" && arr.length >= 2){//通用标签库内容如（第一列表示表示url，第二列及以后表示标签，多个标签用/t分隔）hkbici.com/thread-566060-1-4.html	比思论坛	资讯,出行,航班
						String url = arr[0];
						Set<String> tags = new HashSet<String>();
//						String tagsStr = "";
						for(int i=1; i < arr.length; i++){
//							if(i==arr.length-1){
//								tagsStr += arr[i].trim();
//							}else{
//								tagsStr += arr[i].trim() + "\t";
//							}
							tags.add(arr[i].trim().replaceAll("&", "").replaceAll(":", "").replaceAll("<", "").replaceAll(">", ""));//替换标签中的"&"字符（solr中导入的字符串或文本中不能含有"&"字符）
						}
						map.put(url,tags);
//						System.out.println(url + "：" + tags);
					}
				}
				br.close();
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	//加载电商标签数据
	public static Map<String,String>loadEBusinessTagsLib(String filePath,String delimiter,String coding){
		Map<String,String>map = new HashMap<String,String>();
		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream(filePath),coding));
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = null;
			while((line = br.readLine())!= null){//一次输出文本中的一行内容
				String[] arr = line.split(delimiter);
				if(line.trim() != "" && arr.length == 4){//电商标签库内容如（第三列表示标签，并且只有一个标签）：http://item.jd.com/1011878664.html	JD1011878664	食品饮料,休闲食品,休闲零食,久久丫,久久丫精装麻辣小方干260g
//					map.put(arr[1],arr[2].replaceAll("&", "").replaceAll(":", ""));//替换标签中的"&"字符（solr中导入的字符串或文本中不能含有"&"字符）
					map.put(arr[1],arr[2].replaceAll("&", "").replaceAll(":", "") + "," + arr[3].replaceAll("&", "").replaceAll(":", ""));//将分类和商品详情合并作为标签
//					System.out.println(line);
				}
			}
			br.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
//		Map<String,String>map = loadEBusinessTagsLib("/EBusinessTagsLib.txt","\t","UTF-8");
//		for(String key: map.keySet()){  
//			System.out.println(key + "：" + map.get(key));  
//       }  
//		Map<String,String>map2 = loadCommonTagsLib("/CommonTagsLib.txt","\t","UTF-8");
//		for(String key: map2.keySet()){  
//			System.out.println(key + "：" + map2.get(key));  
//       }  
	}

	/**
	 * 加载 标签 关键词 隐射文件
	 * @param filePath
	 * @param delimiter
	 * @param coding
	 * @param mapperSize
	 * @return
	 */
	public static List<Map<String,String[]>> loadTagKeyWordMapper(String filePath,String delimiter,String coding,int mapperSize){
		List<Map<String,String[]>> mapperList=new ArrayList<Map<String, String[]>>(5);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream("/" + filePath),coding));
			String line = null;
			int index = 0;
			Map<String,String[]> mapper=null;
			while((line = br.readLine())!= null){//一次输出文本中的一行内容
				String[] arr = line.split(delimiter);
				if(line.trim() != "" && arr.length >= 2){//第一列 tag 第二列 关键词
					String tag = arr[0];
					String keyword = arr[1];
					if(index%mapperSize==0){
						mapper = new HashMap<String,String[]>();
						mapperList.add(mapper);
					}
					mapper.put(tag,keyword.split(","));
					index++;
				}
			}
			br.close();
			return mapperList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}

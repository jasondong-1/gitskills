package com.idea.tag.process;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.idea.tag.model.MUrlTag;
import com.idea.tag.util.FileUtil;
import com.idea.tag.util.URLUtil;
/**
 * 打标签程序
 * */
public class TagProcess {
	private static final Log LOG = LogFactory.getLog(TagProcess.class);
	private static Map<String,Set<String>> commonTagsLib;//通用标签库
//	private static Map<String,String> eBusinessTagsLib;//电商标签库
	private static Set<String> commonTagsSet;//url集和
//	private static Set<String> eBusinessTagsSet;//itemID集和
	public static List<Map<String,String[]>> mapperList;

	static{
		//加载标签库
		LOG.info("开始加载标签数据...");
		commonTagsLib = FileUtil.loadCommonTagsLib("CommonTagsLib.txt","\t","UTF-8");//通用标签库
//		commonTagsLib = FileUtil.loadCommonTagsLib("/home/hadoop/Desktop/ideal_tag/script/CommonTagsLib.txt","\t","UTF-8");//通用标签库
//		eBusinessTagsLib = FileUtil.loadEBusinessTagsLib("EBusinessTagsLib.txt","\t","UTF-8");//电商标签库
		commonTagsSet = commonTagsLib.keySet();
//		eBusinessTagsSet = eBusinessTagsLib.keySet();
		mapperList=FileUtil.loadTagKeyWordMapper("mapperFile.text","\t","UTF-8",200);
		LOG.info("标签数据加载完成...");
	}
	
	/**具体打标签过程*/
	public static MUrlTag tag(String url_){
		String url = URLUtil.removeHeader(url_);//去掉url开头部分的http://www.、https://www.、www.内容
		String host = URLUtil.getHost(url);//获得url对应的主机名
		String domain = URLUtil.getDomain(host);//获得url对应的域名
		MUrlTag urlTag = new MUrlTag();
		Set<String> urlTagsSet =  new HashSet<String>();//url标签 Set集和
		//给域名打标签
		if(commonTagsSet.contains(domain)){
			urlTagsSet.addAll(commonTagsLib.get(domain));
		}
		//给主机名打标签
		if(commonTagsSet.contains(host)){
			urlTagsSet.addAll(commonTagsLib.get(host));
		}
		//给完整url打标签
//		for(String url_ : commonTagsSet){
//			if(url.startsWith(url_)){
//				urlTagsSet.addAll(commonTagsLib.get(url_));
//			}
//		}
		if(commonTagsSet.contains(url)){
			urlTagsSet.addAll(commonTagsLib.get(url));
		}
//--------
		/**		
		//电商部分标签处理
		String itemID = null;
		try{
			itemID = ItemId.evaluate(url_);
		}catch (StringIndexOutOfBoundsException e){
			LOG.error("StringIndexOutOfBoundsException异常！,URL为：" + url_, e);
			itemID = null;
		}
		//判斷是否爲電商數據（电商数据获得的itemID值为非""字符串）
		if(itemID != null && itemID.length() != 0){//是電商數據
			if(eBusinessTagsSet.contains(itemID)){
//				System.out.println(eBusinessTagsLib.get(itemID)); 
				urlTagsSet.add(eBusinessTagsLib.get(itemID));
			}
		}
	*/	
		if(urlTagsSet.isEmpty()){
			//通过关键词查找tag
			new KeywordMapperProcess().find(url_,urlTagsSet,TagProcess.mapperList);
		}
		//		通过以上过程处理后如果该url存在标签，则把标志位设置为true,并把urlTagsSetset集和的值循环赋给urlTagsStr字段
		if(!urlTagsSet.isEmpty()){
			urlTag.setUrlHasTag(true);
			String tagStr = "";
			for(String tag : urlTagsSet){
				tagStr += tag + "\t";
			}
			tagStr = tagStr.substring(0, tagStr.length()-1);//去掉最后一个多余字符(\t)
			urlTag.setUrlTagsStr(tagStr);
		}
		urlTag.setUrlTagsSet(urlTagsSet);
		return urlTag;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

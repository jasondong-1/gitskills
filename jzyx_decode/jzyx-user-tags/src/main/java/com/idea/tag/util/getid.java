package com.idea.tag.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

/**
 * 
 * @brief Hive UDF：从特定搜索网站根据url提取关键字
 * 
 * @author haoming@126.com
 */

public class getid {
	
	
	//使用此正则表达式会有GBK/UTF-8识别不准的情况
	//protected static String encodeReg = "^(?:[\\x00-\\x7f]|[\\xfc-\\xff][\\x80-\\xbf]{5}|[\\xf8-\\xfb][\\x80-\\xbf]{4}|[\\xf0-\\xf7][\\x80-\\xbf]{3}|[\\xe0-\\xef][\\x80-\\xbf]{2}|[\\xc0-\\xdf][\\x80-\\xbf])+$";
	
	//在不考虑一些如拉丁、希腊等外文的情况下，可以使用此正则表达式
	protected static String encodeReg = "^(?:[\\x00-\\x7f]|[\\xe0-\\xef][\\x80-\\xbf]{2})+$";
	
	protected static HashMap<String, String> sef= new HashMap<String, String>();	//Search Engine Features

/**
 * 	在UDFKeyword类中定义
 * 	正则匹配库 定义于 2013-08-20
 *  正则匹配库 更新于 2013-11-26
 */
	static{
		sef.put("item.jd.com","(/|//)");
		sef.put("product.suning.com","(/|//|detail_[0123456789]{1,10}_)");
		sef.put("detail.tmall.com","(item|farseer).*(\\?|\\&|\\;)id=");
		sef.put("item.tmall.com","(item|farseer).*(\\?|\\&)id=");
		sef.put("item.taobao.com","(item|farseer).*(\\?|\\&|\\;)id=");
		sef.put("detail.taobao.com","(item|farseer).*(\\?|\\&)id=");
		sef.put("item.gome.com.cn","/");
		sef.put("www.gome.com.cn","/product/");
		sef.put("item.yhd.com","/item/");
		sef.put("www.amazon.cn","product/");
		sef.put("item.yixun.com","/item-");
		sef.put("product.dangdang.com","(/|//)");
		sef.put("www.vip.com","detail-[0123456789]{0,10}-");
		sef.put("www.tiantian.com","cosmetic/");
		sef.put("www.feiniu.com","item/");
		sef.put("item.muyingzhijia.com","(/|//)");
		sef.put("pop.jumei.com","deal.*df[0123456789]{0,10}p");
		sef.put("mall.jumei.com","product_");
		sef.put("sh.womai.com","product-[0123456789]{0,10}-");
		sef.put("www.gou.com","product_");
		sef.put("www.m6go.com","product_");
		sef.put("nj.baby.com.cn","product-");
		sef.put("product.lefeng.com","(/|//)");
		sef.put("www.111.com.cn","product/");
		sef.put("item.blemall.com","(/|//)");
		sef.put("www.sfbest.com","products/[0123456789]{0,10}/");
		sef.put("www.yummy77.com","product/");
		sef.put("www.jiuxian.com","goods-");
		sef.put("www.yesmywine.com","goods/");
		sef.put("www.u1baby.com","goods-");

		//加购
		sef.put("cart.jd.com","addtocart.*pid=");
		sef.put("cart.yhd.com","add.*productid=");
		sef.put("fbuy.tmall.com","cart.*itemid%22%3a%22");
		sef.put("www.yhd.com","add.*productid=");
		sef.put("unit.fbuy.tmall.com","cart.*itemid%22%3a%22");

	}
	public static Boolean isSearchEngine(String host){
		return sef.containsKey(host);
	}
	

	public static String getPattern(String host){
		return "^.*"+host+".*"+sef.get(host);
	}
	

	public static Boolean isUTF8(String string){
		Pattern encode_pattern = Pattern.compile(encodeReg);
		String unescaped_string = unescape(string);
		Matcher encode_matcher = encode_pattern.matcher(unescaped_string);
		if (encode_matcher.matches()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isGBK(String string) throws UnsupportedEncodingException {
	    if(string.equals(new String(string.getBytes("GBK"))))
	        return true;
	    else
	        return false;
	}
	
	public static boolean isFormed(String keyword_encoded) {
		return keyword_encoded.matches("^(%25|%[^%]{2}|[^%]+)+$");
	}
	
	public static String filter(String keyword) {
		String keyword_f="";
		try {		
			if(isUTF8(keyword)){
				keyword = URLDecoder.decode(keyword, "UTF-8");
			}else if(isGBK(keyword)){
				keyword = URLDecoder.decode(keyword, "GBK");
			}
		} catch (UnsupportedEncodingException e) {
			return "";
		} catch (IllegalArgumentException e2) {
			return "";
		} catch (StringIndexOutOfBoundsException e3){
			return "";
		}
		
		if(keyword.contains("http://") || keyword.contains("https://")) {
			return "";
		}
		//替换敏感字符
		keyword_f=keyword.replaceAll("\r\n|\n\r|\r|\n|\t|\\x01", " ");
		//检查是否有非法字符
		
		return keyword_f;
	}


	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	

	public final static Text evaluate(Text url_text){
	
		if (null==url_text) {
			return new Text();
		}
		
		String url=url_text.toString().toLowerCase();
		String host=UDFHost.getHost(url);
		String domain=UDFDomain.getDomain(host);
		String url_pattern=null;
		String keyword_encoded=null;
		String keyword=null;
		String keyword_f="";
		
		if(!isSearchEngine(host)) {
				return new Text();
		}else{
			url_pattern = getPattern(host);
		}
			
		Pattern keywords_pattern = Pattern.compile(url_pattern);
		Matcher keywords_matcher = keywords_pattern.matcher(url);
		
		StringBuffer url_sb= new StringBuffer();
		StringBuffer keywords_sb= new StringBuffer();
		if (keywords_matcher.find()) {
			keywords_matcher.appendReplacement(url_sb, "");
			keywords_matcher.appendTail(url_sb);
			//过滤&之后的数据
			Pattern filter_pattern = Pattern.compile("(\\.(html|htm|shtml).*|(\\%|&|\\_|/).*|\\-.*html.*|\\?.*|/ref.*|/(C|c).*)$");
			Matcher filter_matcher = filter_pattern.matcher(url_sb.toString());
			if (filter_matcher.find()) {
				filter_matcher.appendReplacement(keywords_sb,"");
				filter_matcher.appendTail(keywords_sb);
				keyword_encoded=keywords_sb.toString();	//%CC%EC%C6%F8+%CE%E4%BA%BA
			}else{
				keyword_encoded=url_sb.toString();
			}
			//过滤不完整的编码信息，即%后的字符不为两个的情况。存在于系统截取过长的url
//			if(!isFormed(keyword_encoded)){
//				return new Text();
//			}
			//解码
			try {
				
				if(isUTF8(keyword_encoded)){
					keyword = URLDecoder.decode(keyword_encoded, "UTF-8");
				}else if(isGBK(keyword_encoded)){
					keyword = URLDecoder.decode(keyword_encoded, "GBK");
				}else{
					return new Text();
				}
				//过滤操作
				keyword_f=filter(keyword);
				
			} catch (UnsupportedEncodingException e) {
				return new Text();
			} catch (IllegalArgumentException e2) {
				//System.out.println(e2);
				return new Text();
			} catch (StringIndexOutOfBoundsException e3){
				return new Text();
			}
			
			keyword_f = keyword_f.replaceAll("/", "");
			if(domain.contains("jd.com")){
				keyword_f = "JD"+keyword_f;
			}else if(domain.contains("suning.com")){
				keyword_f = "SUNING"+keyword_f;
			}else if(domain.contains("tmall.com")){
				keyword_f = "TMALL"+keyword_f;
			}else if(domain.contains("taobao.com")){
				keyword_f = "TAOBAO"+keyword_f;
			}else if(domain.contains("gome.com.cn")){
				keyword_f = "GOME"+keyword_f;
			}else if(domain.contains("yhd.com")){
				keyword_f = keyword_f.replace("_1", "");
				keyword_f = "YHD"+keyword_f;
			}else if(domain.contains("amazon.cn")){
				keyword_f = "AMAZON"+keyword_f;
			}else if(domain.contains("yixun.com")){
				keyword_f = "YX"+keyword_f;
			}else if(domain.contains("dangdang.com")){
				keyword_f = "DD"+keyword_f;
			}else if(domain.contains("vip.com")){
				keyword_f = "VIP"+keyword_f;
			}else if(domain.contains("tiantian.com")){
				keyword_f = "TianTian"+keyword_f;
			}else if(domain.contains("feiniu.com")){
				keyword_f = "FeiNiu"+keyword_f;
			}else if(domain.contains("muyingzhijia.com")){
				keyword_f = "MYZJ"+keyword_f;
			}else if(domain.contains("jumei.com")){
				keyword_f = "JUMEI"+keyword_f;
			}else if(domain.contains("womai.com")){
				keyword_f = "WMW"+keyword_f;
			}else if(domain.contains("gou.com")){
				keyword_f = "MLG"+keyword_f;
			}else if(domain.contains("m6go.com")){
				keyword_f = "MLG"+keyword_f;
			}else if(domain.contains("baby.com.cn")){
				keyword_f = "AYD"+keyword_f;
			}else if(domain.contains("lefeng.com")){
				keyword_f = "LF"+keyword_f;
			}else if(domain.contains("111.com.cn")){
				keyword_f = "YYW"+keyword_f;
			}else if(domain.contains("blemall.com")){
				keyword_f = "BLW"+keyword_f;
			}else if(domain.contains("sfbest.com")){
				keyword_f = "SF"+keyword_f;
			}else if(domain.contains("yummy77.com")){
				keyword_f = "MW"+keyword_f;
			}else if(domain.contains("jiuxian.com")){
				keyword_f = "JXW"+keyword_f;
			}else if(domain.contains("yesmywine.com")){
				keyword_f = "YMJ"+keyword_f;
			}else if(domain.contains("u1baby.com")){
				keyword_f = "YYBB"+keyword_f;
			}
		}
//		System.out.println(keyword_f);
		return new Text(keyword_f);
	}
	public static void main(String[] args) throws Exception{
		System.out.println(evaluate(new Text("http://item.jd.com/1310003889.html")));
		System.out.println("结果为：" + evaluate(new Text("http://item.m.jd.com/product/1324600.html")));
//		getid ii = new getid();
//		BufferedReader read = new BufferedReader(new FileReader(new File("E://keyword.txt")));
//		String line = "";
//		while((line = read.readLine()) != null){
//			System.out.println(ii.evaluate(new Text(line)));
//		}
	//	BufferedWriter wr = new BufferedWriter(new FileWriter(new File("E://333333.txt")));
		
	}
}

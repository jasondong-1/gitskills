package com.idea.tag.util;

import org.apache.hadoop.io.Text;

/**
 * @author haoming@126.com
 */

public class UDFHost {
	
	public static String getHost(String url){
		String host = "";
		url=url.toLowerCase();
		
		try {
			if (url.startsWith("https://")) {
				url = url.replaceFirst("https://", "");
			} else if (url.startsWith("http://")) {
				url = url.replaceFirst("http://", "");	//	upload.189qas.com/statisGether/androidGzipGether.html
			}
			int qindex = url.indexOf("?");
			if(qindex >= 0) {
				url = url.substring(0,qindex);
			}
			int sindex = url.indexOf("/");	//16
			if(sindex >= 0) {
				url = url.substring(0, sindex);	//	upload.189qas.com

			}
			int cindex = url.indexOf(":");
			if(cindex >= 0){
				host = url.substring(0, cindex);
			}else {
				host = url;
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return host;
	}
	
	public Text evaluate(Text url_text) {
		
		if (null==url_text) {
			return new Text();
		}

		return new Text(getHost(url_text.toString()));
	}
}

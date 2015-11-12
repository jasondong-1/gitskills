package com.idea.tag.filter;

import com.idea.tag.model.CDPI;
import com.idea.tag.model.MNoise;
import com.idea.tag.model.TbGDPI;
/**
 *去噪处理类
 * */
public class Denoising {
	private final static String JS_FORMATS = "css,js"; // 11
	private final static String SWF_FORMATS = "swf"; // 12
	private final static String SOUND_FORMATS = "mp3,wav,wma,flac,m4a,m3u,m3u8"; // 13
	private final static String VIDEO_FORMATS = "mp4,mpe,mpeg,asf,wmv,wmf,m2v,vob,avi,flv,mpg,rm,mov,3gp,mkv,rmvb"; // 14
	private final static String GRAPH_FORMATS = "ico,bmp,jpg,jpeg,tiff,gif,png,tga,exif,fpx,svg,psd,cdr,pod,dxf,ufo,eps,ai,raw,hdr,tif";

	/**过滤GDPI固网噪声数据*/
	public static MNoise filterGDPI(TbGDPI pojo){
		MNoise noise = new MNoise();
		String url = pojo.getUrl();
		
		if (isEmpty(pojo.getUserAccount()) || "none".equalsIgnoreCase((pojo.getUserAccount()))) {
			noise.setNoise(true);
			noise.setReason("empty useraccount");
			return noise;
		}
		if (isEmpty(url)) {
			noise.setNoise(true);
			noise.setReason("empty url");
			return noise;
		}
		if ("http://".equals(url) || "https://".equals(url)) {
			noise.setNoise(true);
			noise.setReason("empty url");
			return noise;
		}

		if (isURLTypeLegal(url, JS_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("js or css detected");
			return noise;
		}

		if (isURLTypeLegal(url, SWF_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("swf noised detected");
			return noise;
		}

		if (isURLTypeLegal(url, SOUND_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("sound noise detected");
			return noise;
		}

		if (isURLTypeLegal(url, VIDEO_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("video noise fdetected");
			return noise;
		}

		if (isURLTypeLegal(url, GRAPH_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("image noise detected");
			return noise;
		}

		if (isEmpty(pojo.getDestintionIp())) {
			noise.setNoise(true);
			noise.setReason("empty destinationIP");
			return noise;
		}

		if (isEmpty(pojo.getSourceIp())) {
			noise.setNoise(true);
			noise.setReason("empty srcIP");
			return noise;
		}
		return noise;
	}

	/**过滤CDPI C网噪声数据*/
	public static MNoise filterCDPI(CDPI pojo){
		MNoise noise = new MNoise();
		String url = pojo.getUrl();
		
		if (isEmpty(pojo.getMdn())) {
			noise.setNoise(true);
			noise.setReason("empty ad");
			return noise;
		}
		if (isEmpty(url)) {
			noise.setNoise(true);
			noise.setReason("empty url");
			return noise;
		}
		if ("http://".equals(url) || "https://".equals(url)) {
			noise.setNoise(true);
			noise.setReason("empty url");
			return noise;
		}

		if (isURLTypeLegal(url, JS_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("js or css detected");
			return noise;
		}

		if (isURLTypeLegal(url, SWF_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("swf noised detected");
			return noise;
		}

		if (isURLTypeLegal(url, SOUND_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("sound noise detected");
			return noise;
		}

		if (isURLTypeLegal(url, VIDEO_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("video noise fdetected");
			return noise;
		}

		if (isURLTypeLegal(url, GRAPH_FORMATS)) {
			noise.setNoise(true);
			noise.setReason("image noise detected");
			return noise;
		}
		return noise;
	}
	
	private static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	/**
	 * 判断url是否是图片、视频或js
	 * 
	 * @param urlstr
	 *            input url string
	 * @return true or false
	 */
	private static boolean isURLTypeLegal(String urlstr, String format_set) {
		String urlstr_lowcase = urlstr.toLowerCase();
		String[] patternstrs = format_set.split(",");
		for (String pattern : patternstrs) {
			if (urlstr_lowcase.endsWith("." + pattern) || (0 <= urlstr_lowcase.indexOf("." + pattern + "?")) || (0 <= urlstr_lowcase.indexOf("." + pattern + "&"))) {
				return true;
			}
		}
		return false;
	}
	
}

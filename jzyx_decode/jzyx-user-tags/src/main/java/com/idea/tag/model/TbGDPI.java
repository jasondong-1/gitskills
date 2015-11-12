package com.idea.tag.model;

import java.util.HashSet;
import java.util.Set;

//用户上网有效点击数据表（来自gdpi.sada_gdpi_click）
public class TbGDPI {
	private String userAccount;// 用户账号
	private String protocolType;// 协议类型
	private String sourceIp;// 源Ip
	private String destintionIp;// 目标ip
	private String sourcePort;// 源端口（十六进制整形表示）
	private String destintionPort;// 目标端口（十六进制整形表示）
	private String domainName;// 域名（Base64編碼數據）
	private String url;// （Base64編碼數據）
	private String referer;// 链接源信息（Base64編碼數據）
	private String userAgent;// （Base64編碼數據）
	private String cookie;// 用户Cookie（Base64編碼數據）
	private String accessTime;// 访问时间（UTC格式）

	/*
	 * private String srcIP;//用户所在客户端IP private String ad;//用户所在客户端IP private
	 * String ts;//用户请求的时间戳(1970年1月1日0时至今的毫秒数如1384912729136) private String
	 * url;//用户请求的当前页面URL private String ref;//用户请求的来源页面URL（Base64編碼數據） private
	 * String ua;//用户浏览器的UserAgent（Base64編碼數據） private String dstIP;//用户访问的服务器IP
	 * private String cookie;//用户Cookie（Base64編碼數據）
	 */// 額外屬性
	private MNoise noise;// 噪声对象
	private MUrlTag urlTag;// url标签对象

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public String getDestintionIp() {
		return destintionIp;
	}

	public void setDestintionIp(String destintionIp) {
		this.destintionIp = destintionIp;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getDestintionPort() {
		return destintionPort;
	}

	public void setDestintionPort(String destintionPort) {
		this.destintionPort = destintionPort;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public MNoise getNoise() {
		return noise;
	}

	public void setNoise(MNoise noise) {
		this.noise = noise;
	}

	public MUrlTag getUrlTag() {
		return urlTag;
	}

	public void setUrlTag(MUrlTag urlTag) {
		this.urlTag = urlTag;
	}

	@Override
	public String toString() {
		return "TbGDPI [userAccount=" + userAccount + ", protocolType="
				+ protocolType + ", sourceIp=" + sourceIp + ", destintionIp="
				+ destintionIp + ", sourcePort=" + sourcePort
				+ ", destintionPort=" + destintionPort + ", domainName="
				+ domainName + ", url=" + url + ", referer=" + referer
				+ ", userAgent=" + userAgent + ", cookie=" + cookie
				+ ", accessTime=" + accessTime + ", noise=" + noise
				+ ", urlTag=" + urlTag + "]";
	}

}

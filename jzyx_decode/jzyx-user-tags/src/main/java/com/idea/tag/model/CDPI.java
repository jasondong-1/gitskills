/*
* Copyright (C) China Telecom Corporation Limited, Cloud Computing Branch Corporation - All Rights Reserved
*
* Unauthorized copying of this file, via any medium is strictly prohibited
*
* Proprietary and confidential
*
* Contributors:
*     lf, l_feng@189.cn, 2015
*/ 
package com.idea.tag.model;

/**
 * @author LF
 *C网DPI数据模型，包括3G,4G属性
 */
public class CDPI {
	
	//3G&4G共有属性
	private String imsi;//[0]用户识别码
	private String mdn;//[1]用户手机号码
	private String meid;//[2]移动台设备标识
	private String nai;//[3]网络接入标识
	private String destIp;//[4]用户访问IP
	private String destPo;//[5]用户访问端口
	private String sourceIp;//[6]用户访问外网Ip
	private String sourcePo;//[7]用户访问外网端口
	private String serviceOp;//[8]用户cdma网络类型
	private String protocolId;//[9]协议类型
	private String serviceTyp;//[10]业务应用
	private String startTime;//[11]业务流开始时间
	private String endTime;//[12]业务流结束时间
	private String duration;//[13]持续时间
	private String inputOc;//[14]接收的字节数
	private String outputOc;//[15]发送的字节数
	private String inputPack;//[16]接受数据包
	private String outputPack;//[17]发送数据包
	private String recordclo;//[18]记录关闭原因
	private String userAgent;//[19]User Agent信息
	private String url;//[20]用户访问等url
	private String domainName;//[21]访问域名
	private String host;//[22]主机名
	private String contentLe;//[23]内容大小
	private String contentTy;//[24]内容类型
	private String ifLink;//[25]是否链接访问
	private String refer;//[26]链接源信息
	private String httpActio;//[27]http请求类型
	private String httpStatus;//[28]http状态码
	private String respDelay;//[29]响应时延
	private String behaviorTa;//[30]目标行为
	
	//3G特有属性
	private String pdsnip;//[31]
	private String pcsnip;//[32]
	private String haip;//[33]
	private String userZone;//[34]
	private String sbid;//[35]
	private String subnet;//[36]
	private String sessionId;//[37]
	//4G特有属性
	private String sgwip;//[38]
	private String mmeip;//[39]
	private String pgwip;//[40]
	private String sai;//[41]
	private String tai;//[42]
	private String visitedPl;//[43]
	private String pdnConn;//[44]
	private String bearerId;//[45]
	private String bearerQos;//[46]
	
	//额外属性
	private MNoise noise;//噪声对象
	private MUrlTag urlTag;//url标签对象


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

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	/**
	 * @return the mdn
	 */
	public String getMdn() {
		return mdn;
	}

	/**
	 * @param mdn the mdn to set
	 */
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	/**
	 * @return the meid
	 */
	public String getMeid() {
		return meid;
	}

	/**
	 * @param meid the meid to set
	 */
	public void setMeid(String meid) {
		this.meid = meid;
	}

	/**
	 * @return the nai
	 */
	public String getNai() {
		return nai;
	}

	/**
	 * @param nai the nai to set
	 */
	public void setNai(String nai) {
		this.nai = nai;
	}

	/**
	 * @return the destIp
	 */
	public String getDestIp() {
		return destIp;
	}

	/**
	 * @param destIp the destIp to set
	 */
	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	/**
	 * @return the destPo
	 */
	public String getDestPo() {
		return destPo;
	}

	/**
	 * @param destPo the destPo to set
	 */
	public void setDestPo(String destPo) {
		this.destPo = destPo;
	}

	/**
	 * @return the sourceIp
	 */
	public String getSourceIp() {
		return sourceIp;
	}

	/**
	 * @param sourceIp the sourceIp to set
	 */
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	/**
	 * @return the sourcePo
	 */
	public String getSourcePo() {
		return sourcePo;
	}

	/**
	 * @param sourcePo the sourcePo to set
	 */
	public void setSourcePo(String sourcePo) {
		this.sourcePo = sourcePo;
	}

	/**
	 * @return the serviceOp
	 */
	public String getServiceOp() {
		return serviceOp;
	}

	/**
	 * @param serviceOp the serviceOp to set
	 */
	public void setServiceOp(String serviceOp) {
		this.serviceOp = serviceOp;
	}

	/**
	 * @return the protocolId
	 */
	public String getProtocolId() {
		return protocolId;
	}

	/**
	 * @param protocolId the protocolId to set
	 */
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	/**
	 * @return the serviceTyp
	 */
	public String getServiceTyp() {
		return serviceTyp;
	}

	/**
	 * @param serviceTyp the serviceTyp to set
	 */
	public void setServiceTyp(String serviceTyp) {
		this.serviceTyp = serviceTyp;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}

	/**
	 * @return the inputOc
	 */
	public String getInputOc() {
		return inputOc;
	}

	/**
	 * @param inputOc the inputOc to set
	 */
	public void setInputOc(String inputOc) {
		this.inputOc = inputOc;
	}

	/**
	 * @return the outputOc
	 */
	public String getOutputOc() {
		return outputOc;
	}

	/**
	 * @param outputOc the outputOc to set
	 */
	public void setOutputOc(String outputOc) {
		this.outputOc = outputOc;
	}

	/**
	 * @return the inputPack
	 */
	public String getInputPack() {
		return inputPack;
	}

	/**
	 * @param inputPack the inputPack to set
	 */
	public void setInputPack(String inputPack) {
		this.inputPack = inputPack;
	}

	/**
	 * @return the outputPack
	 */
	public String getOutputPack() {
		return outputPack;
	}

	/**
	 * @param outputPack the outputPack to set
	 */
	public void setOutputPack(String outputPack) {
		this.outputPack = outputPack;
	}

	/**
	 * @return the recordclo
	 */
	public String getRecordclo() {
		return recordclo;
	}

	/**
	 * @param recordclo the recordclo to set
	 */
	public void setRecordclo(String recordclo) {
		this.recordclo = recordclo;
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the domainName
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * @param domainName the domainName to set
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the contentLe
	 */
	public String getContentLe() {
		return contentLe;
	}

	/**
	 * @param contentLe the contentLe to set
	 */
	public void setContentLe(String contentLe) {
		this.contentLe = contentLe;
	}

	/**
	 * @return the contentTy
	 */
	public String getContentTy() {
		return contentTy;
	}

	/**
	 * @param contentTy the contentTy to set
	 */
	public void setContentTy(String contentTy) {
		this.contentTy = contentTy;
	}

	/**
	 * @return the ifLink
	 */
	public String getIfLink() {
		return ifLink;
	}

	/**
	 * @param ifLink the ifLink to set
	 */
	public void setIfLink(String ifLink) {
		this.ifLink = ifLink;
	}

	/**
	 * @return the refer
	 */
	public String getRefer() {
		return refer;
	}

	/**
	 * @param refer the refer to set
	 */
	public void setRefer(String refer) {
		this.refer = refer;
	}

	/**
	 * @return the httpActio
	 */
	public String getHttpActio() {
		return httpActio;
	}

	/**
	 * @param httpActio the httpActio to set
	 */
	public void setHttpActio(String httpActio) {
		this.httpActio = httpActio;
	}

	/**
	 * @return the httpStatus
	 */
	public String getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus the httpStatus to set
	 */
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the respDelay
	 */
	public String getRespDelay() {
		return respDelay;
	}

	/**
	 * @param respDelay the respDelay to set
	 */
	public void setRespDelay(String respDelay) {
		this.respDelay = respDelay;
	}

	/**
	 * @return the behaviorTa
	 */
	public String getBehaviorTa() {
		return behaviorTa;
	}

	/**
	 * @param behaviorTa the behaviorTa to set
	 */
	public void setBehaviorTa(String behaviorTa) {
		this.behaviorTa = behaviorTa;
	}

	/**
	 * @return the pdsnip
	 */
	public String getPdsnip() {
		return pdsnip;
	}

	/**
	 * @param pdsnip the pdsnip to set
	 */
	public void setPdsnip(String pdsnip) {
		this.pdsnip = pdsnip;
	}

	/**
	 * @return the pcsnip
	 */
	public String getPcsnip() {
		return pcsnip;
	}

	/**
	 * @param pcsnip the pcsnip to set
	 */
	public void setPcsnip(String pcsnip) {
		this.pcsnip = pcsnip;
	}

	/**
	 * @return the haip
	 */
	public String getHaip() {
		return haip;
	}

	/**
	 * @param haip the haip to set
	 */
	public void setHaip(String haip) {
		this.haip = haip;
	}

	/**
	 * @return the userZone
	 */
	public String getUserZone() {
		return userZone;
	}

	/**
	 * @param userZone the userZone to set
	 */
	public void setUserZone(String userZone) {
		this.userZone = userZone;
	}

	/**
	 * @return the sbid
	 */
	public String getSbid() {
		return sbid;
	}

	/**
	 * @param sbid the sbid to set
	 */
	public void setSbid(String sbid) {
		this.sbid = sbid;
	}

	/**
	 * @return the subnet
	 */
	public String getSubnet() {
		return subnet;
	}

	/**
	 * @param subnet the subnet to set
	 */
	public void setSubnet(String subnet) {
		this.subnet = subnet;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the sgwip
	 */
	public String getSgwip() {
		return sgwip;
	}

	/**
	 * @param sgwip the sgwip to set
	 */
	public void setSgwip(String sgwip) {
		this.sgwip = sgwip;
	}

	/**
	 * @return the mmeip
	 */
	public String getMmeip() {
		return mmeip;
	}

	/**
	 * @param mmeip the mmeip to set
	 */
	public void setMmeip(String mmeip) {
		this.mmeip = mmeip;
	}

	/**
	 * @return the pgwip
	 */
	public String getPgwip() {
		return pgwip;
	}

	/**
	 * @param pgwip the pgwip to set
	 */
	public void setPgwip(String pgwip) {
		this.pgwip = pgwip;
	}

	/**
	 * @return the sai
	 */
	public String getSai() {
		return sai;
	}

	/**
	 * @param sai the sai to set
	 */
	public void setSai(String sai) {
		this.sai = sai;
	}

	/**
	 * @return the tai
	 */
	public String getTai() {
		return tai;
	}

	/**
	 * @param tai the tai to set
	 */
	public void setTai(String tai) {
		this.tai = tai;
	}

	/**
	 * @return the visitedPl
	 */
	public String getVisitedPl() {
		return visitedPl;
	}

	/**
	 * @param visitedPl the visitedPl to set
	 */
	public void setVisitedPl(String visitedPl) {
		this.visitedPl = visitedPl;
	}

	/**
	 * @return the pdnConn
	 */
	public String getPdnConn() {
		return pdnConn;
	}

	/**
	 * @param pdnConn the pdnConn to set
	 */
	public void setPdnConn(String pdnConn) {
		this.pdnConn = pdnConn;
	}

	/**
	 * @return the bearerId
	 */
	public String getBearerId() {
		return bearerId;
	}

	/**
	 * @param bearerId the bearerId to set
	 */
	public void setBearerId(String bearerId) {
		this.bearerId = bearerId;
	}

	/**
	 * @return the bearerQos
	 */
	public String getBearerQos() {
		return bearerQos;
	}

	/**
	 * @param bearerQos the bearerQos to set
	 */
	public void setBearerQos(String bearerQos) {
		this.bearerQos = bearerQos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CDPI [imsi=" + imsi + ", mdn=" + mdn + ", meid=" + meid
				+ ", nai=" + nai + ", destIp=" + destIp + ", destPo=" + destPo
				+ ", sourceIp=" + sourceIp + ", sourcePo=" + sourcePo
				+ ", serviceOp=" + serviceOp + ", protocolId=" + protocolId
				+ ", serviceTyp=" + serviceTyp + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", duration=" + duration
				+ ", inputOc=" + inputOc + ", outputOc=" + outputOc
				+ ", inputPack=" + inputPack + ", outputPack=" + outputPack
				+ ", recordclo=" + recordclo + ", userAgent=" + userAgent
				+ ", url=" + url + ", domainName=" + domainName + ", host="
				+ host + ", contentLe=" + contentLe + ", contentTy="
				+ contentTy + ", ifLink=" + ifLink + ", refer=" + refer
				+ ", httpActio=" + httpActio + ", httpStatus=" + httpStatus
				+ ", respDelay=" + respDelay + ", behaviorTa=" + behaviorTa
				+ ", pdsnip=" + pdsnip + ", pcsnip=" + pcsnip + ", haip="
				+ haip + ", userZone=" + userZone + ", sbid=" + sbid
				+ ", subnet=" + subnet + ", sessionId=" + sessionId
				+ ", sgwip=" + sgwip + ", mmeip=" + mmeip + ", pgwip=" + pgwip
				+ ", sai=" + sai + ", tai=" + tai + ", visitedPl=" + visitedPl
				+ ", pdnConn=" + pdnConn + ", bearerId=" + bearerId
				+ ", bearerQos=" + bearerQos + ", noise=" + noise + ", urlTag="
				+ urlTag + "]";
	}
	
	

}


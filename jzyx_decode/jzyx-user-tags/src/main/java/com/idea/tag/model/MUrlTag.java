package com.idea.tag.model;

import java.util.HashSet;
import java.util.Set;

/**URL标签实体类*/
public class MUrlTag {
	private boolean urlHasTag;//url字段是否含有标签
	private String urlTagsStr;//url标签內容(字符串表示)
	private Set<String> urlTagsSet;//url标签Set集和
	
	public boolean isUrlHasTag() {
		return urlHasTag;
	}
	public void setUrlHasTag(boolean urlHasTag) {
		this.urlHasTag = urlHasTag;
	}
	public String getUrlTagsStr() {
		return urlTagsStr;
	}
	public void setUrlTagsStr(String urlTagsStr) {
		this.urlTagsStr = urlTagsStr;
	}
	public Set<String> getUrlTagsSet() {
		return urlTagsSet;
	}
	public void setUrlTagsSet(Set<String> urlTagsSet) {
		this.urlTagsSet = urlTagsSet;
	}
	@Override
	public String toString() {
		return "UrlTag [urlHasTag=" + urlHasTag + ", urlTagsStr=" + urlTagsStr
				+ ", urlTagsSet=" + urlTagsSet + "]";
	}
}

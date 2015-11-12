package com.idea.tag.util;

import java.util.HashSet;
import java.util.Set;

public class ArraysUtil {

	public static void main(String[] args) {
		Set<String> strings=new HashSet<String>();
		strings.add("aa");
		System.out.println(setToString(strings));

	}

	public static String[] concat(String[] a, String[] b) {
		String[] c = new String[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	public static String setToString(Set<String> stringSet){
		if(stringSet==null||stringSet.isEmpty())return "";
		StringBuilder stringBuilder=new StringBuilder();
		for (String s:stringSet){
			if(s!=null&&!s.isEmpty()){
				stringBuilder.append(s.trim());
				stringBuilder.append(',');
			}
		}
		if(stringBuilder.length()>0){
			return stringBuilder.replace(stringBuilder.lastIndexOf(","),stringBuilder.length(),"").toString();
		}else{
			return "";
		}
	}
}

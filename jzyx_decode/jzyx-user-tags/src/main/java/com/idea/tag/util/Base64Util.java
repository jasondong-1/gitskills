package com.idea.tag.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	
	public static String base64Encrypt(String str){
		return Base64.encodeBase64String(str.getBytes()).replaceAll("\r\n", "");
	}
	
	public static String base64Decode(String str){
		return new String(Base64.decodeBase64(str));
	}

	public static void main(String[] args) {
//		System.out.println(Base64.encodeBase64String("你好你好你好你好你好你好你好你好你好你好".getBytes()).replaceAll("\r\n", ""));
//		System.out.println(new String(Base64.decodeBase64(Base64.encodeBase64String("http://item.jd.com/1000003.html".getBytes()).replaceAll("\r\n", ""))));
		System.out.println(base64Decode(("aHR0cDovL3NlYXJjaC5hcGkuM2cueW91a3UuY29tL2tleXdvcmRzL3N1Z2dlc3Q_cGlkPTZiNWY5NGY0YWIzM2M3MDImZ3VpZD1lODFkYjQzMjk4ZWIxNzlmNWYwODdkOWE3ZWI4ZWEzNiZtYWM9NjglM0FkZiUzQWRkJTNBOGYlM0FiZCUzQTBlJmltZWk9ODY0MjYwMDIxNjAzMjc0JnZlcj00LjYuMSZfdF89MTQyODU1NzM2OSZlPW1kNSZfc189ZGM1NDUwODY5YzBiNTEwNjE2NDZlY2Q1NGI5MTBhOGUmb3BlcmF0b3I9Q01DQ180NjAwMCZuZXR3b3JrPVdJRkkma2V5d29yZHM9JUU4JTgyJTk2JUU2JTlEJUIwJmJyYW5kPVhpYW9taSZidHlwZT1NSSszJmtyZWY9Pz8_JUU2JThGJTkwJUU3JUE0JUJBJUU4JUFGJThE")));
		System.out.println(base64Decode("年和哦法").length());
	}
}

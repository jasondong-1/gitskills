package com.idea.tag.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 加密 
 *
 */

public class Encryption {
	public static void main(String[] args) {

//		System.out.println(SHA(""));				//da39a3ee5e6b4b0d3255bfef95601890afd80709
//		System.out.println(SHA("none"));			//71f8e7976e4cbc4561c9d62fb283e7f788202acb
//		System.out.println(SHA("null"));			//2be88ca4242c76e8253ac62474851065032d6833
//		System.out.println(SHA("ad56524325"));		//e928204ac4bf45e308334d6f616022741bffd56e
		System.out.println(SHA("lifeng"));		//f218c7fdda2ee4498337c7ed30a55e1fe0b78a38
		//4b581cdce6283495fd4934ce574ac47e92881c64
		//4b581cdce6283495fd4934ce574ac47e92881c64
//		System.out.println(SHA("ad67358544"));		//58795bcdd2fdd1b95b6ffb151314e1539675ba2d
		
	}
	
	protected final static String SHA(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(s.getBytes());
			byte[] hash = md.digest();
			StringBuilder builder = new StringBuilder();
			for (byte b : hash) {
				builder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException nsae) {
			return null;
		}
	}
}


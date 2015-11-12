package com.ideal.main;


import com.idea.update.UpdateData;

import ocm.ideal.top10000.Top10000;

public class MainClass {
	public static void main(String[] args) {
		//Log log=LogFactory.getLog(MainClass.class);
//		if(args.length!=2){
//			//log.error("reference [city] [date]");
//			return;
//		}else{
//			String city=args[0];
//			String date=args[1];
//			//Top10000.top10000(city, date);
//			UpdateData.update(city, date);
//		}
		UpdateData.update("shanghai", "20150914");
	}
}

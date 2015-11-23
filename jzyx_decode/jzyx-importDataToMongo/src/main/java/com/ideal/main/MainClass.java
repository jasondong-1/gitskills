package com.ideal.main;


import com.idea.update.UpdateData;

import ocm.ideal.top10000.Top10000;

public class MainClass {
	public static void main(String[] args) {
		//Log log=LogFactory.getLog(MainClass.class);
		if(args.length!=3){
			//log.error("reference [city] [date]");
			System.out.println("reference [inputpath]  [outputpath] [city]");
			System.exit(-1);
		}else{
//			String city=args[0];
//			String date=args[1];
//			Top10000.top10000(city, date);
//			UpdateData.update(city, date);
			String inputPath=args[0];
			String outputPath=args[1];
			String city=args[2];
			Top10000.top10000(inputPath,outputPath);
			UpdateData.update(outputPath,city);

		}
		
	}
}

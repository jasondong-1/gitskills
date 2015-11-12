package com.ideal.mapreduce;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TagCountMapper extends Mapper<LongWritable, Text, Text, Text> {
	static Set<String> set1 = new HashSet<String>();
	static Set<String> set2 = new HashSet<String>();
	static Set<String> set3 = new HashSet<String>();

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// do something initialize
		super.setup(context);
		getFile();
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		// do something cleanup
		super.cleanup(context);
	}

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		List<String> list = new ArrayList<String>();
		String info = value.toString();
		String[] infos = info.split("\t");
		if (infos.length > 1) {
			  String ad = infos[0];
			String tag = infos[1];
			String[] tags = tag.split(";", -1);
			for (String tg : tags) {
				if (tg.contains(",")) {
					String[] tgs = tg.split(",", -1);
					if (tgs.length == 3) {
						for (String set : set1) {
							if (tgs[0].equals(set)) {
								list.add("1"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
								break;
							}
						}
						for (String set : set2) {
							if (tgs[1].equals(set)) {
								list.add("2"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
								break;
							}
						}
						for (String set : set3) {
							if (tgs[2].contains(set)) {
								list.add("3"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
								break;
							}
						}
					} else {
						for (String set : set1) {
							if (tgs[0].equals(set)) {
								list.add("1"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
								break;
							}
						}
						for (String set : set2) {
							if (tgs[1].contains(set)) {
								list.add("2"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
								break;
							}
						}
					}
				} else {
					for (String set : set1) {
						if (tg.contains(set)) {
							list.add("1"+ "\t"+ set+ "\t"+ tg.substring(tg.indexOf(":") + 1,tg.length()));
							break;
						}
					}
				}
			}
			for(String lis : list){
				String [] str = lis.split("\t");
				context.write(new Text(str[0]+"\t"+str[1]),new Text(ad+"\t"+str[2]));
			}
		}
		
	}

	public  void getFile() throws IOException {
		String ruleFile = "original_tag.txt";
		InputStream is =TagCountMapper.class.getClassLoader().getResourceAsStream("original_tag.txt");
				//this.getClass().getResourceAsStream(ruleFile);

		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String line = "";
		while ((line = br.readLine())!= null) {
			
			String[] str = line.split("\t",-1);
			if (!(str[0].equals(""))) {
				set1.add(str[0]);
			}
			if (!(str[1].equals(""))) {
				set2.add(str[1]);
			}
			if (!(str[2].equals(""))) {
				set3.add(str[2]);
			}
		}
		br.close();
	}


}

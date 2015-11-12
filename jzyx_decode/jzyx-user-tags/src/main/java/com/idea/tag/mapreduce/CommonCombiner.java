package com.idea.tag.mapreduce;

import java.io.IOException;
import java.util.*;

import com.idea.tag.util.ArraysUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class CommonCombiner extends Reducer<Text, Text, Text, Text> {
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Map<String, Integer> map = new HashMap();
		Set<String> meidStr=new HashSet<String>();
		// 处理combiner
		for (Text val : values) {
			// 处理combiner中的数据
			String[] tmArra = val.toString().split("\t");
			String tag = tmArra[0];
			if(tmArra.length==2&&tmArra[1]!=null){
				meidStr.add(tmArra[1]);
			}
			if (map.keySet().contains(tag)) {
				map.put(tag, map.get(tag) + 1);
			} else {
				map.put(tag, 1);
			}
		}

		String tags = "";
		// 遍历命中map组字符串
		for (String tag : map.keySet()) {
			tags += tag + ":" + map.get(tag) + "\t";
		}
		if ("".equals(tags)) {
			return;
		}
		if("".equals(key.toString())){
			return;
		}
		if(!meidStr.isEmpty()){
			tags=tags+"\t\t"+ ArraysUtil.setToString(meidStr);
		}
		context.write(key, new Text(tags));
	}
}

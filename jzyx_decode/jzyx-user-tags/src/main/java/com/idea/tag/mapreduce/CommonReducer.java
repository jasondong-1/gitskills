package com.idea.tag.mapreduce;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import com.idea.tag.util.ArraysUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class CommonReducer extends Reducer<Text, Text, Text, Text> {
	private static final Log LOG = LogFactory.getLog(CommonReducer.class);

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		LOG.info("reduce任务开始执行。。。。");
	}

	/**
	 * 按标签次数排序并输出标签及次数 eg:资讯,新闻资讯:5 新华网:4
	 * */
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Map<String, Integer> map = new HashMap();
		Set<String> meidStr=new HashSet<String>();
		// 处理打了标签的url
		for (Text val : values) {
			// 处理combiner中的数据
			String tmStr=val.toString();
			String[] tmArr=tmStr.split("\t\t");
			if(tmArr.length==2&&!tmArr[1].isEmpty()){
				meidStr.addAll(Arrays.asList(tmArr[1].split(",")));
			}
			StringTokenizer itr = new StringTokenizer(tmArr[0], "\t");
			while (itr.hasMoreTokens()) {
				String tag_num = itr.nextToken();
				String[] onetag = tag_num.split(":");
				if (onetag.length < 2) {
					return;
				}
				String tag = onetag[0];
				Integer sum = Integer.valueOf(onetag[1]);
				if ("".equals(onetag[0])) {
					return;
				}

				if (map.keySet().contains(onetag[0])) {
					map.put(onetag[0], map.get(onetag[0]) + sum);
				} else {
					map.put(tag, sum);
				}
			}
		}

		String tags = "";
		// 遍历命中map组字符串
		for (String tag : map.keySet()) {
			tags += tag + ":" + map.get(tag) + ";";
		}
		if ("".equals(tags)) {
			return;
		}
		if (!meidStr.isEmpty()){
			tags=tags.trim()+'\t'+ ArraysUtil.setToString(meidStr);
		}
		context.write(key, new Text(tags.getBytes("UTF-8")));
	}

	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
	}

}

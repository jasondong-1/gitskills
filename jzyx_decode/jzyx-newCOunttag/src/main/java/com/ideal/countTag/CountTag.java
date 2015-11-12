package com.ideal.countTag;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/11/7.
 */
public class CountTag extends Configured implements Tool{
    public static class MyMapper extends Mapper<LongWritable,Text,Text,DoubleWritable>{
        private Text text=new Text();
        private DoubleWritable doubleWritable=new DoubleWritable();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            //将数据转换为字符串
            String info=value.toString();//00BB397E006AB876E355023ECF2B2A2E        娱乐,演出,电影:8;IT,IT资源:3;   0D6BDAC4BEE65EA6530C488A5E5C2556
             //以\t 分割，取出各列信息 第一个为ad，第二个为标签权重，第三个为meid号，这里不用第三个
            String[] infos=info.split("\t",-1);
            //取出标签权重列
            String tag=infos[1];//娱乐,演出,电影:8;IT,IT资源:3;
            Map map=handleTag(tag);
            if(!(map.size()==0)){
                Set<String> keys=map.keySet();
                for(String ke:keys){
                    double d=(Double)(map.get(ke));
                    text.set(ke);
                    doubleWritable.set(d);
                    context.write(text,doubleWritable);
                }
            }
        }

        /**
         *
         * @param tag
         * @return
         */
        private static Map handleTag(String tag){
            //创建map用于存储分割后的信息
            Map<String,Double> map =new HashMap<String,Double>();
            try{
                //{"娱乐,演出,电影:8",......}
                String[] tags=tag.split(";",-1);
                for(String s:tags){
                    //{“娱乐,演出,电影”，“8”}
                    String[] tagAndWeight=s.split(":",-1);
                    //取出带有等级的标签，
                    String tagUnion=tagAndWeight[0];
                    //取出权重值
                    Double weight=Double.parseDouble(tagAndWeight[1]);
                    //取出每一级标签存放在数组当中
                    String[] everylevel=tagUnion.split(",",-1);
                    int len=everylevel.length;
                    //处理每一个标签
                    for(int i=0;i<len;i++){
                        //levelTag为标签等级，标签和用户数的组合，中间用\t分割
                        String levelTag=""+(i+1)+"\t"+everylevel[i];
                        Double d1=map.get(levelTag);
                        if(d1!=null){
                            d1=d1+weight;
                            map.remove(levelTag);
                            map.put(levelTag,d1);
                        }else {
                            map.put(levelTag,weight);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return map;
        }
    }

    public static class MyReduce extends Reducer<Text,DoubleWritable,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
            //统计总标签浏览量

                double sum = 0.0;
                //统计总用户数
                int i = 0;
                for (DoubleWritable value : values) {
                    i++;
                    double d = value.get();
                    sum += d;
                }
                double heat_value = sum / i;
                DecimalFormat df = new DecimalFormat("#.00");
                //将热度值保留两位小数
                String formatHeatValue = df.format(heat_value);
                String sums=df.format(sum);
                String mes = "" + i + "\t" + sums + "\t" + formatHeatValue;
                context.write(key, new Text(mes));

        }
    }
    public int run(String[] args) throws Exception {
        Job job=Job.getInstance(getConf());

        job.setJarByClass(CountTag.class);
        job.setJobName("countTag");

        job.setMapperClass(MyMapper.class);
        //job.setCombinerClass(MyReduce.class);
        job.setReducerClass(MyReduce.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(DoubleWritable.class);
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);



        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        boolean success=job.waitForCompletion(true);
        return success?0:1;
    }

    public static void main(String[] args) throws Exception {
        int a=ToolRunner.run(new CountTag(),args);
        System.exit(a);
    }
}
package com.idea.tag.mapreduce;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.idea.tag.constant.Constants;
import com.idea.tag.filter.Denoising;
import com.idea.tag.model.CDPI;
import com.idea.tag.model.MNoise;
import com.idea.tag.model.MUrlTag;
import com.idea.tag.model.TbGDPI;
import com.idea.tag.process.TagProcess;
import com.idea.tag.util.ModelUtil;

public class CommonMapper extends Mapper<LongWritable, Text, Text, Text> {
    /**
     * DPI map类
     */
    private static final Log LOG = LogFactory.getLog(CommonMapper.class);
    private static String input_delimiter;
    private static boolean isDoNoise;
    private static boolean saveUnlabelledUrl;
    private static boolean needBase64;

    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        LOG.info("map任务开始执行。。。。");
        input_delimiter = context.getConfiguration().get(
                Constants.INPUT_DELIMITER.toString());
        isDoNoise = context.getConfiguration().getBoolean(
                Constants.DO_NOISE.toString(), true);
        saveUnlabelledUrl = context.getConfiguration().getBoolean(
                Constants.SAVE_UNLABELLED.toString(), false);
        needBase64 = context.getConfiguration().getBoolean("NeedBase64", false);
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        CDPI record = ModelUtil.toCDPI(value.toString(), input_delimiter,
                needBase64);
        if (record == null || record.getUrl() == null) {// 如果record为null，则返回
            return;
        }

        if (isDoNoise) {// 去噪处理
            record.setNoise(Denoising.filterCDPI(record));
        } else {// 不去噪处理
            record.setNoise(new MNoise());
        }
        if (!record.getNoise().isNoise()) {// 非噪声数据（合法数据）
            // LOG.info(record.getDestinationUR()+"===========================");
            MUrlTag tag = TagProcess.tag(record.getUrl());// 打标签处理
            // LOG.info(tag);
            if (tag.isUrlHasTag()) {
                LOG.debug("一行打出的标签：" + tag.toString());
                record.setUrlTag(tag);
                // 命中user 输出 S_user_url
                String val=record.getUrlTag().getUrlTagsStr();
                if(record.getMeid()!=null){
                    val=val+"\t"+record.getMeid();
                }
                context.write(new Text(record.getMdn()),
                        new Text(val));
            }
        }
    }


}

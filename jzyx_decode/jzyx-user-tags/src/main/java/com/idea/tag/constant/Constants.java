package com.idea.tag.constant;
/**
 *常量枚举类（需要在配置文件conf.properties中配置）
 * */
public enum Constants {
	JOB_NAME,// job名稱的
	INPUT_PATH_GDPI,//GDPI数据輸入路徑
	OUTPUT_PATH_GDPI,//GDPI数据輸出路徑
	INPUT_PATH_CDPI,//CDPI数据輸入路徑
	OUTPUT_PATH_CDPI,//CDPI数据輸出路徑
	INPUT_DELIMITER,//输入记录的分隔符
	DO_NOISE,//是否进行去噪处理
	DO_GDPI,//是否对GDPI数据进行标签处理
	DO_CDPI,//是否对CDPI数据进行标签处理
	ID,//输出的唯一标识字段(ad/uid)
	SAVE_UNLABELLED//是否保留未命中的用户&url
}

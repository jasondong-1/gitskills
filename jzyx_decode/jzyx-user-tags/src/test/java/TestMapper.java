import com.idea.tag.process.KeywordMapperProcess;
import com.idea.tag.util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by xia on 2015/10/20.
 */
public class TestMapper {
    public static void main(String[] args) {
        String url="http://www.ssaa23212.com?k=";
        HashSet<String> urlTagsSet=new HashSet<String>();
        List<Map<String,String[]>> mapperList= FileUtil.loadTagKeyWordMapper("mapperFile.text", "\t", "UTF-8", 300);
        long t=new Date().getTime();
        for(int i=0;i<10000;i++){
            new KeywordMapperProcess().find(url,urlTagsSet,mapperList);
        }
        System.out.println("---------------");
        System.out.println(new Date().getTime()-t);
        System.out.println(urlTagsSet);

    }
}

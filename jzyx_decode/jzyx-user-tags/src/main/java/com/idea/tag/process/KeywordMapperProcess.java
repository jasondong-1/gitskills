package com.idea.tag.process;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xia on 2015/10/20.
 */
public class KeywordMapperProcess{
    private volatile String tag=null;
    private ExecutorService executor;
    private  String  getTag(){
            return tag;
    }
    private  void setTag(String tag){
            this.tag=tag;
    }
    public void find(String url,Set<String> urlTagsSet,List<Map<String,String[]>> mapperList){

        CountDownLatch threadSignal = new CountDownLatch(mapperList.size());
         this.executor = Executors.newFixedThreadPool(mapperList.size());

        for(int i=0;i<mapperList.size();i++){
            Runnable task = new KeywordMapperThead(mapperList.get(i),url,threadSignal);
            executor.execute(task);
        }
        try {
            threadSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        if(tag!=null){
            urlTagsSet.add(tag);
        }
    }

    class KeywordMapperThead implements Runnable{
        private Map<String,String[]> mapper;
        private String url;
        private CountDownLatch threadsSignal;

        public KeywordMapperThead(Map<String, String[]> mapper, String url, CountDownLatch threadsSignal) {
            this.mapper = mapper;
            this.url = url;
            this.threadsSignal = threadsSignal;
        }

        public void run() {
                for(Map.Entry<String,String[]> entry:mapper.entrySet()){
                    if(getTag()==null){
                        for (String k:entry.getValue()){
                            if (url.indexOf(k)>=0){
                                setTag(entry.getKey());
                                threadsSignal.countDown();
                                break;
                            }
                        }
                        if (getTag()!=null){
                            threadsSignal.countDown();
                            break;
                        }
                    }else{
                        break;
                    }
                }
                threadsSignal.countDown();
        }
    }
}



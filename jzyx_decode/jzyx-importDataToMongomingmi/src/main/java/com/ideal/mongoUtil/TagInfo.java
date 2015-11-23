package com.ideal.mongoUtil;

/**
 * Created by Administrator on 2015/11/8.
 */
public class TagInfo {
    private String ad;
    private String meid;
    private String no;

    public TagInfo() {

    }

    public String getAd() {
        return ad;
    }

    public String getMeid() {
        return meid;
    }

    public String getNo() {
        return no;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return this.getAd()+","+this.getMeid()+","+this.getNo();
    }
}

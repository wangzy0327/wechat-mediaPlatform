package com.wechat.mp.pojo;

import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.Date;

public class WxItemRead {
    private Integer id;
    private Fans fans;
    private WxItem wxItem;
    private int readTime;//阅读时间
    private int readTimes;//阅读次数
    private int shareAppMessage;//转发给好友
    private int shareTimeLine;//分享到朋友圈
    private Date createTime;

    private Date updateTime;

    public WxItemRead() {super(); }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fans getFans() {
        return fans;
    }

    public void setFans(Fans fans) {
        this.fans = fans;
    }

    public WxItem getWxItem() {
        return wxItem;
    }

    public void setWxItem(WxItem wxItem) {
        this.wxItem = wxItem;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    public int getShareAppMessage() {
        return shareAppMessage;
    }

    public void setShareAppMessage(int shareAppMessage) {
        this.shareAppMessage = shareAppMessage;
    }

    public int getShareTimeLine() {
        return shareTimeLine;
    }

    public void setShareTimeLine(int shareTimeLine) {
        this.shareTimeLine = shareTimeLine;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }
}

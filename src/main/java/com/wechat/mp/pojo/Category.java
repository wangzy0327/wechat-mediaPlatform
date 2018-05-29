package com.wechat.mp.pojo;

import com.wechat.mp.common.DateUtil;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable{
    private Integer id;

    private String name;

    private String content;

    private Date createTime;

    private Date updateTime;

    private List<WxItem> wxItems;

    public Category(Integer id, String name, String content,Date createTime, Date updateTime,List<WxItem> wxItems) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.wxItems = wxItems;
    }

    public Category() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public Date getCreateTime() { return createTime ; }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() { return updateTime; }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<WxItem> getWxItems() { return wxItems; }

    public void setWxItems(List<WxItem> wxItems) { this.wxItems = wxItems; }

    @Override
    public String toString() { return ToStringUtils.toSimpleString(this); }
}
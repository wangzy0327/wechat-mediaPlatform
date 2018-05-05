package com.wechat.mp.pojo;

import com.wechat.mp.common.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Category implements Serializable{
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private List<WxItem> wxItems;

    public Category(Integer id, String name, Date createTime, Date updateTime,List<WxItem> wxItems) {
        this.id = id;
        this.name = name;
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

    public String getCreateTime() {
        return DateUtil.COMMON_FULL.getDateText(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return DateUtil.COMMON_FULL.getDateText(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<WxItem> getWxItems() { return wxItems; }

    public void setWxItems(List<WxItem> wxItems) { this.wxItems = wxItems; }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", wxItems=" + wxItems +
                '}';
    }
}
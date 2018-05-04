package com.wechat.mp.pojo;

import java.io.Serializable;
import java.util.Date;

public class TagItem implements Serializable {
    private Integer id;

    private Integer tagId;

    private Integer itemId;

    private Date createTime;

    private Date updateTime;

    public TagItem(Integer id, Integer tagId, Integer itemId, Date createTime, Date updateTime) {
        this.id = id;
        this.tagId = tagId;
        this.itemId = itemId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TagItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
}
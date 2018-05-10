package com.wechat.mp.pojo;

import com.wechat.mp.common.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {
    private Integer id;

    private String name;

    private Date createTime;

    private Date updateTime;

    public Tag(Integer id, String name, Date createTime, Date updateTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
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
        return (createTime == null)?null: DateUtil.COMMON_FULL.getDateText(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return (updateTime == null)?null:DateUtil.COMMON_FULL.getDateText(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
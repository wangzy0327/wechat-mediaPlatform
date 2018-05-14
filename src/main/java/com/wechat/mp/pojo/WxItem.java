package com.wechat.mp.pojo;

import com.wechat.mp.common.DateUtil;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WxItem implements Serializable {
    private Integer id;

    private String itemId;

    private String title;

    private String description;

    private String url;

    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    private Category category;

    private List<Tag> tags;

    private Integer state;

    public WxItem(Integer id, String itemId, String title, String description, String url, String imgUrl, Date createTime, Date updateTime, Category category, List<Tag> tags,Integer state) {
        this.id = id;
        this.itemId = itemId;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imgUrl = imgUrl;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.category = category;
        this.tags = tags;
        this.state = state;
    }

    public WxItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getCreateTime() {
        return (createTime == null)?null:DateUtil.COMMON_FULL.getDateText(createTime);
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

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public List<Tag> getTags() { return tags; }

    public void setTags(List<Tag> tags) { this.tags = tags; }

    public Integer getState() { return state; }

    public void setState(Integer state) { this.state = state; }

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }

}
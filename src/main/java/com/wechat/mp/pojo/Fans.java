package com.wechat.mp.pojo;

import com.wechat.mp.util.wechat.AccountFans;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.Date;
import java.util.List;

public class Fans{

    //采用openId来唯一标识Fans
    private Integer id;
    private String openId;
    private AccountFans accountFans;
    private List<Category> categories;
    private List<WxItemRead> wxItemReads;
    private Date createTime;

    private Date updateTime;

    public Fans() {super(); }

    public Fans(AccountFans accountFans){
        this.accountFans = accountFans;
    }

    public Fans(Integer id, AccountFans accountFans, List<Category> categories) {
        this.id = id;
        this.accountFans = accountFans;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public AccountFans getAccountFans() {
        return accountFans;
    }

    public void setAccountFans(AccountFans accountFans) {
        this.accountFans = accountFans;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<WxItemRead> getWxItemReads() {
        return wxItemReads;
    }

    public void setWxItemReads(List<WxItemRead> wxItemReads) {
        this.wxItemReads = wxItemReads;
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

package com.wechat.mp.pojo;

import com.wechat.mp.util.wechat.AccountFans;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.util.List;

public class Fans{

    //采用openId来唯一标识Fans
    private String id;
    private AccountFans accountFans;
    private List<Category> categories;


    public Fans() {super(); }

    public Fans(AccountFans accountFans){
        this.accountFans = accountFans;
    }

    public Fans(String id, AccountFans accountFans, List<Category> categories) {
        this.id = id;
        this.accountFans = accountFans;
        this.categories = categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return ToStringUtils.toSimpleString(this);
    }
}

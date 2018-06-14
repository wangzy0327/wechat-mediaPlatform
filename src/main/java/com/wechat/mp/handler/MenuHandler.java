package com.wechat.mp.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wechat.mp.builder.TextBuilder;
import com.wechat.mp.controller.WxOAuth2Controller;
import com.wechat.mp.dao.WxItemMapper;
import com.wechat.mp.pojo.WxItem;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage.Item;

/**
 * @author Binary Wang
 */
@Component
public class MenuHandler extends AbstractHandler {

  private String openId;

  @Autowired
  private WxItemMapper wxItemMapper;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {
    String msg = String.format("type:%s, event:%s, key:%s",
        wxMessage.getMsgType(), wxMessage.getEvent(),
        wxMessage.getEventKey());
    if (WxConsts.EventType.VIEW.equals(wxMessage.getEvent())) {
      this.openId = wxMessage.getFromUser();
      return null;
    }
    else if(WxConsts.EventType.CLICK.equals(wxMessage.getEvent())){
      String eventKey = wxMessage.getEventKey();
      switch (eventKey){
        case "1_2":
          List<WxItem> wxItems = wxItemMapper.findUpToDateWxItem();
          List<Item> items = new ArrayList<>();
          for(int i = 0;i<wxItems.size();i++){
            Item item = new Item();
            item.setUrl(wxItems.get(i).getUrl());
            item.setTitle(wxItems.get(i).getTitle());
            item.setPicUrl(wxItems.get(i).getImgUrl());
            item.setDescription(wxItems.get(i).getDescription());
            items.add(item);
          }
          return new NewsBuilder().addArticle( items.toArray(new Item[0])).
                  fromUser(wxMessage.getToUser()).
                  toUser(wxMessage.getFromUser()).
                  build();
        case "2_1":
          StringBuffer content = new StringBuffer("点击好文推荐，推送相关好文\n" +
                  "发送地理位置信息，可获取您的地理经纬度\n"+
                  "点击个人详情，查看个人信息\n");
          return new TextBuilder().build(content.toString(), wxMessage, null);
      }

    }

    return WxMpXmlOutMessage.TEXT().content(msg)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }
}

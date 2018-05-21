package com.wechat.mp.handler;

import java.util.Map;

import com.wechat.mp.controller.WxOAuth2Controller;
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
      Item item = new Item();
      item.setUrl("https://www.jianshu.com/p/1266379bdcaa");
      item.setTitle("微信公众号开发-简书");
      item.setPicUrl("https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png");
      item.setDescription("微信公众号开发JS—SDK");
      return new NewsBuilder().addArticle(item).
              fromUser(wxMessage.getToUser()).
              toUser(wxMessage.getFromUser()).
              build();
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

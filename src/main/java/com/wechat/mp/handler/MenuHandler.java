package com.wechat.mp.handler;

import java.util.Map;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
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
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {
    String msg = String.format("type:%s, event:%s, key:%s",
        wxMessage.getMsgType(), wxMessage.getEvent(),
        wxMessage.getEventKey());
    if (WxConsts.EventType.VIEW.equals(wxMessage.getEvent())) {
      return null;
    }
    else if(WxConsts.EventType.CLICK.equals(wxMessage.getEvent())){
      Item item = new Item();
      item.setUrl("http://v6.rabbitpre.com/m/neUzquH?mobile=1");
      item.setTitle("大辣娇--加1元再来1桶");
      item.setPicUrl("http://cdn5.rabbitpre.com/e6efb2b6-3758-4a10-9439-32c65cab1406.png@0-0-0-0a_284w_70Q_1an.src?crossorigin=1");
      item.setDescription("白象大辣娇，加1元再来1桶，感恩回馈消费者！");
      return new NewsBuilder().addArticle(item).
              fromUser(wxMessage.getToUser()).
              toUser(wxMessage.getFromUser()).
              build();
    }

    return WxMpXmlOutMessage.TEXT().content(msg)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();
  }


}

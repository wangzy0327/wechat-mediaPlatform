package com.wechat.mp.handler;

import java.util.Map;

import com.wechat.mp.dao.FansMapper;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wechat.mp.builder.TextBuilder;
import com.wechat.mp.service.WxService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * @author Binary Wang
 *
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  @Autowired
  private FansMapper fansMapper;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
      WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    String openId = wxMessage.getFromUser();
    // 获取微信用户基本信息
    AccountFans fans = WxApiClient.getAccountFans(openId);

    WxService weixinService = (WxService) wxMpService;

//    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

    if (fans != null) {
      // TODO 可以添加关注用户到本地
      if(fansMapper.findCountByPrimaryKey(openId)>0){
        System.out.println("update(更新):"+fans.getNicknameStr());
        fansMapper.updateByPrimaryKeySelective(fans);
      }else{
        System.out.println("insert(插入):"+openId);
        fansMapper.insertSelective(fans);
      }

    }

    WxMpXmlOutMessage responseResult = null;

    System.out.println("关注EventKey:"+wxMessage.getEventKey());
    if(wxMessage.getEventKey().startsWith("qrscene_")){
      try {
        responseResult = handleSpecial(wxMessage);
      } catch (Exception e) {
        this.logger.error(e.getMessage(), e);
      }
    }

    if (responseResult != null) {
      return responseResult;
    }

    try {
      return new TextBuilder().build("感谢关注", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
    //TODO
    this.logger.info("新关注用户（扫码关注） OPENID: " + wxMessage.getFromUser());

    try {
      return new TextBuilder().build("感谢扫码关注", wxMessage, null);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }
    return null;
  }

}

package com.wechat.mp.service.impl;

import com.wechat.mp.dao.WxItemMapper;
import com.wechat.mp.pojo.WxItem;
import com.wechat.mp.service.IWxItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Service("iWxItemService")
public class WxItemServiceImpl implements IWxItemService {

    @Autowired
    private WxItemMapper wxItemMapper;


    /**
     * 根据DataTables中的参数进行查询
     * @param param
     * @return
     */
    public List<WxItem> findWxItemByParam(Map<String,String> param){
//        for(Map.Entry<String, String> entry : param.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
        return wxItemMapper.findWxItemByParam(param);
    }

    /**
     * 获取图文消息的总数量
     * @return
     */
    public Integer findWxItemCount() {
        return wxItemMapper.findWxItemCount();
    }

    /**
     * 根据查询条件获取用户的数量
     * @param param
     * @return
     */
    public Integer findWxItemCountByParam(Map<String,String> param){
        return wxItemMapper.findWxItemCountByParam(param);
    }

}

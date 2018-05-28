package com.wechat.mp.controller.wechat;

import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.WxItem;
import com.wechat.mp.service.ICategoryService;
import com.wechat.mp.service.IWxItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wechat/wxItem")
public class WxItemController {

    @Autowired
    private IWxItemService iWxItemService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "/items.json",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse load(HttpServletRequest request, HttpSession session,String searchValue){
//        String search = request.getParameter("searchValue");
//        System.out.println("search:"+search);
        Map<String,String> param = new HashMap<>();
        param.put("start",String.valueOf(0));
        param.put("length",String.valueOf(100));
        List<WxItem> wxItemList = null;
        if(StringUtils.isNotEmpty(searchValue)) {
//            param.put("title","%" + (searchValue) + "%");
//            param.put("category","%" + (searchValue) + "%");
            param.put("keyword", "%" + (searchValue) + "%");
            param.put("orderColumn","create_time");
            param.put("orderType","desc");

            wxItemList = iWxItemService.findWxItemByParam(param);
        }else{
            wxItemList = iWxItemService.findWxItemByParam();
        }
        for(int i = 0;i<wxItemList.size();i++){
            Category category = wxItemList.get(i).getCategory();
            System.out.println("Category:"+i+":"+wxItemList.get(i).getCategory());
//            System.out.println(category.getCreateTime());
            System.out.println("Item"+i+":"+wxItemList.get(i));
            System.out.println(wxItemList.get(i).getCreateTime());
        }
//        return ServerResponse.createBySuccess();
        return ServerResponse.createBySuccess(wxItemList);
    }


    @RequestMapping(value = "/categorys.json",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse loadCategory(HttpServletRequest request, HttpSession session){
        return iCategoryService.findCategory();
    }

}

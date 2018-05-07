package com.wechat.mp.controller.backend;

import com.google.common.collect.Maps;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.WxItem;
import com.wechat.mp.service.ICategoryService;
import com.wechat.mp.service.IWxItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend")
public class WxItemManageController {
    @Autowired
    private IWxItemService iWxItemService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(){return "backend/backend";}

    /**
     * 让DataTables控件加载数据
     */
    @RequestMapping(value = "/items.json",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){

        String draw = request.getParameter("draw");
        Integer start = Integer.valueOf(request.getParameter("start"));
        Integer length = Integer.valueOf(request.getParameter("length"));
        String searchValue = request.getParameter("search[value]");
        String orderColumnIndex = request.getParameter("order[0][column]");
        String orderType = request.getParameter("order[0][dir]");
        String orderColumnName = request.getParameter("columns["+orderColumnIndex+"][name]");

        Map<String,String> param = Maps.newHashMap();
        param.put("start",String.valueOf(start));
        param.put("length",String.valueOf(length));
        if(StringUtils.isNotEmpty(searchValue)) {
            param.put("keyword", "%" + (searchValue) + "%");
        }
        param.put("orderColumn",orderColumnName);
        param.put("orderType",orderType);

        Map<String,Object> result = Maps.newHashMap();

        List<WxItem> wxItemList = iWxItemService.findWxItemByParam(param); //
        for(int i = 0;i<wxItemList.size();i++){
            Category category = wxItemList.get(i).getCategory();
            System.out.println("Category:"+i+":"+wxItemList.get(i).getCategory());
//            System.out.println(category.getCreateTime());
            System.out.println("Item"+i+":"+wxItemList.get(i));
            System.out.println(wxItemList.get(i).getCreateTime());
        }
        Integer count = iWxItemService.findWxItemCount();
        Integer filteredCount = iWxItemService.findWxItemCountByParam(param);

        result.put("draw",draw);
        result.put("recordsTotal",count); //总记录数
        result.put("recordsFiltered",filteredCount); //过滤出来的数量
        result.put("data",wxItemList);

        return result;
    }

    /**
     * 添加新图文消息时，加载分类
     */

    @RequestMapping(value = "/category.json",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Category>> loadCategory(HttpServletRequest request, ModelMap map){
        ServerResponse<List<Category>> response = iCategoryService.findCategory();
//        map.addAttribute("message",response);
        return response;
    }

}

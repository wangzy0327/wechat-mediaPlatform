package com.wechat.mp.controller.backend;

import com.google.common.collect.Maps;
import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.WxItem;
import com.wechat.mp.service.ICategoryService;
import com.wechat.mp.service.IWxItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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
//            param.put("title","%" + (searchValue) + "%");
//            param.put("category","%" + (searchValue) + "%");
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

    /**
     * 添加新的图文消息
     *
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String newItem(HttpServletRequest request,@RequestBody WxItem item){
        System.out.println(item.toString());
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String path = request.getScheme()+"://"
                +request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        System.out.println("path:"+path);
        System.out.println("realPath:"+realPath);
        ResponseCode state = iWxItemService.saveNewItem(path,realPath,item);
        if(state.getCode() == 0){
            return "success";
        }else if(state.getCode() == 3){
            return "duplicate";
        }else{
            return "fail";
        }
    }

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/item.json",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getWxItemInfo(Integer id){
        return iWxItemService.findWxItemById(id);
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public String editWxItem(HttpServletRequest request,@RequestBody WxItem item){
        System.out.println(item.toString());
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String path = request.getScheme()+"://"
                +request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        System.out.println("path:"+path);
        System.out.println("realPath:"+realPath);
        ResponseCode state = iWxItemService.editWxItem(path,realPath,item);
        if(state.getCode() == 0){
            return "success";
        }else if(state.getCode() == 3){
            return "duplicate";
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "del",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteWxItem(HttpServletRequest request, @RequestParam Integer id){
        return iWxItemService.delWxItem(id);
    }

}

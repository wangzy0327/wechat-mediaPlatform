package com.wechat.mp.controller.backend;

import com.google.common.collect.Maps;
import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.controller.common.ValidLogin;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/wxItem")
public class WxItemManageController {
    @Autowired
    private IWxItemService iWxItemService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String itemList(HttpSession session,ModelMap map){
        if(!ValidLogin.isLogin(session)){
            return "redirect:/";
        }
        return "backend/wxItem/list";
    }

    /**
     * 让DataTables控件加载数据
     */
    @RequestMapping(value = "/items.json",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request,HttpSession session){
        if(!ValidLogin.isLogin(session)){
            return null;
        }

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
    public ServerResponse<List<Category>> loadCategory(HttpServletRequest request,HttpSession session, ModelMap map){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
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
    public String newItem(HttpServletRequest request,HttpSession session,@RequestBody WxItem item){
        if(!ValidLogin.isLogin(session)){
            return null;
        }

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
     * 根据ID获取图文消息
     * @param id
     * @return
     */
    @RequestMapping(value = "/item.json",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getWxItemInfo(HttpSession session,Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iWxItemService.findWxItemById(id);
    }

    /**
     * 编辑图文消息
     * @param request
     * @param item
     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.POST)
    @ResponseBody
    public String editWxItem(HttpServletRequest request,HttpSession session,@RequestBody WxItem item){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
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

    /**
     * 将图文消息放入回收站
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delWxItem(HttpServletRequest request,HttpSession session, @RequestParam Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iWxItemService.delWxItem(id);
    }

    /**
     * 展示回收站中的图文消息
     * @return
     */
    @RequestMapping(value = "/delList",method = RequestMethod.GET)
    public String delList(HttpSession session){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return "backend/wxItem/delList";
    }

    /**
     * 让DataTables控件加载数据
     */
    @RequestMapping(value = "/delItems.json",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> loadDel(HttpServletRequest request,HttpSession session){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
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

        List<WxItem> wxItemList = iWxItemService.findDelWxItemByParam(param); //
        Integer count = iWxItemService.findDelWxItemCount();
        Integer filteredCount = iWxItemService.findDelWxItemCountByParam(param);

        result.put("draw",draw);
        result.put("recordsTotal",count); //总记录数
        result.put("recordsFiltered",filteredCount); //过滤出来的数量
        result.put("data",wxItemList);

        return result;
    }

    /**
     * 还原删除列表的图文消息
     */
    @RequestMapping(value = "/restore",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse restoreWxItem(HttpServletRequest request, HttpSession session,@RequestParam Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iWxItemService.restoreWxItem(id);
    }

    /**
     * 彻底删除图文消息
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteWxItem(HttpServletRequest request,HttpSession session, @RequestParam Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iWxItemService.deleteWxItem(id);
    }

    @RequestMapping(value = "/categoryList",method = RequestMethod.GET)
    public String categoryList(HttpSession session){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return "backend/wxItem/categoryList";
    }

    /**
     * 让DataTables控件加载数据
     */
    @RequestMapping(value = "/categorys.json",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> loadCategory(HttpServletRequest request,HttpSession session){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
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

        List<Category> categoryList = iCategoryService.findCategoryByParam(param);
        for (Category cate:categoryList) {
            System.out.println(cate);
        }
        Integer count = iCategoryService.findCategoryCount();
        Integer filteredCount = iCategoryService.findCategoryCountByParam(param);

        result.put("draw",draw);
        result.put("recordsTotal",count); //总记录数
        result.put("recordsFiltered",filteredCount); //过滤出来的数量
        result.put("data",categoryList);

        return result;
    }


    /**
     * 添加新的类别
     *
     */
    @RequestMapping(value = "/newCategory",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String newCategory(HttpServletRequest request,HttpSession session,@RequestBody Category category){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        System.out.println(category.toString());
        ResponseCode state = iCategoryService.saveNewCategory(category);
        if(state.getCode() == 0){
            return "success";
        }else if(state.getCode() == 3){
            return "duplicate";
        }else{
            return "fail";
        }
    }

    /**
     * 根据ID获取图文消息
     * @param id
     * @return
     */
    @RequestMapping(value = "/category.json",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getCategoryInfo(HttpSession session,Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iCategoryService.findCategoryById(id);
    }

    /**
     * 编辑新的类别
     *
     */
    @RequestMapping(value = "/editCategory",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String editCategory(HttpServletRequest request,HttpSession session,@RequestBody Category category){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        System.out.println(category.toString());
        ResponseCode state = iCategoryService.editCategory(category);
        if(state.getCode() == 0){
            return "success";
        }else if(state.getCode() == 3){
            return "duplicate";
        }else{
            return "fail";
        }
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delCategory",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse delCategory(HttpSession session,@RequestParam Integer id){
        if(!ValidLogin.isLogin(session)){
            return null;
        }
        return iCategoryService.delCategory(id);
    }

}

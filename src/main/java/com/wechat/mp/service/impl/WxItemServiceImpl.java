package com.wechat.mp.service.impl;

import com.wechat.mp.common.ResponseCode;
import com.wechat.mp.common.ServerResponse;
import com.wechat.mp.dao.CategoryMapper;
import com.wechat.mp.dao.TagMapper;
import com.wechat.mp.dao.WxItemMapper;
import com.wechat.mp.pojo.Category;
import com.wechat.mp.pojo.Tag;
import com.wechat.mp.pojo.WxItem;
import com.wechat.mp.service.IWxItemService;
import com.wechat.mp.util.PropertiesUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("iWxItemService")
public class WxItemServiceImpl implements IWxItemService {

    @Autowired
    private WxItemMapper wxItemMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private CategoryMapper categoryMapper;


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
     * 微信端查询wxItem
     * @return
     */
    public List<WxItem> findWxItemDetailByParam(Map<String,String> param){
//        for(Map.Entry<String, String> entry : param.entrySet()){
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
        return wxItemMapper.findWxItemDetailByParam(param);
    }

    /**
     * 微信端图文列表
     * @return
     */
    public List<WxItem> findWxItemDetailByParam(){
        return wxItemMapper.findWxItemDetailList();
    }

    /**
     * 获取图文消息的总数量
     * @return
     */
    public Integer findWxItemCount() {
        return wxItemMapper.findWxItemCount();
    }

    /**
     * 根据查询条件获取图文消息的数量
     * @param param
     * @return
     */
    public Integer findWxItemCountByParam(Map<String,String> param){
        return wxItemMapper.findWxItemCountByParam(param);
    }

    /**
     *
     * 微信JS-SDK分享 图文消息 根据itemId查询图文消息
     * @param itemId
     * @return
     */
    @Override
    public ServerResponse findWxItem(String itemId){
        WxItem wxItem= wxItemMapper.findWxItemByItemId(itemId);
        if(wxItem!=null){
            return ServerResponse.createBySuccess(wxItem);
        }else{
            return ServerResponse.createByError();
        }
    }

    @Transactional
    public ResponseCode saveNewItem(String path,String realPath,WxItem item){
        int lastIndex = getLastIndex(item.getUrl());
        String itemId = item.getUrl().substring(item.getUrl().lastIndexOf("/")+1, lastIndex);
        if(wxItemMapper.findByIndex(itemId)>0){
            return ResponseCode.DUPLICATE;
        }
        ResponseCode responseCode = this.H5Parse(path,item.getUrl(),realPath,item);
        if(responseCode.getCode() != 0){
            return ResponseCode.ERROR;
        }
        List<Tag> tags = item.getTags();
        Category category = item.getCategory();
        wxItemMapper.insert(item);
        System.out.println("插入后主键id:"+item.getId());
        wxItemMapper.insertWxItemCategory(item,category);
        for(int i = 0;i<tags.size();i++){
            Tag temp = tagMapper.selectByName(tags.get(i).getName());
            if(temp != null){
                tags.set(i,temp);
            }else{
                tagMapper.insert(tags.get(i));
                System.out.println("tagId:"+tags.get(i).getId());
                temp = tags.get(i);
            }
            wxItemMapper.insertWxItemTags(item,temp);
        }
        return ResponseCode.SUCCESS;
    }

    public ResponseCode H5Parse(String path,String urlStr,String realPath,WxItem wxItem){
        String h5_url = formatUrl(urlStr);
        try {
            URL url = new URL(h5_url.toString());
            InputStream input;
            input = url.openStream();   // 打开输入流
            String text = IOUtils.toString(input, "UTF-8");
            input.close();

            StringBuffer sb = new StringBuffer(text);

            int index = text.indexOf("</body>");
            // 指定位置插入js
            sb.insert(index, "<script src=\"../../js/h5-page-listen.js\"></script>");
            sb.insert(index,"<script src=\"/wechat-tools/js/weui/lib/jquery-2.1.4.js\"></script>");
            sb.insert(index, "<script src=\"https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js\"></script>");
            sb.insert(index, "<script src=\"/wechat-tools/js/weui/lib/jquery-2.1.4.js\"></script>");

//            String path = ""; // 项目地址
            StringBuffer pagePath = new StringBuffer("");
            pagePath.append("page/push/");

            int lastIndex = getLastIndex(urlStr);
//                pagePath.append(req.getParameter("t"));
            wxItem.setItemId(urlStr.substring(urlStr.lastIndexOf("/")+1, lastIndex));
            System.out.println("Url:"+wxItem.getUrl());
            pagePath.append(wxItem.getItemId());
            pagePath.append(".html");
            System.out.println(pagePath);
            wxItem.setUrl(path+"/"+pagePath);
//            wxItem.setUrl(wxItem.getUrl().replace("http","https"));

            String fileUrl = realPath+pagePath;
//            String fileUrl = path+pagePath;
            System.out.println("fileUrl:"+fileUrl);
            File dist = new File(fileUrl);
//            File local = new File(PropertiesUtil.getProperty("localPath")+wxItem.getItemId()+".html");
//            File target = new File(PropertiesUtil.getProperty("targetPath")+wxItem.getItemId()+".html");
            FileUtils.writeStringToFile(dist, sb.toString(), "UTF-8");
//            FileUtils.writeStringToFile(local, sb.toString(), "UTF-8");
//            FileUtils.writeStringToFile(target, sb.toString(), "UTF-8");

            System.out.println("文件保存成功！");

            return ResponseCode.SUCCESS;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseCode.ERROR;
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseCode.ERROR;
        }
    }

    private int getLastIndex(String h5_url_str){
        int lastIndex = h5_url_str.indexOf(".html");
        if(lastIndex == -1){
            lastIndex = h5_url_str.indexOf(".htm");
            if(lastIndex == -1){
                lastIndex = h5_url_str.length();
            }
        }
        return lastIndex;
    }

    private String formatUrl(String urlStr){
        String h5_url_str = urlStr;
//        if(urlStr.indexOf("https")<0){
//            urlStr = urlStr.replace("http","https");
//        }
        StringBuffer h5_url = new StringBuffer("");
        if(urlStr.indexOf("/aUe1Zi")>=0&&urlStr.indexOf("/m")>0){
            String newS1 = urlStr.replace("/m/","/m2/");
            System.out.println(newS1);
            h5_url = new StringBuffer("").append(newS1);
        }else{
            h5_url.append(urlStr);
        }
        return h5_url.append("?mobile=1").toString();
    }

    private boolean CheckUrl(String urlStr){
        try {
            URL url = new URL(urlStr);
            InputStream input;
            input = url.openStream();   // 打开输入流
            String text = IOUtils.toString(input, "UTF-8");
            input.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ServerResponse findWxItemById(Integer id){
        WxItem wxItem = wxItemMapper.findWxItemWithTagById(id);
        System.out.println(wxItem);
        if(wxItem == null){
            wxItem = wxItemMapper.findWxItemWithCateById(id);
            if(wxItem == null){
                return ServerResponse.createByErrorMessage("图文信息获取已删除或不存在！");
            }else{
                return ServerResponse.createBySuccess(wxItem);
            }
        }else {
            return ServerResponse.createBySuccess(wxItem);
        }
    }

    @Transactional
    public ResponseCode editWxItem(String path,String realPath,WxItem item){
        int lastIndex = getLastIndex(item.getUrl());
        String itemId = item.getUrl().substring(item.getUrl().lastIndexOf("/")+1, lastIndex);
        WxItem findItem = wxItemMapper.findWxItemByIndex(itemId);
        Category category = item.getCategory();
        List<Tag> tags = item.getTags();
        wxItemMapper.updateByPrimaryKeySelective(item);
        wxItemMapper.updateWxItemCategory(item);
        List<Integer> newAdd = new ArrayList<>();
        List<Integer> oldAdd = wxItemMapper.getTagIdByItemId(item.getId());
        if(findItem!=null && !findItem.getId().equals(item.getId()) ){
            return ResponseCode.DUPLICATE;
        }else if(findItem!=null && findItem.getId().equals(item.getId()) ){
            item.setUrl(null);
            updateTags(tags,newAdd,oldAdd,item);
            return ResponseCode.SUCCESS;
        }else{
            ResponseCode responseCode = this.H5Parse(path,item.getUrl(),realPath,item);
            if(responseCode.getCode() != 0){
                return ResponseCode.ERROR;
            }
            updateTags(tags,newAdd,oldAdd,item);
            return ResponseCode.SUCCESS;
        }
    }

    private void updateTags(List<Tag> tags,List<Integer> newAdd,List<Integer> oldAdd,WxItem item){
        for(int i = 0;i<tags.size();i++){
            Tag temp = tagMapper.selectByName(tags.get(i).getName());
            if(temp != null){
                tags.set(i,temp);
                if(wxItemMapper.isRelation(temp.getId(),item.getId()).intValue() == 0){
                    wxItemMapper.insertWxItemTags(item,temp);
                }
            }else{
                tagMapper.insert(tags.get(i));
                System.out.println("tagId:"+tags.get(i).getId());
                temp = tags.get(i);
                wxItemMapper.insertWxItemTags(item,temp);
            }
            newAdd.add(temp.getId());
        }
        for(int i = 0;i<oldAdd.size();i++){
            if(!newAdd.contains(oldAdd.get(i))){
                wxItemMapper.deleteRelation(oldAdd.get(i),item.getId());
            }
        }
    }

    public ServerResponse delWxItem(Integer id){
        int col = wxItemMapper.delWxItem(id).intValue();
        if(col>0){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("删除异常");
        }
    }


    /**
     * 根据DataTables中的参数进行查询删除图文消息
     * @param param
     * @return
     */
    public List<WxItem> findDelWxItemByParam(Map<String,String> param){ return wxItemMapper.findDelWxItemByParam(param); }

    /**
     * 获取已删除图文消息的总数量
     * @return
     */
    public Integer findDelWxItemCount() {
        return wxItemMapper.findDelWxItemCount();
    }

    /**
     * 根据查询条件获取已删除图文消息的数量
     * @param param
     * @return
     */
    public Integer findDelWxItemCountByParam(Map<String,String> param){
        return wxItemMapper.findDelWxItemCountByParam(param);
    }

    public ServerResponse restoreWxItem(Integer id){
        int col = wxItemMapper.restoreWxItem(id).intValue();
        if(col>0){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("还原异常");
        }
    }

    /**
     * 彻底删除图文消息
     */
    public ServerResponse deleteWxItem(Integer id){
        int col = wxItemMapper.deleteByPrimaryKey(id);
        if(col>0){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByErrorMessage("删除异常");
        }
    }




}

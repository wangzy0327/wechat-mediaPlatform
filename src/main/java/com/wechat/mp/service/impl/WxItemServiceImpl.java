package com.wechat.mp.service.impl;

import com.wechat.mp.common.ResponseCode;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//    @Transactional
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
            sb.insert(index, "<script src=\"/wechat-tools/js/h5-page-listen.js\"></script>");

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

            String fileUrl = realPath+pagePath;
            System.out.println("fileUrl:"+fileUrl);
            File dist = new File(fileUrl);
            File local = new File(PropertiesUtil.getProperty("localPath")+wxItem.getItemId()+".html");
//            File target = new File(PropertiesUtil.getProperty("targetPath")+wxItem.getItemId()+".html");
            FileUtils.writeStringToFile(dist, sb.toString(), "UTF-8");
            FileUtils.writeStringToFile(local, sb.toString(), "UTF-8");
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
        StringBuffer h5_url = new StringBuffer();
        if (h5_url_str.indexOf("http://") < 0 && h5_url_str.indexOf("https://") < 0)
            h5_url = new StringBuffer("http://").append(h5_url_str);
        else{
            h5_url.append(h5_url_str);
        }
        return h5_url.append("?mobile=1").toString();
    }


}

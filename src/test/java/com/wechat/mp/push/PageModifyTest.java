package com.wechat.mp.push;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;

public class PageModifyTest {

    @Test
    public void ModifyPushPage(){
//        String s = "D:\\test";
        String s = "D:\\project\\wechat-tools\\target\\wechat-tools\\page\\push";
        traverseFolder(s);
    }

    private void traverseFolder(String path){
        File dir = new File(path);
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        handlerFile(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private void handlerFile(String fileName){
        try {
            FileInputStream input = new FileInputStream(fileName);
            int n = 0;
            String text = IOUtils.toString(input, "UTF-8");
            input.close();


            text = text.replace("<script src=\"../../js/share.js\"></script>","");
            text = text.replace("<script src=\"../../js/initWxConfig.js\"></script>","");
            text = text.replace("<script src=\"../../js/h5-page-listen.js\"></script>","");
            text = text.replace("<script src=\"../../js/jweixin-1.2.0.js\"></script>","");
            text = text.replace("<script src=\"https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js\"></script>","");
            text = text.replace("<script src=\"/wechat-tools/js/weui/lib/jquery-2.1.4.js\"></script>","");

            StringBuffer sb = new StringBuffer(text);
            int index = text.indexOf("</body>");
            if(index == -1){
                index = text.indexOf("</html>");
            }
            // 指定位置插入js
            sb.insert(index, "<script src=\"../../js/share.js\"></script>");
            sb.insert(index, "<script src=\"../../js/initWxConfig.js\"></script>");
            sb.insert(index, "<script src=\"../../js/h5-page-listen.js\"></script>");
            sb.insert(index,"<script src=\"http://res.wx.qq.com/open/js/jweixin-1.2.0.js\"></script>");
            sb.insert(index, "<script src=\"https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js\"></script>");
            sb.insert(index, "<script src=\"/wechat-tools/js/weui/lib/jquery-2.1.4.js\"></script>");
            File file = new File(fileName);
            FileUtils.writeStringToFile(file, sb.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

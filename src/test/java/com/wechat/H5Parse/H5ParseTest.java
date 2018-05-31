package com.wechat.H5Parse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class H5ParseTest {

    public static String getUrlContent(String urlStr,String charset){
//        StringBuilder sb = new StringBuilder();
        String text = "";
        try {
            URL url = new URL(urlStr);
            InputStream input;
            input = url.openStream();   // 打开输入流
            text = IOUtils.toString(input, charset);
            input.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void saveH5Page(String str){
        int lastIndex = getLastIndex(str);
        String fileName = str.substring(str.lastIndexOf("/")+1, lastIndex);
        System.out.println("fileName:"+fileName);
        String text = getUrlContent(str,"UTF-8");
        StringBuffer sb = new StringBuffer(text);
        int index = text.indexOf("</body>");
        // 指定位置插入js
        sb.insert(index, "<script src=\"../../js/h5-page-listen.js\"></script>");

//                String path = req.getSession().getServletContext().getRealPath("/"); // 项目地址
        String path = ""; // 项目地址
        StringBuffer pagePath = new StringBuffer("");
        pagePath.append("src/main/webapp/page/dist/h5_page/");
//                pagePath.append(req.getParameter("t"));
        pagePath.append(fileName);
        pagePath.append(".html");

        System.out.println(path+pagePath);
        try {
            File dist = new File(path + pagePath);
            FileUtils.writeStringToFile(dist, sb.toString(), "UTF-8");
            System.out.println("文件保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getLastIndex(String h5_url_str){
        int lastIndex = h5_url_str.indexOf(".html");
        if(lastIndex == -1){
            lastIndex = h5_url_str.indexOf(".htm");
            if(lastIndex == -1){
                lastIndex = h5_url_str.length();
            }
        }
        return lastIndex;
    }

    private static String formatUrl(String urlStr){
        String h5_url_str = urlStr;
        StringBuffer h5_url = new StringBuffer();
        if(CheckUrl(h5_url_str+"?mobile=1")){
            return h5_url_str+"?mobile=1";
        }else{
            if(h5_url.indexOf("/m/")>=0){
                String newS1 = h5_url.toString().replace("/m/","/m2/");
                System.out.println(newS1);
                h5_url = new StringBuffer("").append(newS1);
            }
            return h5_url.append("?mobile=1").toString();
        }
    }

    private static boolean CheckUrl(String urlStr){
        try {
            URL url = new URL(urlStr);
            InputStream input;
            input = url.openStream();   // 打开输入流
            String text = IOUtils.toString(input, "UTF-8");
            System.out.println(text);
            input.close();
            Document document = Jsoup.parse(text);
            Elements pageContents = document.getElementsByClass("page-list");
            for(int i = 0;i<pageContents.size();i++){
                System.out.println(pageContents.get(i).ownText());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getMatcherSubstrs(String destStr, String regexStr){
        Pattern p = Pattern.compile(regexStr);
        Matcher m = p.matcher(destStr);
        List<String> result = new ArrayList<>();
        while(m.find()){
            System.out.println("group:"+m.group());
            System.out.println(m.group(1));
            result.add(m.group(1));
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "http://a2.rabbitpre.com/m2/aUe1Zi9OhR";
        String formatStr = formatUrl(s);
        saveH5Page(formatStr);
    }


}

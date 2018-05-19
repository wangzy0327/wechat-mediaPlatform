package com.wechat.mp.test;

import com.wechat.mp.common.DateUtil;
import com.wechat.mp.util.StrHexUtil;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccessToken;
import com.wechat.mp.util.wechat.AccountFans;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FansListInfo {
    public static void main(String[] args) throws ParseException {
//        List<String> list = WxApiClient.getAccountFansList();


        List<String> list = new ArrayList<>();
        String [] openIdArr = {
                "ohIIkv0EWHRMIFNgCn9iM4obFktI",
                "ohIIkv2yQrt-o1QVnf5U1V2HgUQI",
                "ohIIkv1EQR3QlLDx1nDZFA-eJ9Rw",
                "ohIIkv2d--Ndb-yeD1xwCnbAKDyI",
                "ohIIkvyWrBmozKAHehHjI9Pb4zNs",
                "ohIIkvzAbG-xRrXO1Fso2uH97EdA",
                "ohIIkv7W02M5XXv7OshLnghnXFdg",
                "ohIIkvzGeXvrBfpPvY6R-F-hGbtk",
                "ohIIkv7YSqaQhDIwKYo-ldf-dkc4",
        "ohIIkv6X9n_yeANlLqEk2JpMavwQ",
        "ohIIkvzRa0Ydg9Am1HNQhZTGOS6o",
        "ohIIkv5987LlIEThyGbnacUnD1PY",
        "ohIIkv91Vv6UGB9C_bty0NT-EgJc",
        "ohIIkvw0WSTNTC60NORfdvKlGQ2U",
        "ohIIkv5dPrjtGbbTAwgeV4isWXeg",
        "ohIIkv7k85OlLDFwStncbQTulgc8",
        };
        list.addAll(Arrays.asList(openIdArr));
        List<AccountFans> fansList = getFansInfo(list);

//        String coolString = "击剑___婧\uD83E\uDD3A\uD83E\uDD3A\uD83E\uDD3A";
//        byte [] bytes = {-27,-121,-69,-27,-119,-111,95,95,95,-27,-87,-89,-16,-97,-92,-70,-16,-97,-92,-70,-16,-97,-92,-70};
//        String s = new String(bytes);
//        System.out.println(s);
//        byte[] b = s.getBytes(Charset.forName("UTF-8"));

//        byte[] byteArray = coolString.getBytes();
//        System.out.println("byteArray"+byteArray);
//        byte[] b = coolString.getBytes(Charset.forName("UTF-8"));
//        System.out.println("b"+b);

//        String hexStr = "E7AC91E582B2E6B19FE6B996";
//        String str = "笑傲江湖";
//        String s = StrHexUtil.hexStr2Str("E587BBE589915F5F5FE5A9A7F09FA4BAF09FA4BAF09FA4BA");
//        System.out.println(s);
//        System.out.println(StrHexUtil.hexStr2Str("E7AC91E582B2E6B19FE6B996").equals(str));
//        System.out.println(StrHexUtil.str2HexStr(str).equals(hexStr));

//        byte[] bytes = {-25,-84,-111,-27,-126,-78,-26,-79,-97,-26,-71,-106};
//        System.out.println(new String(bytes));
//        String s = "笑傲江湖";
//        byte[] b = null;
//        try {
//            b = s.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        for(int i = 0;i<b.length;i++){
//            if(i == 0)
//                System.out.print("{"+b[i]);
//            else if(i>0&&i<b.length - 1)
//                System.out.print(","+b[i]);
//            else if(b.length - 1>0&&i == b.length - 1){
//                System.out.println(","+b[i]+"}");
//            }else if(b.length - 1 == 0){
//                System.out.println("}");
//            }
//        }
//        System.out.println(System.currentTimeMillis());

    }
    public static List<AccountFans> getFansInfo(List<String> list){
        List<AccountFans> fansList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            String openId = list.get(i);
            System.out.println("第"+(i+1)+"个openId: "+openId);
            AccountFans fans = WxApiClient.getAccountFans(openId);
            fansList.add(fans);
//            System.out.println(fans.getSubscribeTime());
            System.out.println(fans);
        }
        return fansList;
    }

}

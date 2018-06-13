package com.wechat.mp.dao;

import com.wechat.mp.pojo.Tag;
import com.wechat.mp.pojo.WxItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class WxItemMapperTest {

    @Autowired
    private WxItemMapper wxItemMapper;

    @Test
    public void findWxItemWithTagById() throws Exception {
        Integer id = 1000;
        WxItem wxItem = wxItemMapper.findWxItemWithTagById(id);
        List<Tag> tags = wxItem.getTags();
        System.out.println(wxItem);
        for(int i = 0;i<tags.size();i++){
            System.out.println(tags.get(i));
        }
    }

}
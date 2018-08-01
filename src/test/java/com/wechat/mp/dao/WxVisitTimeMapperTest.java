package com.wechat.mp.dao;

import com.wechat.mp.pojo.WxItemRead;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class WxVisitTimeMapperTest {

    @Autowired
    private WxVisitTimeMapper wxVisitTimeMapper;

    @Test
    public void getFansRead() throws Exception {
        List<WxItemRead> wxItemReads = wxVisitTimeMapper.selectAll();
        for(int i = 0;i<wxItemReads.size();i++){
            System.out.println(wxItemReads.get(i));
        }
//        System.out.println(Arrays.deepToString(wxItemReads.toArray()));
    }

}
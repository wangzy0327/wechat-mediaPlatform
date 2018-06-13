package com.wechat.mp.dao;

import com.wechat.mp.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:applicationContext-datasource.xml"})
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void findInterestList() throws Exception {
        List<Category> categorys = categoryMapper.findInterestList();
        System.out.println(Arrays.deepToString(categorys.toArray()));
    }

}
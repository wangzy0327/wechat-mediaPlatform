package com.wechat.mp.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CSVUtilTest {
    @Test
    public void exportCsv() throws Exception {
        List<String> list = new ArrayList(Arrays.asList("Tom,18,100", "Jerry,19,95", "Mike,18,90"));
        boolean check = CSVUtil.exportCsv("test.csv",list);
        System.out.println(check);
    }

    @Test
    public void importCsv() throws Exception {
        List<String> list = CSVUtil.importCsv("test.csv");
        System.out.println(Arrays.deepToString(list.toArray()));
    }

}
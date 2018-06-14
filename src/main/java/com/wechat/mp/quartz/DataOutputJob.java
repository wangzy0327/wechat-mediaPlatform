package com.wechat.mp.quartz;

import com.wechat.mp.service.IDataOutputService;
import com.wechat.mp.util.CSVUtil;
import com.wechat.mp.util.PropertiesUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

public class DataOutputJob extends QuartzJobBean {

    private IDataOutputService iDataOutputService;

    public void setiDataOutputService(IDataOutputService iDataOutputService) {
        this.iDataOutputService = iDataOutputService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        String webPath = servletContext.getRealPath("/");
        System.out.println("webPath:"+webPath);
        String fileName = webPath+"/output.csv";
        System.out.println("fileName:"+fileName);
        boolean check = CSVUtil.exportCsv(fileName,iDataOutputService.getFansRead());
        System.out.println("output csv:"+check);
    }
}

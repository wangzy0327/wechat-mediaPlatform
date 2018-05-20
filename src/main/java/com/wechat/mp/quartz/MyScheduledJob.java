package com.wechat.mp.quartz;

import com.wechat.mp.service.IWxQuartzService;
import com.wechat.mp.util.WxApiClient;
import com.wechat.mp.util.wechat.AccountFans;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyScheduledJob extends QuartzJobBean {

    private IWxQuartzService iWxQuartzService;

    public void setiWxQuartzService(IWxQuartzService iWxQuartzService) {
        this.iWxQuartzService = iWxQuartzService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("MyScheduledJob Executes!" + sf.format(date));
        List<String> openIdList = WxApiClient.getAccountFansList();
        List<AccountFans> fansList = this.iWxQuartzService.getFansInfo(openIdList);
        this.iWxQuartzService.updateFans(fansList);
    }
}

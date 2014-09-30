package com.joinsoft.mobile.cms.task;

import com.joinsoft.mobile.cms.service.RewardService;
import com.joinsoft.online.signin.ws.csb.TrafficService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by wangxulong on 14-9-23.
 */
public class SyncTrafficTask {
    private Logger logger = LoggerFactory.getLogger(SyncTrafficTask.class);
    @Resource
    private TrafficService trafficService;

    public void execute() {

        logger.info("开始申请流量:{}", new Date());
        long start = System.currentTimeMillis();
        //trafficService.applyTrafficFromTelecom();
        long times = System.currentTimeMillis() - start;
        logger.info("申请流量结束，耗时:{}", times);
    }
}

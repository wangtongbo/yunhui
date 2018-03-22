package com.lakala.ls.ms.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.lakala.ls.ms.service.CheckClearService;
import com.lakala.ls.ms.service.CheckSettleService;
import com.lakala.ls.ms.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by whmyy on 17/5/11.
 */
@Component
public class ScheduledTaskConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int REDIS_OUT_TIME_SECONDS = 30;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CheckClearService checkClearService;

    @Autowired
    private CheckSettleService checkSettleService;

    /**
     * 执行对账任务
     * @throws InterruptedException
     */
//    @Scheduled(cron = "0 30 0 * * *")
    public void checkJob() throws InterruptedException {
        // 多服务器,保证只有一台服务器执行定时任务
        if(!checkLock("checkJob")) {
            return;
        }

        String checkDate = DateUtil.getDateBeforeXDay(new Date(), 1, DateUtil.format1);
        logger.info("===================对账处理开始,日期[{}]=====================", checkDate);
        checkClearService.check(checkDate);
        logger.info("===================对账处理完成=====================");
    }

    /**
     * 执行生产日报告任务
     * @throws InterruptedException
     */
//    @Scheduled(cron = "0 40 0 * * *")
    public void settleByDayJob() throws InterruptedException {
        // 多服务器,保证只有一台服务器执行定时任务
        if(!checkLock("settleByDayJob")) {
            return;
        }

        String checkDate = DateUtil.getDateBeforeXDay(new Date(), 1, DateUtil.format1);
        logger.info("===================供货商-产品日报生成开始,日期[{}]=====================", checkDate);
        checkSettleService.settleByEveryday(checkDate);
        logger.info("===================供货商-产品日报生成完成=====================");
    }

    private boolean checkLock(String key) {
        BoundHashOperations<String, String, String> bos = stringRedisTemplate.boundHashOps(key);
        if (bos.putIfAbsent("value", key)) {
            bos.expire(REDIS_OUT_TIME_SECONDS, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }
}

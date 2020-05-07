package org.springboot.chapter.chapter01.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 商品添加任务实现类
 */
@Slf4j
public class GoodAddTask extends QuartzJobBean {


    /**
     * 定时任务执行方法
     * @param context
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("商品添加完成后执行任务，任务时间：{}",new Date());
    }
}

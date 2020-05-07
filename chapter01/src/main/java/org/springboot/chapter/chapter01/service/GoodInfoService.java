package org.springboot.chapter.chapter01.service;

import org.quartz.*;
import org.springboot.chapter.chapter01.entity.GoodInfoEntity;
import org.springboot.chapter.chapter01.job.GoodAddTask;
import org.springboot.chapter.chapter01.job.GoodStockCheckTask;
import org.springboot.chapter.chapter01.repository.GoodeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodInfoService {
    private final Scheduler scheduler;

    private final GoodeInfoRepository goodeInfoRepository;

    @Autowired
    public GoodInfoService(Scheduler scheduler, GoodeInfoRepository goodeInfoRepository) {
        this.scheduler = scheduler;
        this.goodeInfoRepository = goodeInfoRepository;
    }

    public Long save(GoodInfoEntity good) throws Exception {
        goodeInfoRepository.save(good);
// 构建创建商品定时任务
        builderCreateGoodTask();
// 构建商品库存定时任务
        buildGoodStockTask();;

        return good.getId();
    }

    public void builderCreateGoodTask() throws Exception {
        //1分钟后开始
        long startTime = System.currentTimeMillis() + 1000 * 60;
        String taskName = UUID.randomUUID().toString();
        String group = GoodAddTask.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodAddTask.class).withIdentity(taskName,group).build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(taskName,group).startAt(new Date(startTime)).build();
//       将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail,trigger);
    }

    /**
     * 构建商品库存定时任务
     * @throws Exception
     */
    public void buildGoodStockTask() throws Exception
    {
        //任务名称
        String name = UUID.randomUUID().toString();
        //任务所属分组
        String group = GoodStockCheckTask.class.getName();
//        30秒执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodStockCheckTask.class).withIdentity(name,group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

}

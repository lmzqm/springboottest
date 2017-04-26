package com.lmzqm.Controller;

import com.lmzqm.Extention.MyExcention;
import com.lmzqm.Task.AsyncTasks;
import com.lmzqm.Task.SayTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by lmzqm on 2017/4/25.
 *
 * 动态修改定时任务的操作
 * http://blog.csdn.net/zhao1949/article/details/54863624
 */
@RestController
@RequestMapping(value = "task")
public class TaskViewController {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private AsyncTasks asyncTasks;



    private String cronStr = "*/5 * * * * ?";

    @RequestMapping(value = "/start")
    public String startCron(){

        threadPoolTaskScheduler.schedule(new SayTask(), new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                return new CronTrigger(cronStr).nextExecutionTime(triggerContext);
            }
        });

        return "success";
    }

    @RequestMapping(value = "/changeCronStr")
    public String changeCronStr(){
        cronStr = "*/12 * * * * ?";
        return "success";
    }

    @RequestMapping(value = "async")
    public String doAsyncTask() throws Exception{

      Future<String> task1 =  asyncTasks.doTaskOne();

      if (task1 instanceof AsyncResult){
          //说明是AysncResult的

      }


       AsyncResult<String> task2 =  asyncTasks.doTaskTwo();
       Future<String> task3 =  asyncTasks.doTaskThree();

       task2.addCallback(new SuccessCallback<String>() {
           @Override
           public void onSuccess(String s) {

           }
       }, new FailureCallback() {
           @Override
           public void onFailure(Throwable throwable) {

           }
       });

       while (true){
           if(task1.isDone() && task2.isDone() && task3.isDone()){
               break;
           }
       }

        return "success";
    }


}

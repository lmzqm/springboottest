package com.lmzqm.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 创建定时任务
 *
 * 首先在主类中加入@EnableScheduling注解，启用定时任务配置
 *
 * 然后就是创建任务实现
 *
 * http://blog.csdn.net/prisonbreak_/article/details/49180307
 *
 * @Scheduled 注解的讲解
 *
 * fixedRate = 5000 表示每5秒钟执行任务 以下方法将以一个固定速率5s来调用一次执行，这个周期是以上一个任务开始时间为基准，从上一任务开始执行后5s再次调用：
 *
 * fixedDealy = 5000 延迟5秒后再执行(也是循环执行的操作) 以下的方法将以一个固定延迟时间5秒钟调用一次执行，这个周期是以上一个调用任务的完成时间为基准，在上一个任务完成之后，5s后再次执行
 *
 * initialDelay = 1000,fixedRate = 5000 第一次延迟1秒执行，之后按fixedRate规则每5秒执行一次
 *
 * cron 通过cron表达式定义规则
 * @Scheduled(cron="0 0 2 * * ?")
 *
 * 按照顺序分别是：
 *
 * 秒（0-59） 分钟（0-59） 小时（0-23） 天（0-31）月（0-11）
 * 天（星期）（1-7 1=SUN 或 SUN MON TUE WED THU FRI SAT）
 * 年份（1970-2099）
 *
 * 其中每个元素可以是一个值，一个连续的区间 9-12 一个时间间隔（8-18/4）表示每隔4小时 一个列表（1，3，5）由于日期中的天和星期中的日期这两个元素是互斥的，必须有一个设置为？
 *
 * 0 0 10，14，16 * * ？每天上午10点，下午2点，4点
 *
 * 0 0/30 9-17 * * ? 朝九晚五工作时间内没半小时
 *
 * *表示所有可能的值
 * / 表示数值的增量 0/15 表示0分组开始，每15分钟执行一次
 *
 * ？ 仅用于天（月）和天 两个子表达式 表示不指定值
 * L 仅被用于天（月）和天（星期） 两个子表达式 表示Last的意思 6L 倒数第六天 FRIL 表示这个月的最后一个星期五
 *
 *
 *
 *
 *
 *
 *
 * Created by lmzqm on 2017/4/25.
 */
@Component
public class ScheduleTasks {

    private static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 50 15 * * ?")
    public void reportCurrentTime(){
        System.out.println("现在时间："+dateformat.format(new Date()));
    }



}

package com.lmzqm.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lmzqm on 2017/4/25.
 */
public class SayTask implements Runnable {

    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    @Override
    public void run() {
        System.out.println("现在的时间："+df.format(new Date()));
    }
}

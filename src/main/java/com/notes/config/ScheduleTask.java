//package com.notes.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import java.time.LocalDateTime;
//
///**
// * 文件描述 定时任务
// **/
//@Configuration
//public class ScheduleTask {
//    @Scheduled(cron = "0/5 0 0 * * ?")
//    public void configureTasks2() {
//        System.out.println("清除文件上传结果缓存key开始执行gggg: " + LocalDateTime.now());
//    }
//}
package com.notes.java.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 文件描述 sum求和
 **/
public class SumTask extends RecursiveTask <Integer>{
    private static final Integer THRESHOLD = 1000;
    private int start;
    private int end;
    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer sum = 0;
        boolean isOk = (end - start) <= THRESHOLD;
        if(isOk) {
            for(int i = start; i <= end; i ++) {
                sum += i;
            }
            return sum;
        }

        int middle = (end + start) / 2;
        //子任务递归
        SumTask sumSubTask = new SumTask(start, middle);
        SumTask sumSubTask1 = new SumTask(middle + 1, end);

        //fork子任务
        sumSubTask.fork();
        sumSubTask1.fork();

        //join子任务
        Integer join = sumSubTask.join();
        Integer join1 = sumSubTask1.join();

        sum = join + join1;
        //计算结果
        return sum;
    }
}

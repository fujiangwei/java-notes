package com.notes.java.thread.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 文件描述 sum求和
 **/
public class SumTask2 extends RecursiveTask <Integer>{
    private static final Integer THRESHOLD = 1000;
    private int start;
    private int end;
    public SumTask2(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        Integer sum = 0;
        boolean isOk = end - start <= THRESHOLD;
        if(isOk) {
            for(int i = start; i <= end; i ++) {
                sum += i;
            }
//            System.out.println(String.format("compute %d-%d = %d", start, end, sum));
            return sum;
        }

        //除以2
        int middle = (end + start) / 2;
        //子任务递归
//        System.out.println(String.format("fork %d-%d => %d-%d&%d-%d", start, end, start, middle - 1, middle, end));
        SumTask2 sumSubTask = new SumTask2(start, middle - 1);
        SumTask2 sumSubTask1 = new SumTask2(middle, end);

        //fork子任务
        invokeAll(sumSubTask, sumSubTask1);

        //join子任务
        Integer join = sumSubTask.join();
        Integer join1 = sumSubTask1.join();

        sum = join + join1;
        //计算结果
        return sum;
    }
}

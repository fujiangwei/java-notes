package com.notes.java.thread.threadpoolexecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.notes.domain.User;
import com.notes.utils.UserUtil;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 文件描述
 **/
public class MyDemo {

    public static void main(String[] args) throws Exception{
        List<User> users = UserUtil.getUsers(100000);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("1");
        Map<Integer, List<User>> listMap = users.stream().collect(Collectors.groupingBy(User::getId));
        stopWatch.stop();
        System.out.println(">>>>>>>> 耗时： " + stopWatch.getTotalTimeMillis() + "ms");
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start("2");
        Map<Integer, List<User>> listMap2 = Maps.newHashMap();
        for (int i = 0; i < users.size(); i ++) {
            List<User> users2 = Lists.newArrayList();
            User e = users.get(i);
            users2.add(e);
            listMap2.put(e.getId(), users2);
        }
        stopWatch2.stop();
        System.out.println(">>>>>>>> 耗时2： " + stopWatch2.getTotalTimeMillis() + "ms");

        StopWatch stopWatch3 = new StopWatch();
        stopWatch3.start("3");
        Map<Integer, List<User>> listMap3 = users.parallelStream().collect(Collectors.groupingBy(User::getId));
        stopWatch3.stop();
        System.out.println(">>>>>>>> 耗时3： " + stopWatch3.getTotalTimeMillis() + "ms");

        StopWatch stopWatch4 = new StopWatch();
        stopWatch4.start("4");
        //使用线程池，具体corePoolSize：活跃线程数，maximumPoolSize最大线程数根据需要自己定义
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(4,8,
                2000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        List<Callable<Map<Integer, List<User>>>> tasks = Lists.newArrayList();
        for (int i = 0; i < 10; i ++) {
            List<User> subUsers = users.subList(i * 10000, 10000 * (i + 1));
            MyRunner myRunner = new MyRunner(subUsers);
            tasks.add(myRunner);
        }
        Map<Integer, List<User>> all = Maps.newHashMap();
        List<Future<Map<Integer, List<User>>>> futures = poolExecutor.invokeAll(tasks);
        for (int i = 0; i < futures.size(); i ++) {
            Map<Integer, List<User>> single = futures.get(i).get();
            all.putAll(single);
        }
        stopWatch4.stop();
        poolExecutor.shutdown();
        System.out.println(">>>>>>>> 耗时4： " + stopWatch4.getTotalTimeMillis() + "ms" + "  " + all.size());

        StopWatch stopWatch5 = new StopWatch();
        stopWatch5.start("5");
        //使用线程池，具体corePoolSize：活跃线程数，maximumPoolSize最大线程数根据需要自己定义
        ThreadPoolExecutor poolExecutor2 = new ThreadPoolExecutor(4,8,
                2000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

        List<Future<Map<Integer, List<User>>>> tasks2 = Lists.newArrayList();
        for (int i = 0; i < 10; i ++) {
            Future<Map<Integer, List<User>>> future = poolExecutor2.submit(new MyRunner(users.subList(i * 10000, (i + 1) * 10000)));
            tasks2.add(future);
        }
        Map<Integer, List<User>> all2 = Maps.newHashMap();
        for (int i = 0; i < tasks2.size(); i ++) {
            Map<Integer, List<User>> single2 = tasks2.get(i).get();
            all2.putAll(single2);
        }
        stopWatch5.stop();
        poolExecutor2.shutdown();
        System.out.println(">>>>>>>> 耗时5： " + stopWatch5.getTotalTimeMillis() + "ms" + "  " + all2.size());

    }

    public static class MyRunner implements Callable<Map<Integer, List<User>>> {

        private List<User> users;

        public MyRunner(List<User> users) {
            this.users = users;
        }

        @Override
        public Map<Integer, List<User>> call() throws Exception {

            Map<Integer, List<User>> listMap = Maps.newHashMap();
            for (int i = 0; i < users.size(); i ++) {
                List<User> userList = Lists.newArrayList();
                User e = this.users.get(i);
                userList.add(e);
                listMap.put(e.getId(), userList);
            }
            return listMap;
        }
    }
}

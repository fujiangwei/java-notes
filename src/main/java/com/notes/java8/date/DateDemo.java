package com.notes.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.TimeZone;

/**
 * descripiton:
 * 最新JDBC映射将把数据库的日期类型和Java 8的新类型关联起来：
    SQL -> Java
    --------------------------
    date -> LocalDate
    time -> LocalTime
    timestamp -> LocalDateTime
    再也不会出现映射到java.util.Date其中日期或时间某些部分为0的情况了。
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/6
 * @time: 10:48
 * @modifier:
 * @since:
 */
public class DateDemo {

    /**
     * 日期格式
     */
    private final static String PATTERN_1 = "YYYY-MM-DD";

    private final static String PATTERN_2 = "yyyy-MM-dd hh:mm:ss";

    private final static String PATTERN_3 = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
//        clock();
        localDate();
//        localTime();
//        localDateTime();
//        zoneDateTime();
//        duration();
//        date2Str();
//        str2Date();

//        System.out.println(date2LocalDateTime());
//        System.out.println(localDateTime2Date());

//        System.out.println(localDateTime2LocalDate());
//        System.out.println(localDateTime2LocalTime());
//        System.out.println(localDate2Date());
    }

    /**
     * Clock类，它通过指定一个时区，然后就可以获取到当前的时刻，日期与时间。
     * Clock可以替换System.currentTimeMillis()与TimeZone.getDefault()。
     */
    public static void clock() {
        // Get the system clock as UTC offset
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.getZone());
        System.out.println(TimeZone.getDefault());

        //获取时区
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock1.getZone());
        System.out.println(ZoneId.systemDefault());

        //获取当前时间
        System.out.println(clock.instant());
        System.out.println(new Date());

        //获取毫秒数
        System.out.println(clock.millis());
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 获取当前日期
     */
    public static void localDate() {

        LocalDate now = LocalDate.now();
        LocalDate now1 = LocalDate.now(Clock.systemUTC());

        System.out.println(now);
        System.out.println(now1);

        LocalDate of = LocalDate.of(2019, 3, 6);
        //严格按照ISO yyyy-MM-dd验证，03写成3都不行
        LocalDate parse = LocalDate.parse("2019-03-06");
        System.out.println(of);
        System.out.println(parse);

        System.out.println("**************now****************");

        //当前开始时间
        System.out.println(now.atStartOfDay());
        //当月第一天日期
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));
        //本月第二天日期
        System.out.println(now.withDayOfMonth(2));
        //当月最后一天
        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(now.getDayOfMonth());
        //当月下一天
        System.out.println(now.plusDays(1));
        //当月上一天
        System.out.println(now.minusDays(1));
        System.out.println(now.getDayOfWeek());
        //当月下一周
        System.out.println(now.plusWeeks(1));
        //当月上一周
        System.out.println(now.minusWeeks(1));
        System.out.println(now.getMonth() + "-" + now.getMonthValue());
        //当月下一个月
        System.out.println(now.plusMonths(1));
        //当月上一个月
        System.out.println(now.minusMonths(1));

        //时间比较
        System.out.println(now.isEqual(LocalDate.of(2019, 03, 06)));
    }

    /**
     * 获取当前时间
     */
    public static void localTime() {

        LocalTime now = LocalTime.now();
        //指定时区
        LocalTime now1 = LocalTime.now(Clock.system(ZoneId.systemDefault()));
        LocalTime now2 = LocalTime.now(Clock.systemUTC());

        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);

        System.out.println("************now************");
        //清除毫秒位
        System.out.println(now.withNano(0));
        //获取当前的小时
        System.out.println(now.getHour());
        //解析时间时间也是按照ISO格式识别，但可以识别以下3种格式： 12:00 12:01:02 12:01:02.345
        System.out.println(LocalTime.parse("11:58:12"));

        //时间比较
        LocalTime other = LocalTime.of(13, 45, 59);
        System.out.println(now.isBefore(other));
        System.out.println(now.isAfter(other));
    }

    /**
     * 获取当前日期和时间
     */
    public static void localDateTime() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime now1 = LocalDateTime.now(Clock.system(ZoneId.systemDefault()));
        LocalDateTime now2 = LocalDateTime.now(Clock.systemUTC());

        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
    }

    /**
     * 特定时区的日期/时间
     */
    public static void zoneDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime now1 = ZonedDateTime.now(Clock.systemUTC());
        ZonedDateTime now2 = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
    }

    /**
     * 秒与纳秒级别上的一段时间
     */
    public static void duration() {

        LocalDateTime from = LocalDateTime.of(2018, Month.OCTOBER, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2019, Month.MARCH, 6, 23, 59, 59);

        Duration between = Duration.between(from, to);
        System.out.println(between.toDays());
        System.out.println(between.toHours());
    }

    /**
     * 日期格式
     */
    public static void date2Str() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.format(DateTimeFormatter.ofPattern(PATTERN_1)));
        System.out.println(now.format(DateTimeFormatter.ofPattern(PATTERN_2)));
        System.out.println(now.format(DateTimeFormatter.ofPattern(PATTERN_3)));
    }

    /**
     * 日期字符串转为LocalDateTime格式
     */
    public static void str2Date() {

        String dateStr2 = "2019-03-06 12:22:45";
        LocalDateTime parse2 = LocalDateTime.parse(dateStr2, DateTimeFormatter.ofPattern(PATTERN_3));
        System.out.println(parse2);

    }

    /**
     * Date转换为LocalDateTime
     * @return LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime() {
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return localDateTime;
    }

    /**
     * LocalDateTime转换为Date
     * @return Date
     */
    public static Date localDateTime2Date() {
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return date;
    }

    /**
     * LocalDateTime转换为LocalDate
     * @return LocalDate
     */
    public static LocalDate localDateTime2LocalDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = now.toLocalDate();

        return localDate;
    }

    /**
     * LocalDateTime转换为LocalTime
     * @return LocalTime
     */
    public static LocalTime localDateTime2LocalTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime LocalTime = now.toLocalTime();

        return LocalTime;
    }

    /**
     * LocalDate转换为Date
     * @return Date
     */
    public static Date localDate2Date() {
        LocalDate now = LocalDate.now();
        Instant instant = now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return date;
    }

}

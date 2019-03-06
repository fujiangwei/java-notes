##  Date
> LocalDatetime

* adjustInto	调整指定的Temporal和当前LocalDateTime对
* atOffset	结合LocalDateTime和ZoneOffset创建一个
* atZone	结合LocalDateTime和指定时区创建一个ZonedD
* compareTo	比较两个LocalDateTime
* format	格式化LocalDateTime生成一个字符串
* from	转换TemporalAccessor为LocalDateTi
* get	得到LocalDateTime的指定字段的值
* getDayOfMonth	得到LocalDateTime是月的第几天
* getDayOfWeek	得到LocalDateTime是星期几
* getDayOfYear	得到LocalDateTime是年的第几天
* getHour	得到LocalDateTime的小时
* getLong	得到LocalDateTime指定字段的值
* getMinute	得到LocalDateTime的分钟
* getMonth	得到LocalDateTime的月份
* getMonthValue	得到LocalDateTime的月份，从1到12
* getNano	得到LocalDateTime的纳秒数
* getSecond	得到LocalDateTime的秒数
* getYear	得到LocalDateTime的年份
* isAfter	判断LocalDateTime是否在指定LocalDateT
* isBefore	判断LocalDateTime是否在指定LocalDateT
* isEqual	判断两个LocalDateTime是否相等
* isSupported	判断LocalDateTime是否支持指定时间字段或单元
* minus	返回LocalDateTime减去指定数量的时间得到的值
* minusDays	返回LocalDateTime减去指定天数得到的值
* minusHours	返回LocalDateTime减去指定小时数得到的值
* minusMinutes	返回LocalDateTime减去指定分钟数得到的值
* minusMonths	返回LocalDateTime减去指定月数得到的值
* minusNanos	返回LocalDateTime减去指定纳秒数得到的值
* minusSeconds	返回LocalDateTime减去指定秒数得到的值
* minusWeeks	返回LocalDateTime减去指定星期数得到的值
* minusYears	返回LocalDateTime减去指定年数得到的值
* now	返回指定时钟的当前LocalDateTime
* of	根据年、月、日、时、分、秒、纳秒等创建LocalDateTi
* ofEpochSecond	根据秒数(从1970-01-0100:00:00开始)创建L
* ofInstant	根据Instant和ZoneId创建LocalDateTim
* parse	解析字符串得到LocalDateTime
* plus	返回LocalDateTime加上指定数量的时间得到的值
* plusDays	返回LocalDateTime加上指定天数得到的值
* plusHours	返回LocalDateTime加上指定小时数得到的值
* plusMinutes	返回LocalDateTime加上指定分钟数得到的值
* plusMonths	返回LocalDateTime加上指定月数得到的值
* plusNanos	返回LocalDateTime加上指定纳秒数得到的值
* plusSeconds	返回LocalDateTime加上指定秒数得到的值
* plusWeeks	返回LocalDateTime加上指定星期数得到的值
* plusYears	返回LocalDateTime加上指定年数得到的值
* query	查询LocalDateTime
* range	返回指定时间字段的范围
* toLocalDate	返回LocalDateTime的LocalDate部分
* toLocalTime	返回LocalDateTime的LocalTime部分
* toString	返回LocalDateTime的字符串表示
* truncatedTo	返回LocalDateTime截取到指定时间单位的拷贝
* until	计算LocalDateTime和另一个LocalDateTi
* with	返回LocalDateTime指定字段更改为新值后的拷贝
* withDayOfMonth	返回LocalDateTime月的第几天更改为新值后的拷贝
* withDayOfYear	返回LocalDateTime年的第几天更改为新值后的拷贝
* withHour	返回LocalDateTime的小时数更改为新值后的拷贝
* withMinute	返回LocalDateTime的分钟数更改为新值后的拷贝
* withMonth	返回LocalDateTime的月份更改为新值后的拷贝
* withNano	返回LocalDateTime的纳秒数更改为新值后的拷贝
* withSecond	返回LocalDateTime的秒数更改为新值后的拷贝
* withYear	返回LocalDateTime年份更改为新值后的拷贝

> 相关对象

Instant：表示时刻，不直接对应年月日信息，需要通过时区转换
LocalDateTime: 表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
LocalDate：表示与时区无关的日期，与LocalDateTime相比，只有日期信息，没有时间信息
LocalTime：表示与时区无关的时间，与LocalDateTime相比，只有时间信息，没有日期信息
ZonedDateTime： 表示特定时区的日期和时间
ZoneId/ZoneOffset：表示时区

> 用于格式化和解析DateFor事宜的模式列表

Symbol  Meaning                     Presentation      Examples
  ------  -------                     ------------      -------
   G       era                         text              AD; Anno Domini; A
   u       year                        year              2004; 04
   y       year-of-era                 year              2004; 04
   D       day-of-year                 number            189
   M/L     month-of-year               number/text       7; 07; Jul; July; J
   d       day-of-month                number            10

   Q/q     quarter-of-year             number/text       3; 03; Q3; 3rd quarter
   Y       week-based-year             year              1996; 96
   w       week-of-week-based-year     number            27
   W       week-of-month               number            4
   E       day-of-week                 text              Tue; Tuesday; T
   e/c     localized day-of-week       number/text       2; 02; Tue; Tuesday; T
   F       week-of-month               number            3

   a       am-pm-of-day                text              PM
   h       clock-hour-of-am-pm (1-12)  number            12
   K       hour-of-am-pm (0-11)        number            0
   k       clock-hour-of-am-pm (1-24)  number            0

   H       hour-of-day (0-23)          number            0
   m       minute-of-hour              number            30
   s       second-of-minute            number            55
   S       fraction-of-second          fraction          978
   A       milli-of-day                number            1234
   n       nano-of-second              number            987654321
   N       nano-of-day                 number            1234000000

   V       time-zone ID                zone-id           America/Los_Angeles; Z; -08:30
   z       time-zone name              zone-name         Pacific Standard Time; PST
   O       localized zone-offset       offset-O          GMT+8; GMT+08:00; UTC-08:00;
   X       zone-offset 'Z' for zero    offset-X          Z; -08; -0830; -08:30; -083015; -08:30:15;
   x       zone-offset                 offset-x          +0000; -08; -0830; -08:30; -083015; -08:30:15;
   Z       zone-offset                 offset-Z          +0000; -0800; -08:00;

   p       pad next                    pad modifier      1

   '       escape for text             delimiter
   ''      single quote                literal           '
   [       optional section start
   ]       optional section end
   #       reserved for future use
   {       reserved for future use
   }    

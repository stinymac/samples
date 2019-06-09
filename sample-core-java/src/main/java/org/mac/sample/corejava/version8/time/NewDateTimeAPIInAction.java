/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.version8.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @auther mac
 * @date 2019-06-09
 */
public class NewDateTimeAPIInAction {

    public static void main(String[] args) {
        //======================================================LocalDate===============================================
        LocalDate localDate = LocalDate.now();
        LocalDate localDateOf = LocalDate.of(2019,6,9); // of 方法获取LocalDate实例

        LocalDate localDateParse =LocalDate.parse("2019-02-20");  //String --> LocalDate实例
        /*
         * plus(TemporalAmount amountToAdd)  plusWeeks  plusMonths  plusYears
         * minus(TemporalAmount amountToAdd) minusWeeks minusMonths minusYears
         * TemporalAmount 时间量 -> ChronoPeriod(长周期时间量)/ChronoPeriodImpl ,Duration , Period
         */
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("tomorrow:"+tomorrow);

        DayOfWeek sunday = LocalDate.parse("2019-06-09").getDayOfWeek();
        int twelve = LocalDate.parse("2019-06-12").getDayOfMonth();
        // 闰年
        boolean leapYear = LocalDate.now().isLeapYear();

        boolean notBefore = LocalDate.parse("2019-06-12").isBefore(LocalDate.parse("2016-06-11"));
        boolean isAfter = LocalDate.parse("2019-06-12").isAfter(LocalDate.parse("2016-06-11"));
        System.out.println(notBefore+"-"+isAfter);

        LocalDateTime beginningOfDay = LocalDate.parse("2019-06-12").atStartOfDay();
        System.out.println(beginningOfDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.parse("2019-06-12"), LocalTime.MAX);
        System.out.println(endOfDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS")));

        /*
         * firstDayOfMonth lastDayOfMonth firstDayOfNextMonth firstDayOfYear lastDayOfYear
         */
        LocalDate firstDayOfMonth = LocalDate.parse("2019-06-12").with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth);

        //======================================================LocalTime===============================================
        LocalTime now = LocalTime.now();
        LocalTime sixThirty = LocalTime.parse("06:30");
        LocalTime sixThirtyTime = LocalTime.of(6, 30);

        LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
        LocalTime plusHours = sevenThirty.plusHours(1);
        LocalTime fiveThirty = sixThirtyTime.minus(1, ChronoUnit.HOURS);
        System.out.println(sevenThirty+"|"+plusHours+"|"+fiveThirty);

        int six = LocalTime.parse("06:30").getHour();
        boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        System.out.println(isBefore);

        LocalTime maxTime = LocalTime.MAX;
        System.out.println("maxTime:"+maxTime);

        //======================================================LocalDateTime===========================================

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
        LocalDateTime.parse("2015-02-20T06:30:00");

        localDateTime.plusDays(1);
        localDateTime.minusHours(2);
        localDateTime.getMonth();

        String isoLocalDateTime = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(isoLocalDateTime);
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));

        //======================================================Period==================================================

        LocalDate initialDate = LocalDate.parse("2007-05-10");
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        System.out.println("plus Period.ofDays:"+finalDate);

        Period period = Period.between(finalDate, initialDate);
        System.out.println("period:"+period);
        int five = period.getDays();
        System.out.println("day period:"+five);//-5

        long interval = ChronoUnit.DAYS.between(initialDate , finalDate);
        System.out.println("interval:"+interval);//5

        //======================================================Durataion===============================================

        LocalTime initialTime = LocalTime.of(6, 30, 0);

        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        long thirty = Duration.between(finalTime, initialTime).getSeconds();
        System.out.println(thirty);
        long thirtyDurataion = ChronoUnit.SECONDS.between(finalTime, initialTime);
        System.out.println(thirtyDurataion);

        //======================================================compatibility for  (Date / Calendar)====================

        Date dateNow = new Date();
        Calendar calendar  = Calendar.getInstance();

        // java.util.Date -->LocalDate
        LocalDateTime dateTo = LocalDateTime.ofInstant(dateNow.toInstant(),ZoneId.systemDefault());
        LocalDateTime calendarTo = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        System.out.println("dateTo:"+dateTo);
        System.out.println("calendarTo:"+calendarTo);

        // java.util.Date --> LocalDate/LocalDateTime
        ZonedDateTime zonedDateTime = dateNow.toInstant().atZone(ZoneId.systemDefault());
        LocalDate nowLocalDate = zonedDateTime.toLocalDate();
        LocalDateTime nowLocalDateTime = zonedDateTime.toLocalDateTime();
        //java.sql.Date --> LocalDate
        LocalDate sqlDateToNowLocalDate = new java.sql.Date(dateNow.getTime()).toLocalDate();

        // LocalDate --> java.sql.Date
        System.out.println(java.sql.Date.valueOf(LocalDate.now()));

        // LocalDate --> java.utl.Date
        System.out.println(java.util.Date.from(LocalDate.now()
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant())
        );

        // LocalDateTime --> java.utl.Date
        System.out.println("LocalDateTime --> java.utl.Date:"+LocalDateTime.ofInstant(LocalDateTime.now().toInstant(ZoneOffset.UTC), ZoneId.systemDefault()));

        // LocalDate LocalDateTime--> format String
        LocalDate date = LocalDate.now();

        date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("d MMM uuuu->:"+date.format(DateTimeFormatter.ofPattern("d MMM uuuu")));

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("d MMM uuuu HH:mm:ss->:"+ dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss")));
    }
}

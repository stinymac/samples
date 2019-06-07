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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @auther mac
 * @date 2019-06-07
 */

public class NewDateTimeAPIUsage {
    public static void main(String[] args) throws InterruptedException {
        LocalDate date = LocalDate.of(2019,6,7);
        System.out.println(date);

        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDayOfYear());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfWeek());

        LocalTime localTime = LocalTime.now();

        System.out.println(localTime);
        System.out.println(localTime.getHour());
        System.out.println(localTime.getMinute());
        System.out.println(localTime.getSecond());

        LocalDate localDate = LocalDate.now();

        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        System.out.println(localDateTime);

        Instant start = Instant.now();
        Thread.sleep(1000);
        Instant end = Instant.now();
        Duration duration = Duration.between(start,end);

        System.out.println(duration.toMillis());

        Period period = Period.between(LocalDate.of(2010,6,7),LocalDate.of(2019,6,7));
        System.out.println(period.getYears());

        LocalDateTime now = LocalDateTime.now();

        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        LocalDate localDateNow = LocalDate.now();
        System.out.println(localDateNow.format(DateTimeFormatter.ISO_LOCAL_DATE));

        String dateString  = "2019-06-07T22:06:33.587";
        System.out.println( LocalDateTime.parse(dateString,DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    }
}

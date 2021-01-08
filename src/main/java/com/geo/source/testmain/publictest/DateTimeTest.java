package com.geo.source.testmain.publictest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeTest {

  public static void main(String[] args) {
    // m1();
//    m2();
//    m3();
//    m4();
//    m5();
//    m6();
//    m7();
//    m8();
//    m9();
    m11();
  }

  private static void m11() {
    Date date = new Date();
    Instant instant = date.toInstant();
    ZoneId zoneId = ZoneId.systemDefault();

    LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    System.out.println("Date = " + date);
    System.out.println("LocalDateTime = " + localDateTime);
  }

  private static void m10() {
    LocalDate now = LocalDate.now();
    LocalDate localDate = now.plusDays(7);
    Period between = Period.between(now, localDate);
//    long l = Duration.between(now, localDate).toDays();
    System.out.println(between.getDays());

  }

  private static void m9() {
    final LocalDateTime now = LocalDateTime.now();
    System.out.println(now.toEpochSecond(ZoneOffset.of("+8")));
    System.out.println(now.toEpochSecond(ZoneOffset.of("+8")));
    System.out.println(System.currentTimeMillis());
    final LocalDate now1 = LocalDate.now();
    final LocalTime now2 = LocalTime.now();
    System.out.println(now instanceof Temporal);
    System.out.println(now1 instanceof Temporal);
    System.out.println(now2 instanceof Temporal);
    System.out.println(now1 + " " + now2.toString());
  }

  private static void m8() {
    System.out.println(System.nanoTime());
    System.out.println(System.currentTimeMillis());
//    System.out.println(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
  }

  private static void m7() {
//    final LocalDateTime now = LocalDateTime.now();
//    final LocalTime localTime = now.toLocalTime();
//    final int toSecondOfDay = localTime.toSecondOfDay();// 今天得总秒数

    final LocalTime start = LocalTime.of(20, 30, 0);
    final LocalTime end = LocalTime.of(20, 0, 0);
    final LocalTime nowTime = LocalTime.now();
    final int startSecondOfDay = start.toSecondOfDay();
    final int endSecondOfDay = end.toSecondOfDay();
    final int nowSecondOfDay = nowTime.toSecondOfDay();

    if (start.isBefore(end)) {
      if (nowTime.isAfter(start) && nowTime.isBefore(end)) {
        final int b = endSecondOfDay - nowSecondOfDay;
        System.out.println("距离结束："+b);
        System.out.println("YES");
      } else {
        final int b;
        if (nowTime.isBefore(start)) {
          b = startSecondOfDay - nowSecondOfDay;
        } else {
          b = 86400 - nowSecondOfDay + startSecondOfDay;
        }
        System.out.println("距离开始："+b);
        System.out.println("NO");
      }
    } else {
      if (nowTime.isBefore(start) && nowTime.isAfter(end)) {
        final int b = startSecondOfDay - nowSecondOfDay;
        System.out.println("距离开始："+b);
        System.out.println("NO");
      } else {
        final int b;
        if (nowTime.isBefore(end)) {
          b = endSecondOfDay - nowSecondOfDay;
        } else {
          b = 86400 - nowSecondOfDay + endSecondOfDay;
        }

        System.out.println("距离结束："+b);
        System.out.println("YES");
      }
    }
//    System.out.println(nowTime.toString());
//    System.out.println(Duration.between(start, end).getSeconds());
//    System.out.println(Duration.between(end, start).getSeconds());
  }

  private static void m6() {
    final LocalDate localDate = LocalDate.of(2019, 4, 21);
    final ZoneId zone = ZoneId.of("Europe/Rome");
    final ZonedDateTime zonedDateTime = localDate.atStartOfDay(zone);
    System.out.println(zonedDateTime);
    final Instant now = Instant.now();
    final ZonedDateTime zonedDateTime1 = now.atZone(zone);
    System.out.println(zonedDateTime1);

    final LocalDateTime localDateTime = LocalDateTime.now();
    System.out.println(localDateTime);
    final Instant instant = localDateTime.toInstant(ZoneOffset.of("-01:00"));
    System.out.println(instant);
    final LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, zone);
    System.out.println(localDateTime1);
  }

  private static void m5() {
    final LocalDate localDate = LocalDate.of(2019, 4, 20);
    final LocalDate date1 = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    final LocalDate date2 = date1.with(TemporalAdjusters.lastDayOfMonth());
    System.out.println(date1);
    System.out.println(date2);
  }

  private static void m4() {
    final LocalDateTime now = LocalDateTime.now();
    System.out.println("BASIC_ISO_DATE : "+now.format(DateTimeFormatter.BASIC_ISO_DATE));
    System.out.println("ISO_DATE : "+now.format(DateTimeFormatter.ISO_DATE));
    System.out.println("ISO_DATE_TIME : "+now.format(DateTimeFormatter.ISO_DATE_TIME));
    System.out.println("ISO_LOCAL_DATE : "+now.format(DateTimeFormatter.ISO_LOCAL_DATE));
    System.out.println("ISO_LOCAL_DATE_TIME : "+now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    System.out.println("ISO_LOCAL_TIME : "+now.format(DateTimeFormatter.ISO_LOCAL_TIME));
    System.out.println("ISO_ORDINAL_DATE : "+now.format(DateTimeFormatter.ISO_ORDINAL_DATE));
    System.out.println("ISO_TIME : "+now.format(DateTimeFormatter.ISO_TIME));
    System.out.println("ISO_WEEK_DATE : "+now.format(DateTimeFormatter.ISO_WEEK_DATE));
    System.out.println("自定义 : "+now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }

  private static void m3() {
    System.out.println(System.nanoTime());
  }

  private static void m2() {
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
    System.out.println(new Date());
    System.out.println(Instant.now());
  }

  private static void m1() {
  /*System.out.println("day of week:" + Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
	System.out.println("day of week:" + Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH));
	System.out.println("day of week:" + Calendar.getInstance().get(Calendar.WEEK_OF_MONTH));*/

    // System.out.println("month day:" + DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
    // System.out.println("month:" + DateUtils.truncate(new Date(), Calendar.MONTH));
    // System.out.println("week day:" + DateUtils.truncate(new Date(), Calendar.DAY_OF_WEEK));
    // System.out.println("year day:" + DateUtils.truncate(new Date(), Calendar.DAY_OF_YEAR));
    // System.out.println("day week month:" + DateUtils.truncate(new Date(), Calendar.DAY_OF_WEEK_IN_MONTH));
    // System.out.println("date:" + DateUtils.truncate(new Date(), Calendar.DATE));

    String d = "2019/03/22 10:00-11:00";
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm-HH:mm");
    try {
      Date parse = df.parse(d);
      System.out.println(df.format(parse));
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
}

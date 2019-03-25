package com.geo.source.testmain.publictest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateTimeTest {

	public static void main(String[] args) {
		
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

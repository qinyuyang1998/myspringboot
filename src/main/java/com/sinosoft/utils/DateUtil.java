package com.sinosoft.utils;

import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * 
	 * @Title: getWeekOfDate
	 * @param @param
	 *            dt
	 * @param @return
	 *            Description: 获取当前日期是周几 return String
	 * @author zhangruixin date 2017年3月29日 下午4:18:53
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 
	 * @Title: getStringDayOfWeek
	 * @param @param
	 *            date
	 * @param @return
	 *            Description: 获取当前日期是周几 return String
	 * @author zhangruixin date 2017年3月29日 下午4:19:14
	 */
	public static String getStringDayOfWeek(Date date) {
		String result = "";
		if (null != date) {
			SimpleDateFormat formatter4 = new SimpleDateFormat("E");
			result = formatter4.format(date);
		}
		return result;
	}

	/**
	 * 
	 * @Title: getYear
	 * @param @param
	 *            newDate
	 * @param @return
	 *            Description: 根据字符串格式日期数据获取年份 return int
	 * @author zhangruixin date 2017年3月23日 上午10:04:59
	 */
	public static int getYear(String newDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date startDate = null;
		try {
			startDate = df.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @Title: getlastMonth
	 * @param @param
	 *            n
	 * @param @param
	 *            newDate
	 * @param @return
	 *            Description: 根据字符串格式日期数据，获取N年前得年份 return String
	 * @author zhangruixin date 2017年3月23日 上午10:05:30
	 */
	public static String getlastMonth(int n, String newDate) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		Date startDate = null;
		try {
			startDate = df.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(startDate);
		c.add(Calendar.YEAR, -n);
		Date date = c.getTime();
		return df.format(date);
	}

	/**
	 * 
	 * @Title: DateToString
	 * @param @param
	 *            Datedate
	 * @param @param
	 *            datetype
	 * @param @return
	 *            日期根据datetype转换需要的日期格式字符串数据 Description: return String
	 * @author zhangruixin date 2017年3月23日 上午10:06:46
	 */
	public static String DateToString(Date Datedate, String datetype) {
		Format format = new SimpleDateFormat(datetype);
		return format.format(Datedate);

	}

	/**
	 * 获取当前年
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		return "".equals(year) ? "" : year;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return date
	 */
	public static Date getNowTime() {
		java.util.Date date = new java.util.Date();
		Timestamp newTime = new Timestamp(date.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(newTime);
		newTime = Timestamp.valueOf(timeStr);
		return newTime;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return String
	 */
	public static String getNewTimeStr() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(date);
		return timeStr;
	}

	/**
	 * @Description: 获取月份，做上传文件夹用
	 * @return String
	 * @author liuch
	 * @date 2018年11月7日下午1:43:40
	 */
	public static String getFileFolderDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
		return df.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.getNowTime());
	}
}

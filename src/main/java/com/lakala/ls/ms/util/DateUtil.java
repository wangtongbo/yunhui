package com.lakala.ls.ms.util;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.getInstance;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String format1 = "yyyyMMdd";
	public static final String format2 = "yyyy-MM-dd";
	public static final String format3 = "yyyyMMddHHmmss";
	public static final String format4 = "yyyy-MM-dd HH:mm:ss";
	public static final String format5 = "MM-dd HH:mm";
	public static final String format6 = "YYYY年MM月dd日";

	/* add by Hydra begin */
	private static final Calendar cal = getInstance();
	private static final Calendar tmp = getInstance();
	public static final int YEAR = Calendar.YEAR;
	public static final int MONTH = Calendar.MONTH;
	public static final int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;
	public static final int HOUR = Calendar.HOUR;
	public static final int MINUTE = Calendar.MINUTE;
	public static final int SECOND = Calendar.SECOND;
	
	//静态工具类，防误生成
	private DateUtil(){}
	
	/**
	* @Description: 判断指定日期与当前日期是否在指定范围内。范围包括
	* @param @param date
	* @param @param range
	* @param @return
	* @return Boolean
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月22日 下午8:16:23
	 */
	public static Boolean isInRange(Date date, int range){
		synchronized(cal){
			boolean res = false;
			tmp.setTime(date);
			resetCalendarWithNowTime();
			switch(range){
				case YEAR: cal.set(MONTH, cal.getActualMinimum(MONTH));
				case MONTH: cal.set(DAY_OF_MONTH, cal.getActualMinimum(DAY_OF_MONTH));
				case DAY_OF_MONTH: cal.set(HOUR_OF_DAY, cal.getActualMinimum(HOUR_OF_DAY));
				case HOUR_OF_DAY: cal.set(MINUTE, cal.getActualMinimum(MINUTE));
				case MINUTE: cal.set(SECOND, cal.getActualMinimum(SECOND));
				case SECOND: cal.set(MILLISECOND, cal.getActualMinimum(MILLISECOND));
			}
			res = tmp.after(cal);
			cal.add(range, 1);
			res = res && tmp.before(cal);
			return res;
		}
	}
	
	public static Boolean isThisMonth(Date date){
		synchronized(cal){
			boolean res = false;
			tmp.setTime(date);
			resetCalendarWithZero();
			cal.set(DAY_OF_MONTH, cal.getActualMinimum(DAY_OF_MONTH));
			res = tmp.after(cal);
			cal.add(MONTH, 1);
			res = res && tmp.before(cal);
			return res;
		}
	}
	
	/**
	* @Description: 查找指定日期的下一个提醒日 
	* @param date：指定日期
	* @param day：提醒日
	* @return Date
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午5:11:16
	 */
	public static Date getDateByDayOfMonth(Date date, int day){
		synchronized (cal) {
			cal.setTime(date);
			int today = cal.get(DAY_OF_MONTH);
			if(day < today){
				cal.add(MONTH, 1);
			}
			cal.set(DAY_OF_MONTH, day);
			return cal.getTime();
		}
	}
	
	/**
	* @Description: 查找当前日期的下一个提醒日 
	* @param day：提醒日
	* @return Date
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午5:11:16
	 */
	public static Date getDateByDayOfMonth(int day){
		return getDateByDayOfMonth(new Date(), day);
	}
	
	/**
	* @Description: 根据指定日期和月份换回当月的提醒日期，当提醒日期day大于本月最大天数时，取最大天数。
	* @param month：指定的月份（0-11）
	* @param day：指定的提醒日（1-31）
	* @return int
	* @throws
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午2:05:32
	 */
	public static int getTheAppointedDayOfTheMonth(int month, int day){
		synchronized(cal){
			cal.set(MONTH, month);
			int lastDay = cal.getActualMaximum(DAY_OF_MONTH);
			return day > lastDay ? lastDay : day;
		}
	}
	
	/**
	* @Description: 根据指定日期返回当前月份的提醒日期，当提醒日期day大于本月最大天数时，取最大天数。
	* @param day：指定的提醒日（1-31）
	* @return int
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午2:09:32
	 */
	public static int getTheAppointedDayOfTheMonth(int day){
		synchronized(cal){
			resetCalendarWithNowTime();
			int lastDay = cal.getActualMaximum(DAY_OF_MONTH);
			return day > lastDay ? lastDay : day;
		}
	}
	
	/**
	* @Description: 获取当前日期在本月中是第几天
	* @return int
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月19日 上午11:45:46
	 */
	public static int getDayOfMonthOfToday(){
		synchronized (cal) {
			resetCalendarWithNowTime();
			return cal.get(DAY_OF_MONTH);
		}
	}
	
	/**
	* @Description: 根据提醒日和指定日期，返回提醒日和指定日期间隔天数，提醒日当天返回0。
	* @param day：提醒日
	* @param date：指定日期
	* @return int
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午2:39:56
	 */
	public static int getFate(int day, Date date){
		synchronized(cal){
			cal.setTime(date);
			int today = cal.get(DAY_OF_MONTH);
			int max = cal.getActualMaximum(DAY_OF_MONTH);
			return (day - today + max) % max;
		}
	}
	
	/**
	* @Description: 根据提醒日，返回下次提醒日距今的天数，提醒日当天返回0。
	* @param day：提醒日
	* @return int
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午2:39:56
	 */
	public static int getFate(int day){
		return getFate(day, new Date());
	}
	
	/**
	* @Description: 根据指定提醒日，返回下次提醒日距今的天数，提醒日当天返回0。
	* @param settingDay：指定提醒日
	* @return int
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午2:39:56
	 */
	public static int getFateWithSettingDay(int settingDay){
		return getFate(getTheAppointedDayOfTheMonth(settingDay), new Date());
	}
	
	/**
	 * @Description: 将cal重置为当前时间。
	 */
	private static void resetCalendarWithNowTime(Calendar cal){
		cal.setTime(new Date());
	}
	
	/**
	 * @Description: 将cal重置为当前时间。
	 */
	private static void resetCalendarWithNowTime(){
		resetCalendarWithNowTime(cal);
	}
	
	private static void resetCalendarWithZero(Date d){
		cal.setTime(d);
		cal.set(HOUR, 0);
		cal.set(MINUTE, 0);
		cal.set(SECOND, 0);
	}
	
	private static void resetCalendarWithZero(){
		resetCalendarWithZero(new Date());
	}
	/* add by Hydra  end  */
	/**
	 * @description 获取当前日期字符串
	 * @author wushaolin@lakala.com
     * @date 2013年12月30日
	 * @return yyyyMMdd
	 */
	public static String getSystemDate(){
		Date sysDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);
		return simpleDateFormat.format(sysDate);
	}
	/**
	 * @description 获取指定日期格式的字符串
	 * @param data
	 * @param pattern
	 * @return
	 */
	public static String getFormatDate(Date data,String pattern)
	{
		Date sysDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(sysDate);
	}
	/**
	 * @description date转换为yyyyMMdd字符串
	 * @author wushaolin@lakala.com
     * @date 2013年12月30日
	 * @param date
	 * @return yyyyMMdd
	 */
	public static String getFormat1Date(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format1);
		return simpleDateFormat.format(date);
	}
	/**
	 * @description date转换为yyyy-MM-dd字符串
	 * @author wushaolin@lakala.com
     * @date 2013年12月30日
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getFormat2Date(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format2);
		return simpleDateFormat.format(date);
	}
	/**
	 * @description date转换为yyyyMMddHHmmss字符串
	 * @author wushaolin@lakala.com
	 * @date 2013年12月30日
	 * @param date
	 * @return yyyyMMddHHmmss
	 */
	public static String getFormat3Date(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format3);
		return simpleDateFormat.format(date);
	}
	/**
	 * @description date转换为yyyy-MM-dd HH:mm:ss字符串
	 * @author wushaolin@lakala.com
	 * @date 2013年12月30日
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormat4Date(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format4);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * @author wushaolin@lakala.com
	 * @date 2013年12月30日
	 * @description 计算两个日期差几天，也可比较两个日期谁在前，谁在后
	 * @param 只支持yyyyMMdd格式
	 * @return int 如果secondDate在firstDate之前，返回一个负整数；反之返回正整数
	 */
	public static int getDiffBetweenTwoDate(String firstDate,String secondDate){
		SimpleDateFormat myFormatter = new SimpleDateFormat(format1);//计算两天之差
		Date date1=null;
		Date date2=null;
		int cha=0;
		try {
			date1 = myFormatter.parse(firstDate);//起始日期
			date2 = myFormatter.parse(secondDate);//终止日期
			long  seconds=date2.getTime()-date1.getTime();//终止日期-起始日期=毫秒
		    cha=(int)(seconds/(24*60*60*1000));//再除以每天多少毫秒(24*60*60*1000) ＝差几天
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cha;
	}

	/**
	 *
	 * @param firstDate
	 * @param secondDate
     * @return
     */
	public static boolean compareDate(String firstDate,String secondDate){
		SimpleDateFormat myFormatter = new SimpleDateFormat(format4);
		Date date1=null;
		Date date2=null;

		try {
			date1 = myFormatter.parse(firstDate);//起始日期
			date2 = myFormatter.parse(secondDate);//终止日期
			if(date1.getTime() > date2.getTime()) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	* @Description: 将传入的fm1格式的时间字符串，转换为fm2格式的时间戳类型
	* @param date
	* @param fm1
	* @param fm2
	* @return Date
	* @throws
	 */
	public static Timestamp getDateFripmstring(String date,String fm1,String fm2){
		Date outDate=null;
		SimpleDateFormat fmt = new SimpleDateFormat(fm1);
		SimpleDateFormat fmt1 = new SimpleDateFormat(fm2);
		try {
			outDate = fmt.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String oDate=fmt1.format(outDate);
		return Timestamp.valueOf(oDate);
	}
	/**
	 * 获取传入日期时间前n月的日期时间
	 * @param Date date
	 * @param int n
	 * @param String format 日期格式化字符串
	 * @return String date
	 */
	public static String getXAgoMonth(Date date,int n,String format){
		int[] minmonth={4,6,9,11};
		SimpleDateFormat s = new SimpleDateFormat(format);
    	Calendar inDate = Calendar.getInstance();      
    	Calendar outDate = Calendar.getInstance();      
    	inDate.setTime(date); 
    	outDate.setTime(date); 
    	outDate.set(Calendar.MONTH, outDate.get(Calendar.MONTH)-n);
    	Date d=outDate.getTime();
    	if(inDate.getTime().getDate()==31){
    		int month=outDate.getTime().getMonth();
    		for(int i=0;i<minmonth.length;i++){
    			if(month==minmonth[i]){
    				outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-1);
    			}
    		}
    		if(month==2){
    			if(check2Month(d)){
        			outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-2);
    			}else{
        			outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-3);
    			}
    		}
    	}
    	if(inDate.getTime().getDate()==30){
    		int month=outDate.getTime().getMonth()+1;
    		if(month==2){
    			if(check2Month(d)){
        			outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-1);
    			}else{
        			outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-2);
    			}
    		}
    	}
    	if(inDate.getTime().getDate()==29){
    		int month=outDate.getTime().getMonth()+1;
    		if(month==2){
    			if(!check2Month(d)){
    				outDate.set(Calendar.DATE, outDate.get(Calendar.DATE)-1);
    			}
    		}
    	}
		return s.format(outDate.getTime());
	}
	/**
	 * 判断某一日期所在年是否为闰年
	 * @param Date date
	 * @return boolean
	 */
	public static boolean check2Month(Date date){
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		int year=d.getTime().getYear();
		if(year%400==0){
			return true;
		}else if(year%100!=0&&year%4==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取传入日期时间前n日的日期时间
	 * @author wushaolin
	 * @param date
	 * @param n
	 * @param format
	 * @return
	 */
	public static String getDateBeforeXDay(Date date,int n,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(DAY_OF_MONTH, cal.get(DAY_OF_MONTH)-n);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 日期字符串转换为日期
	 * @author wushaolin
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDate(String dateStr,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 把日期转换为指定格式的日期字符串
	 * @author wushaolin
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateStrFormat(Date date,String format){
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 把日期转换为指定格式的日期字符串
	 * @author wushaolin
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String getDateStrFormat(String dateStr,String format){
		Date date = getDate(dateStr, format);
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 计算两个时间相差的月份
	 * @author wushaolin
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDifferMonth(Date startDate,Date endDate){
		Calendar start=Calendar.getInstance();
		start.setTime(startDate);
		Calendar end=Calendar.getInstance();
		end.setTime(endDate);
		int differYear=end.get(Calendar.YEAR)-start.get(Calendar.YEAR);
		int differMonth=end.get(Calendar.MONTH)-start.get(Calendar.MONTH);
		return differYear*12+differMonth;
	}
	
	/**
	* @Description: 日期加N年(当前日期)
	* @param year：年数
	* @return Date
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午5:11:16
	 */
	public static Date getDateByAddYear(int year){
		return getDateByAddYear(new Date(), year);
	}
	
	/**
	* @Description: 日期加N年
	* @param date：指定日期
	* @param year：年数
	* @return Date
	* @throws  
	* @author: Hydra wangshuang@lakala.com
	* @date: 2014年1月6日 下午5:11:16
	 */
	public static Date getDateByAddYear(Date date, int year){
		synchronized (cal) {
			cal.setTime(date);
			cal.add(Calendar.YEAR, year);
			return cal.getTime();
		}
	}

	/**
	 * @Description: 日期加N小时
	 * @param date：指定日期
	 * @param year：小时数
	 * @return Date
	 * @throws
	 * @author: Hydra wangshuang@lakala.com
	 * @date: 2014年1月6日 下午5:11:16
	 */
	public static Date getDateByAddHour(Date date, int hour){
		synchronized (cal) {
			cal.setTime(date);
			cal.add(Calendar.HOUR, hour);
			return cal.getTime();
		}
	}
	
	public static String getNowStr(){
		SimpleDateFormat sdf = new SimpleDateFormat(format1);
		return sdf.format(new Date());
	}
	
	public static String getNowLongStr(){
		return getNowStr(format3);
	}
	public static String getNowStr(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	public static String getDateStr(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}

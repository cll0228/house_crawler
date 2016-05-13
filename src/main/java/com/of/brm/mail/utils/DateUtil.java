package com.of.brm.mail.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class DateUtil {

	private static SimpleDateFormat formatter;

	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_NUMBER_PATTERN = "yyyyMMdd";
	public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String MONEY_PATTERN = "############0.00";
	public static final String CURRENCY_PATTERN = "#,###,###,###,##0.00";
	public static final String TERM_PATTERN = "000";
	public static final String DAY_PATTERN = "00";
	public static final String RATE_PATTERN = "############0.000000";
	public static final String FLOAT_PATTERN = "############0.00";

	public DateUtil() {
		super();

	}

	public static java.sql.Date getCurrentDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static Time getCurrentTime() {
		return new Time(System.currentTimeMillis());
	}
	
	public static java.sql.Timestamp getCurrentTimestamp(){
		return new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 由日期返回yyyyMMddHHmmss格式的日期
	 * @param date
	 * @return
	 */
	public static Date getCurrentDateTime(java.util.Date date) throws ParseException{
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(date);
		return date; 
	}
	
	public static String getTimestamp() {
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return formate.format(new Timestamp(System.currentTimeMillis()));
	}

	public static String getACETimestamp() {
		SimpleDateFormat formate = new SimpleDateFormat("yyMMddHHmmssSSS");
		return formate.format(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * 返回yyyy-MM-dd格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(java.util.Date date) {
		if (date == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
	 * 由日期返回yyyyMMdd格式的字符串
	 * @param date
	 * @return
	 */
	public static String dateToNumber(Date date) {

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat(DateUtil.DATE_NUMBER_PATTERN);
		return simpleDateFormat.format(date);
	}

	/**
	 * 由yyyy-MM-dd格式的字符串返回日期
	 * @param date
	 * @return
	 */
	public static Date stringToDate(String string) throws ParseException{
		if (string == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(string);
	}

	/**
	 * 由yyyyMMdd格式的字符串返回日期
	 * @param date
	 * @return
	 */
	public static Date numberToDate(String string) throws ParseException{
		if (string == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat(DATE_NUMBER_PATTERN);
		return simpleDateFormat.parse(string);
	}

	/**
	 * 由日期返回yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param time
	 * @return
	 */
	public static String timeToString(Date time) {
		if (time == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat(TIME_PATTERN);
		return simpleDateFormat.format(time);
	}

	/**
	 * 由日期返回yyyyMMddHHmmss格式的字符串
	 * @param time
	 * @return
	 */
	public static String timeToNumber(Date time) {
		if (time == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat("yyyyMMddHHmmss");
		return simpleDateFormat.format(time);
	}


	/**
	 * 由日期返回yyyy-MM-dd-HH.mm.ss.SSSSSS格式的字符串
	 * @param date Date格式日期
	 * @return String  yyyy-MM-dd-HH.mm.ss.SSSSSS格式 的字符串
	 */
	public static String getTimeStampFormat(Date date) {
		if (date == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 时间转换
	 * @param time hhmmss
	 * @return hh:mm:ss
	 */
	public static String stringToTime(String time){
		if (time == null)
			return null;
		
		SimpleDateFormat sdf =	new SimpleDateFormat("HHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		try {
			return sdf2.format(sdf.parse(time));
		} catch (ParseException e) {
			return null;
		}
	}

	public static int getDaysBetween ( String startDate, String endDate ){
		int days = 0;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date _StartDate = format.parse(startDate);
			Date _EndDate = format.parse(endDate);
			days =  getDaysBetween(_StartDate,_EndDate);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return days;
	}

	public static int getDaysBetween2 ( String startDate, String endDate ){
		int days = 0;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date _StartDate = format.parse(startDate);
			Date _EndDate = format.parse(endDate);
			days =  getDaysBetween2(_StartDate,_EndDate);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return days;
	}

	/**
	 * 计算两个日期相隔的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysBetween(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int days =
			calendarEndDate.get(Calendar.DAY_OF_YEAR)
				- calendarStartDate.get(Calendar.DAY_OF_YEAR);
		int y2 = calendarEndDate.get(Calendar.YEAR);
		while (calendarStartDate.get(Calendar.YEAR) < y2) {
			days += calendarStartDate.getActualMaximum(Calendar.DAY_OF_YEAR);
			calendarStartDate.add(Calendar.YEAR, 1);
		}

		return days;
	}

	public static int getDaysBetween2(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
//		if (startDate.after(endDate)) {
//			Calendar swap = calendarStartDate;
//			calendarStartDate = calendarEndDate;
//			calendarEndDate = swap;
//		}

		int days =
			calendarEndDate.get(Calendar.DAY_OF_YEAR)
				- calendarStartDate.get(Calendar.DAY_OF_YEAR);
		int y2 = calendarEndDate.get(Calendar.YEAR);
		while (calendarStartDate.get(Calendar.YEAR) < y2) {
			days += calendarStartDate.getActualMaximum(Calendar.DAY_OF_YEAR);
			calendarStartDate.add(Calendar.YEAR, 1);
		}

		return days;
	}

	/**
	 * 计算两个日期相隔的月数(不足整月的算一个月)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonthsBetween(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int months =
			calendarEndDate.get(Calendar.MONTH)
				- calendarStartDate.get(Calendar.MONTH)
				+ (calendarEndDate.get(Calendar.YEAR)
					- calendarStartDate.get(Calendar.YEAR))
					* 12;

		if (getEndDateByMonths(startDate, months).compareTo(endDate) < 0)
			months += 1;

		return months;
	}

	/**
	 * 计算两个日期相隔的月数(不比较日)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getActualMonths(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int months =
			calendarEndDate.get(Calendar.MONTH)
				- calendarStartDate.get(Calendar.MONTH)
				+ (calendarEndDate.get(Calendar.YEAR)
					- calendarStartDate.get(Calendar.YEAR))
					* 12;

		return months;
	}

	/**
	 * 计算两个日期相隔的年数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getActualYears(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int years =
			calendarEndDate.get(Calendar.YEAR)
				- calendarStartDate.get(Calendar.YEAR);
		if(calendarEndDate.get(Calendar.MONTH)<calendarStartDate.get(Calendar.MONTH)){
			years=years-1;
		}
		else if(calendarEndDate.get(Calendar.MONTH)==calendarStartDate.get(Calendar.MONTH)){
			if(calendarEndDate.get(Calendar.DAY_OF_MONTH)<calendarStartDate.get(Calendar.DAY_OF_MONTH)){
				years=years-1;
			}
		}

		return years;
	}

	public static String getEndDateByDays(String startDate, int days) {
		String endDate = null;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date _startDt = formatter.parse(startDate);
			Date _endDt = getEndDateByDays(_startDt,days);
			endDate = formatter.format(_endDt);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return endDate;
	}
	/**
	 * 根据起始日和相隔天数计算终止日
	 * @param startDate
	 * @param days
	 * @return
	 */
	public static Date getEndDateByDays(Date startDate, int days) {
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(startDate);
		calendarStartDate.add(Calendar.DAY_OF_YEAR, days);

		return calendarStartDate.getTime();
	}

	/**
	 * 根据起始日和相隔月数计算终止日
	 * @param startDate
	 * @param months
	 * @return
	 */
	public static Date getEndDateByMonths(Date startDate, int months) {
		Calendar calendarStartDate = Calendar.getInstance();
		calendarStartDate.setTime(startDate);
		calendarStartDate.add(Calendar.MONTH, months);

		return calendarStartDate.getTime();
	}

	/**
	 * 根据终止日和相隔天数计算起始日
	 * @param endDate
	 * @param days
	 * @return
	 */
	public static Date getStartDateByDays(Date endDate, int days) {
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(endDate);
		calendarEndDate.add(Calendar.DAY_OF_YEAR, 0 - days);

		return calendarEndDate.getTime();
	}

	/**
	 * 根据终止日和相隔月数计算起始日
	 * @param endDate
	 * @param months
	 * @return
	 */
	public static Date getStartDateByMonths(Date endDate, int months) {
		Calendar calendarEndDate = Calendar.getInstance();
		calendarEndDate.setTime(endDate);
		calendarEndDate.add(Calendar.MONTH, 0 - months);

		return calendarEndDate.getTime();
	}

	public static String getStartDateByMonths(String _endDate, int months) throws Exception {
		Calendar calendarEndDate = Calendar.getInstance();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = formate.parse(_endDate);
		calendarEndDate.setTime(endDate);
		calendarEndDate.add(Calendar.MONTH, 0 - months);
		return formate.format(calendarEndDate.getTime());
	}

	/**
	 * 判断两个日期是否对日
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isSameDate(Date startDate, Date endDate) {
		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		if (calendarStartDate.get(Calendar.DATE)
			== calendarEndDate.get(Calendar.DATE))
			return true;

		if (calendarStartDate.get(Calendar.DATE)
			> calendarEndDate.get(Calendar.DATE)) {
			if (calendarEndDate.get(Calendar.DATE)
				== calendarEndDate.getActualMaximum(Calendar.DATE))
				return true;
		}

		return false;
	}

	/**
	 * 判断日期是否与指定的日期对日
	 * @param date
	 * @param dd
	 * @return
	 */
	public static boolean isSameDate(Date date, String dd) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = Integer.parseInt(dd);

		if (calendar.get(Calendar.DATE) == day)
			return true;

		if (calendar.get(Calendar.DATE) < day) {
			if (calendar.get(Calendar.DATE)
				== calendar.getActualMaximum(Calendar.DATE))
				return true;
		}

		return false;
	}

	/**
	 * 判断两个日期是否同一个月
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isSameMonth(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return false;

		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);

		if (calendarStartDate.get(Calendar.YEAR)
			== calendarEndDate.get(Calendar.YEAR)
			&& calendarStartDate.get(Calendar.MONTH)
				== calendarEndDate.get(Calendar.MONTH))
			return true;

		return false;
	}

	/**
	 * 得到本月第一天的日期
	 * @param today
	 * @return
	 */
	public static Date getFirstDate(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DATE, 1);

		return calendar.getTime();
	}

	/**
	 * 得到本月最后一天的日期
	 * @param today
	 * @return
	 */
	public static Date getLastDate(Date today) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));

		return calendar.getTime();
	}

	/**
	 * 判断数值型的日期是不是有效日期
	 * @param strDate
	 * @return
	 */
	public static boolean verifyDate(String strDate){
		if(strDate.getBytes().length != DATE_NUMBER_PATTERN.getBytes().length){
			return false ;
		}
		if (strDate.equals("00000000")) {
			return false;
		}
		try {
			numberToDate(strDate);
		} catch (ParseException e) {
			return false ;
		}
		return true;
	}

	/**
	 * 计算两个日期相隔天数，其中满月按30天算，不满月按实际天数
	 * @param
	 * @return 天数
	 */

	public static int getDaysBetween30(Date startDate, Date endDate) {

		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int months =
			calendarEndDate.get(Calendar.MONTH)
				- calendarStartDate.get(Calendar.MONTH)
				+ (calendarEndDate.get(Calendar.YEAR)
					- calendarStartDate.get(Calendar.YEAR))
					* 12;

		Date newEndDate = getEndDateByMonths(startDate, months);

		if (newEndDate.compareTo(endDate) <= 0
			|| isSameDate(newEndDate, endDate) == true)
			months += 1;

		int days =  ( months - 1 )* 30;

		Date newStartDate = getEndDateByMonths(startDate, months - 1);

		days += getDaysBetween(newStartDate, endDate);

		return days;
	}

	/**
	 * 计算两个日期相差额, 返回大小为3的数组, 第一位为相差年数,第二位为相差月数,第三位为相差天数
	 * 当整年以年计, 整月以月计, 不足一月以实际天数计
	 * 暂时未使用, 使用了新的规则 MixCalDate类方法
	 * @date 2007-03-08
	 * @author Farly.yu
	 * @param startDate
	 * @param endDate
	 * @return int[]
	 */
	public static int [] getActualYMD(Date startDate, Date endDate) {
		int [] ymd = new int[3];
		for (int i=0; i<ymd.length; i++) {
			ymd[i] = 0;
		}
		if (startDate == null || endDate == null) {
			return ymd;
		}

		Calendar calendarStartDate = Calendar.getInstance();
		Calendar calendarEndDate = Calendar.getInstance();

		// 设日历为相应日期
		calendarStartDate.setTime(startDate);
		calendarEndDate.setTime(endDate);
		if (startDate.after(endDate)) {
			Calendar swap = calendarStartDate;
			calendarStartDate = calendarEndDate;
			calendarEndDate = swap;
		}

		int years =
			DateUtil.getActualYears(
				calendarStartDate.getTime(),
				calendarEndDate.getTime());
		calendarStartDate.add(Calendar.YEAR, years);

		int months =
			DateUtil.getActualMonths(
				calendarStartDate.getTime(),
				calendarEndDate.getTime());
		calendarStartDate.add(Calendar.MONTH, months);

		int days =
			DateUtil.getDaysBetween(
				calendarStartDate.getTime(),
				calendarEndDate.getTime());

		ymd[0] = years;
		ymd[1] = months;
		ymd[2] = days;

		return ymd;
	}
	
	/**
	 * 根据上一次还款日期推算提前还款的约定扣款日
	* @Title: getArrangeDebit 
	* @Description: TODO
	* @author: gzb
	* @param @return
	* @return Date
	* @throws
	 */
	public static Date getArrangeDebit(Date preDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(preDate);
		calendar.add(Calendar.MONTH, 1);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(day<=5){
			day = 1;
		}else{
			day = day-5;
		}
		calendar.set(year, month, day);
		return calendar.getTime();
	}
	
	/**
	 * 计算两个日期之间相差的天数   
	* @Title: daysBetween 
	* @Description: TODO
	* @author: gzb
	* @param @param startDate
	* @param @param endDate
	* @param @return
	* @return int
	* @throws
	 */
	public static int daysBetween(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(endDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 根据当前时间获得HHmm,当前时间为15：20，则返加1520
	* @Title: getHoursMinutes 
	* @Description: TODO
	* @author: gzb
	* @param @param date
	* @param @return
	* @return int
	* @throws
	 */
	public static int getHoursMinutes(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		String hm = hours+""+minutes;
		return Integer.valueOf(hm);
	}
	
	/**
	 * 取得日期年月4位序列号,如2014-06-23,返回结果为1406
	 * @param date
	 * @return
	 */
	public static String getYearMonth(Date date){ 
		SimpleDateFormat sf = new SimpleDateFormat("yyMM");
		return sf.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getYearMonth(new Date()));
	}

	/**
	 * 将日期转化为指定模式的字符串
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String timeFormat(Date time, String pattern){
		if (time == null)
			return null;

		SimpleDateFormat simpleDateFormat =
			new SimpleDateFormat(pattern);
		return simpleDateFormat.format(time);
	}

	/**  
     * 得到几天前的时间  
     *   
     * @param d  
     * @param day  
     * @return  
     */  
    public static Date getDateBefore(Date d, int day) {   
        Calendar now = Calendar.getInstance();   
        now.setTime(d);   
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);   
        return now.getTime();   
    } 

}

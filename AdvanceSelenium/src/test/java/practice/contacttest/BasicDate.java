package practice.contacttest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BasicDate {
	public static void main(String[] args) {
		//To get the today date
		Date date=new Date();
		//System.out.println(date.toString());
		SimpleDateFormat simpDate=new SimpleDateFormat("yyyy-MM-dd");
		String actDate = simpDate.format(date);
		System.out.println(actDate);
		
		//Next required beforedate
		Calendar cal= simpDate.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -30);
		String beforeDate=simpDate.format(cal.getTime());
		System.out.println(beforeDate);
		//Next required afterDate
		cal.add(Calendar.DAY_OF_MONTH, 60);
		String afterDate=simpDate.format(cal.getTime());
		System.out.println(afterDate);
		
		
	}

}

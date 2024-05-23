package practiceTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCheck {
	public static void main(String[] args) {
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		 sim.format(date);
		
		
      	Calendar cal= sim.getCalendar();
	     cal.add(Calendar.DAY_OF_MONTH,-30);
	     String endDate=sim.format(cal.getTime());
	     System.out.println(endDate);
	     
	}

}

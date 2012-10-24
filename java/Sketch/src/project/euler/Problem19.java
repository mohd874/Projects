package project.euler;

import java.util.Calendar;
import java.util.Date;

public class Problem19 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		
		start.set(1901, 1, 1);
		end.set(2000, 12, 31);
		
		int count = 0;
		
		while(start.getTimeInMillis() < end.getTimeInMillis()){
			if(start.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				count++;
			}
			start.add(Calendar.MONTH, 1);
		}
		
		System.out.println(count);
	}

}

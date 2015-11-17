package iat351.nasira;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Calendar {
	private ArrayList<Day> days = new ArrayList<>();
	
	public Calendar() {
		
	}

	public ArrayList<Day> getDays() {
		return days;
	}
	
	/*
	 * @Return true if the day was added, false otherwise.
	 */
	public boolean addDay(Day day) {
		if(days.contains(day)) {
			return false;
		}
		
		days.add(day);
		
		return true;
	}
	
	public Day findDay(Date date) {
		Day findDay = new Day(date);
		
		for(Day day : days) {
			if (day.equals(findDay)) {
				return day;
			}
		}
		
		return null;
	}
}

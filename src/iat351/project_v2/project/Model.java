package iat351.project_v2.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Model {
	private final static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Model() {
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public EventView newEvent() {  
		Event event = new Event(this);
		events.add(event);
		return event.getEventFrame();
	}
	
	public Date getDate(int year, int month,int day, int hrs,int min,int sec){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year + 1900, month, day, hrs, min, sec);
		Date date = calendar.getTime();
		return date;
	}
	
	public Date getDate(){
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		return date;
	}
	
	public String getTimeString(Date date){
		String theTime = dateFormat.format(date);
		return theTime;
	}
	
	public ArrayList<Integer> parseTime(String time) {		
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		for (int i = 0; i < time.length(); i++) {
			String digitString = Character.toString(time.charAt(i));
			
			if (!digitString.equals(":")) {
				try {
					digits.add(Integer.parseInt(digitString));
				} catch (Exception e) {
					System.out.println("Could not parse String digit to int");
				}
			} // if
		} // for
		
		return digits;
	} // setTime
}
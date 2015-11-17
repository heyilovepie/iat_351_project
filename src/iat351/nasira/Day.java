package iat351.nasira;

import java.util.ArrayList;
import java.util.Date;

public class Day {
	
	private Date date;
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Day(Date date) {
		this.date = date;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public void addEvent(Event e) {
		events.add(e);
	}

	public Date getDate() {
		return date;
	}

}

package iat351.nasira;

import java.util.Date;
import java.util.Set;

public class Main {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Calendar c = new Calendar();
		
		// Create day and event for halloween
		Date halloweenDate = new Date(2015, 10, 31);
		Day halloween = new Day(halloweenDate);
		
		Date startTime = new Date(2015, 10, 31, 18, 00);
		Date endTime = new Date(2015, 10, 31, 21, 30);
		Event trickOrTreat = new Event("Trick or treat", "SFU", startTime, endTime);
		trickOrTreat.addTag("Candy");
		
		Note note = new Note("lots of candy", "Got 7.5lbs of candy");
		trickOrTreat.addNote(note);
		
		halloween.addEvent(trickOrTreat);
		c.addDay(halloween);
		
		// Create day and event for thanksgiving
		Date thanksgivingDate = new Date(2015, 10, 12);
		Day thanksgiving = new Day(thanksgivingDate);
		
		Date startTime1 = new Date(2015, 10, 12, 18, 00);
		Date endTime1 = new Date(2015, 10, 31, 19, 30);
		Event dinner = new Event("Thanksgiving", "Home", startTime1, endTime1);
		dinner.addTag("Candy");
		dinner.addTag("Food");
		
		Note note1 = new Note("Food", "Ate too much");
		dinner.addNote(note1);
		
		thanksgiving.addEvent(dinner);
		c.addDay(thanksgiving);
		
		
		System.out.println("--------Trick or treat tags-------");
		Set<Event> events = Event.findEventByTag("Trick or treat");
		for(Event e : events) {
			System.out.println(e.getTitle());
			for(Note n : e.getNotes()) {
				System.out.println("\t" + n.getTitle() + ": " + n.getNote());
			}
		}
		
		System.out.println("--------Candy-------");
		Set<Event> events1 = Event.findEventByTag("CAnDY");
		for(Event e : events1) {
			System.out.println(e.getTitle());
			for(Note n : e.getNotes()) {
				System.out.println("\t" + n.getTitle() + ": " + n.getNote());
			}
		}
	}
}

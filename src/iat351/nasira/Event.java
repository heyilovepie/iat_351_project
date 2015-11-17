package iat351.nasira;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Event class. Contains a title, location, start time, end time, tags and notes
 * Can be searched using tags. The title of each event is also a tag for that event
 */

public class Event {
	private String title;
	private String location;
	private Date startTime;
	private Date endTime;
	private ArrayList<String> tags;
	private ArrayList<Note> notes;
	
	public static Map<String, Set<Event>> eventTagsTable = 
			new HashMap<String, Set<Event>>();

	public Event(String title, String location, Date startTime, Date endTime,
			ArrayList<String> tags, ArrayList<Note> notes) {
		this.title = title;
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tags = tags;
		this.notes = notes;
		
		for(String tag : tags) {
			updateEventTagsTable(tag, this);
		}
		
		updateEventTagsTable(title, this);
	}
	
	public Event(String title, String location, Date startTime, Date endTime) {
		this(title, location, startTime, endTime, new ArrayList<String>(), new ArrayList<Note>());
	}
	
	public Event(String title, String location, Date startTime, Date endTime,
			ArrayList<String> tags) {
		this(title, location, startTime, endTime, tags, new ArrayList<Note>());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void addTag(String tag) {
		updateEventTagsTable(tag, this);
		tags.add(tag);
	}
	
	public void removeTag(String tag) {
		if(eventTagsTable.containsKey(tag)) {
			eventTagsTable.get(tag).remove(this);
			tags.remove(tag);
		}
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void addNote(Note note) {
		notes.add(note);
	}
	
	public static void removeEventFromTable(Event e) {
		for(String tag : e.tags) {
			eventTagsTable.get(tag.toLowerCase()).remove(e);
			e.tags.remove(tag.toLowerCase());
		}
	}
	
	private static void updateEventTagsTable(String tag, Event e) {
		if(eventTagsTable.containsKey(tag.toLowerCase())) {
			eventTagsTable.get(tag.toLowerCase()).add(e);
		} else {
			Set<Event> events = new HashSet<Event>();
			events.add(e);
			eventTagsTable.put(tag.toLowerCase(), events);
		}
	}
	
	public static Set<Event> findEventByTag(String tag) {
		return Event.eventTagsTable.get(tag.toLowerCase());
	}
}

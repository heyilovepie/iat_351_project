package iat351.project_v2.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
	//get the parent that contains the event
	
	protected Model model;
	private String title;
	private String location;
	
	private Date startDate;
	private Date endDate;
	private Date createDate;
	
	private ArrayList<String> tags;
	private ArrayList<Note> notes;
	
	public static Map<String, Set<Event>> eventTagsTable = 
			new HashMap<String, Set<Event>>();
	
	public Event(Model model){
		this.model = model;
		createDate = getDate();
	}

	public Event(Model model, Date startDate) {
		/*
		 * Used when you press on a certain day in the calendar instead of "New Event"
		 */
		this(model);
		this.startDate = startDate;
		this.endDate = startDate;
	}
	
	public void setData(String title, String location, Date startDate, Date endDate,
	ArrayList<String> tags, ArrayList<Note> notes){
		/*
		 * Used when you press "ok" on the Event frame when you edit it. This will change all variables 
		 */
		this.title = title;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tags = tags;
		this.notes = notes;
		
		for(String tag : tags) {
			updateEventTagsTable(tag, this);
		}
		
		updateEventTagsTable(title, this);
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	public Date getDate(int year, int month, int day, int hrs, int min, int sec){
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
	
	//access the view
		public EventView getEventFrame(){
			EventView eventView = new EventView();
			eventView.setTitle("New Event");
			
			//set Controllers
			ActionListener saveAction = new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("saving");
				}
			};
			
			eventView.save.addActionListener(saveAction);
			
			ActionListener toggleAction = new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("toggle");
					eventView.toggleEdit();
				}
			};
			
			eventView.reset.addActionListener(toggleAction);
			eventView.edit.addActionListener(toggleAction);
			
			ActionListener okAction = new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					//exit window
					System.out.println("ok");
					eventView.setVisible(false); 
					eventView.dispose(); 
				}
			};
			
			eventView.ok.addActionListener(okAction);
			//end of adding ActionListeners
			
			return eventView;
		}
}

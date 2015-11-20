package iat351.project_v2.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
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
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private ArrayList<String> tags;
	private ArrayList<Note> notes;
	
	private boolean newEvent;
	
	public static Map<String, Set<Event>> eventTagsTable = 
			new HashMap<String, Set<Event>>();

	public Event(Model model, LocalDate date) {
		/*
		 * Used when you press on a certain day in the calendar instead of "New Event"
		 */
		this.model = model;
		this.date = date;
		
		//defaults
		title = getDateString();
		location = "";
		setStartTime(0, 0);
		setEndTime(0, 0);
		tags = new ArrayList<String>();
		notes = new ArrayList<Note>();
		newEvent = true;
	}
	
	public Event(Model model){
		/*
		 * when you press "New Event"
		 */
		this(model, LocalDate.now());
	}
	
	public Event(Model model, int year, int month, int day) {
		/*
		 * Used when you press on a certain day in the calendar instead of "New Event"
		 */
		this(model, LocalDate.of(year, month, day));
	}
	//end of constructor
	
	//set and get variables
	
	public void setData(String title, String location, LocalDate date, LocalTime startTime, LocalTime endTime,
	ArrayList<String> tags, ArrayList<Note> notes){
		/*
		 * Used when you press "ok" on the Event frame when you edit it. This will change all variables 
		 */
		this.title = title;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
	//date
	public LocalDate getDate(){
		return date;
	}
	
	public int getYear(){
		return date.getYear();
	}
	
	public int getMonthInt(){
		return date.getMonth().getValue();
	}
	
	public Month getMonth(){
		return date.getMonth();
	}
	
	public String getMonthName(){
		return date.getMonth().name();
	}

	public int getDay(){
		return date.getDayOfMonth();
	}
	
	public void setDate(LocalDate date){
		this.date = date;
	}
	
	public void setDate(int year, int month, int day){
		setDate(LocalDate.of(year, month, day));
	}
	
	// start time
	public LocalTime getstartTime() {
		return startTime;
	}
	
	public int getStartHour(){
		return startTime.getHour();
	}
	
	public int getStartMin(){
		return startTime.getMinute();
	}
	
	private String getDateString(){
		return getMonthName() + " " + Integer.toString(getDay()) + ", " + Integer.toString(getYear());
	}
	
	private String getStartTimeString(){
		return Integer.toString(getStartHour()) + ":" + Integer.toString(getStartMin());
	}
	
	private String getEndTimeString(){
		return Integer.toString(getEndHour()) + ":" + Integer.toString(getEndMin());
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public void setStartTime(int hour, int min) {
		setStartTime(LocalTime.of(hour, min, 0, 0));
	}

	// end time
	public LocalTime getEndTime() {
		return endTime;
	}
	
	public int getEndHour(){
		return endTime.getHour();
	}
	
	public int getEndMin(){
		return endTime.getMinute();
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public void setEndTime(int hour, int min) {
		setEndTime(LocalTime.of(hour, min, 0, 0));
	}
	
	//tags
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
	
	//access the view
	public EventView getEventFrame(UIDelegate uiDelegate){
		//returns the event view to uiDelegate
		EventView eventView = new EventView(newEvent);
		eventView.setTitle("New Event");
		
		setDefaultValuesFor(eventView);
		addActionListenersTo(uiDelegate, eventView);
		
		//you are no longer editing this Event for the first time
		newEvent = false; 
		return eventView;
	}
	
	public void setDefaultValuesFor(EventView eventView){
		eventView.title.setText(title);
		eventView.location.setText(location);
		String date = getDateString();
		eventView.date.setText(date);
		String startTime = getStartTimeString();
		eventView.startTime.setText(startTime);
		String endTime = getEndTimeString();
		eventView.endTime.setText(endTime);
		String tagsString = "";
		for(int i = 0; i < tags.size(); i++){
			tagsString += tags.get(i) + ", ";
		}
		eventView.tags.setText(tagsString);
		
		String notesString = "";
		for(int i = 0; i < notes.size(); i++){
			notesString += notes.get(i).getTitle() + ", ";
		}
		eventView.notes.setText(notesString);
		
		eventView.refreshUI();
	}
	
	public void addActionListenersTo(UIDelegate uiDelegate, EventView eventView){
		/*add all action listeners to eventView that is a child of the JFrame uiDelegate */
		
		//make action listeners
		ActionListener saveAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the info in here from the user inputs
				eventView.toggleEdit();
				//set model values
				setTitle(eventView.title.getText());
				setLocation(eventView.location.getText());
				
				uiDelegate.resetCalendarItems();
			}
		};
		
		ActionListener editAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//edit view
				eventView.toggleEdit();
			}
		};
		
		ActionListener resetAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//change to view mode and reset values
				eventView.toggleEdit();
				setDefaultValuesFor(eventView);
			}
		};
		
		ActionListener okAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//exit window
				eventView.setVisible(false); 
				eventView.dispose();
			}
		};
		
		ActionListener addNoteAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//add Note
				makeNote(uiDelegate);
			}
		};
		
		eventView.addNote.addActionListener(addNoteAction);
		eventView.addActionListeners(saveAction, resetAction, okAction, editAction, okAction);
		//end of adding ActionListeners
		
	}
	
	public void makeNote(UIDelegate uiDelegate){
		/*Makes a note that is a child of the uiDelegate*/
		/* note: this is here because otherwise a call to 'this' will get the actionlistener */
		Note note = new Note();
		notes.add(note);
		uiDelegate.newNote(note, this);
	}
}

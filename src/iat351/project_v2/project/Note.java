package iat351.project_v2.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Note {
	protected NoteModel noteModel;
	protected boolean newNote;
	
	
	public Note(String title, String note) {
		this();
		noteModel.setNote(note);
		noteModel.setTitle(title);
	}
	
	public Note(){
		noteModel = new NoteModel();
		newNote = false;
	}
	
	//access the view
	public NoteView getNoteFrame(){
		NoteView noteView = new NoteView();
		noteView.setTitle("This is a note");
		String note = noteModel.getNote();
		if(note != null){
			noteView.setNotebookText(note);
		}
		return noteView;
	}

	//access the model
	public String getTitle() {
		return noteModel.getTitle();
	}
	
	public void setTitle(String title) {
		noteModel.setTitle(title);
	}
	
	public String getNote() {
		return noteModel.getNote();
	}
	
	public void setNote(String note) {
		noteModel.setNote(note);
	}
	
	public NoteView getEventFrame(UIDelegate uiDelegate){
		NoteView noteView = new NoteView(newNote);
		noteView.setTitle("New Event");
		
		setDefaultValuesFor(noteView);
		addActionListenersTo(uiDelegate, noteView);
		
		//you are no longer editing this Event for the first time
		newNote = false; 
		return noteView;
	}
	
	public void addActionListenersTo(UIDelegate uiDelegate, NoteView noteView){
		/*add all action listeners to noteView that is a child of the JFrame uiDelegate */
		
		//make action listeners
		ActionListener saveAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//update the info in here from the user inputs
				noteView.toggleEdit();
				//set model values
				noteModel.setTitle(noteView.title.getText());
				noteModel.setNote(noteView.getNote());
				
				uiDelegate.resetCalendarItems();
			}
		};
		
		ActionListener editAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//edit view
				noteView.toggleEdit();
			}
		};
		
		ActionListener resetAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//change to view mode and reset values
				noteView.toggleEdit();
				setDefaultValuesFor(noteView);
			}
		};
		
		ActionListener okAction = new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				//exit window
				noteView.setVisible(false); 
				noteView.dispose();
			}
		};
		
		noteView.addActionListeners(saveAction, resetAction, okAction, editAction, okAction);
		//end of adding ActionListeners
		
	}
	
	public void setDefaultValuesFor(NoteView noteView){
		noteView.title.setText(noteModel.getTitle());
		noteView.setNote(noteModel.getNote());
		noteView.refreshUI();
	}
	
}

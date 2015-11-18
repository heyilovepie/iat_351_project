package iat351.project_v2.project;

public class Note {
	protected NoteModel noteModel;
	
	
	public Note(String title, String note) {
		this();
		noteModel.setNote(note);
		noteModel.setTitle(title);
	}
	
	public Note(){
		noteModel = new NoteModel();
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
}

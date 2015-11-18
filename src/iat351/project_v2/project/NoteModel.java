package iat351.project_v2.project;

public class NoteModel {
	private String title;
	private String note;
	
	public NoteModel(){
	}
	
	public NoteModel(String title, String note) {
		this();
		this.title = title;
		this.note = note;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
}

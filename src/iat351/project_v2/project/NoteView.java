package iat351.project_v2.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class NoteView extends UIDelegateFrame {
	/*
	 * The main JFrame
	 * Contains all view elements
	 */
	
	// Panels
	public EnterPanel title;
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private BottomSavePanel bottomSavePanel;
	
	private NotebookView notebookView;
	private boolean isEditing;
	
	public NoteView() {
		super();
		this.isEditing = true;
		super.constructor(); //call super constructor
	} // Constructor
	
	public NoteView(boolean isEditing) {
		super();
		this.isEditing = isEditing; //set editing variable
		super.constructor(); //call super constructor
	} // Constructor
	
	public NoteView(String name){
		this();
		setWindowTitle("Name");
	}

	public NoteView(String name, boolean isEditing){
		this(isEditing);
		setWindowTitle("Name");
	}
	
	protected void constructor(){
		/*this is intentionally empty to override the parent class */
	}
	
	// ===================================
	// View & Controller
	// ===================================
	
	//init
	protected void initPanels() {	
		//init
		title = new EnterPanel("Title", true);
		
		topPanel = new JPanel();
		middlePanel = new JPanel();
		bottomSavePanel = new BottomSavePanel(true);
		
		//top
		topPanel.add(notebookView.getTopPanel());
		//bottom
		middlePanel.setLayout(new BorderLayout());
		middlePanel.add(notebookView.getBottomPanel(), BorderLayout.CENTER);
		
		
		
		//main (contains top and bottom)
		setLayout(new BorderLayout());
		setBackground(Color.GRAY);
		add(topPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(bottomSavePanel, BorderLayout.SOUTH);
	}
	
	protected void initWindow() {
		/*
		 * make window
		 */
		super.initWindow();
		setWindowTitle("Note");
	}
	
	protected void preInit(){
		notebookView = new NotebookView(this, new Dimension(0, 0));
	}
	
	@Override
	protected void initToggleButtons() {
		// TODO Auto-generated method stub
		
	}
	//end if init

	public void setNotebookText(String note){
		notebookView.setNote(note);
		refreshUI();
	}
	
	public void setWindowTitle(String title){
		setTitle(title);
	}
	
	public void toggleEdit(){
		isEditing = !isEditing;		
		bottomSavePanel.toggleEdit();
		toggleEnterPanels();
		refreshUI();
	}
	
	protected void toggleEnterPanels(){
		title.toggleEditable();

	}
	
	public void addActionListeners(ActionListener saveL, ActionListener resetL, 
			ActionListener deleteL, ActionListener editL, ActionListener okL){
		
		bottomSavePanel.addActionListeners(saveL, resetL, deleteL, editL, okL);	
		
	}
	
	public void setNote(String note){
		notebookView.setNote(note);
	}
	
	public String getNote(){
		return notebookView.getNote();
	}
}

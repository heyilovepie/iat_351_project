package iat351.project_v2.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class EventView extends UIDelegateFrame {
	/*
	 * The Event JFrame
	 */
	
	// Panels
	public EnterPanel title, location, date, startTime, endTime, tags;
	public EnterPanel notes = new EnterPanel("Notes", 10); // TODO temp var add buttons 
	
	private JPanel addNotePanel;
	private BottomSavePanel bottomSavePanel;
	private JPanel editPanel, viewPanel;
	
	public JButton save, reset, addNote, edit, ok;
	
	public boolean isEditing;
	
	public EventView(boolean isEditing) {
		super();
		this.isEditing = isEditing; //set editing variable
		super.constructor(); //call super constructor
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================
	
	protected void constructor(){
		/*this is intentionally empty to override the parent class */
	}
	
	protected void preInit(){
		//change window size to different that the default
		WIDTH = 500;
		HEIGHT = 400;
		MIN_WINDOW_SIZE = new Dimension((int) WIDTH, (int) HEIGHT);
	}
	
	protected void initWindow() {
		//make the window
		super.initWindow();
		setTitle("Note");
		setLayout(new FlowLayout());
	}
	
	
	@Override
	protected void initToggleButtons() {		
	}
	
	//init
	protected void initPanels() {
		title = new EnterPanel("Event: ", 20, isEditing);
		date =  new EnterPanel("Date: ", 20, isEditing);
		
		JPanel timePanel = new JPanel();
		timePanel.setLayout(new FlowLayout());
		timePanel.setPreferredSize(new Dimension(WIDTH,  HEIGHT / 6));
		startTime = new EnterPanel("Start Time: ", 5, isEditing);
		endTime = new EnterPanel("End Time: ", 5, isEditing);
		timePanel.add(startTime);
		timePanel.add(endTime);
		add(timePanel);
		
		location = new EnterPanel("Location: ", 20, isEditing);
		tags = new EnterPanel("Tags: ", 20, isEditing);
		
		add(title);
		add(date);
		add(startTime);
		add(endTime);
		add(location);
		add(tags);
		
		addNotePanel = new JPanel();
		addNotePanel.setLayout(new FlowLayout());
		addNotePanel.setPreferredSize(new Dimension(WIDTH,  HEIGHT / 6));
		addNote = new JButton("Add Note");	
		addNotePanel.add(addNote);
		add(addNotePanel);
		
		bottomSavePanel = new BottomSavePanel(isEditing);
		//bottomSavePanel.add(new JButton("mew"));
		bottomSavePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
		add(bottomSavePanel);
	}
	//end if init
	
	//get and set
	
	
	public void toggleEdit(){
		isEditing = !isEditing;		
		bottomSavePanel.toggleEdit();
		toggleEnterPanels();
		refreshUI();
	}
	
	protected void toggleEnterPanels(){
		title.toggleEditable();
		location.toggleEditable();
		date.toggleEditable();
		startTime.toggleEditable();
		endTime.toggleEditable();
		tags.toggleEditable();
	}
	
	public void addActionListeners(ActionListener saveL, ActionListener resetL, 
			ActionListener deleteL, ActionListener editL, ActionListener okL){
		
		bottomSavePanel.addActionListeners(saveL, resetL, deleteL, editL, okL);	
		
	}
} // UIDelegate

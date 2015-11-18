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
	
	private JPanel addNotePanel, bottomPanel;
	private JPanel editPanel, viewPanel;
	
	public JButton save, reset, addNote, edit, ok;
	
	public boolean isEditing;
	
	public EventView() {
		super();
		isEditing = true;
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================
	
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
		// TODO Auto-generated method stub
		
	}
	
	//init
	protected void initPanels() {
		title = new EnterPanel("Event: ", 20);
		date =  new EnterPanel("Date: ", 20);
		
		JPanel timePanel = new JPanel();
		timePanel.setLayout(new FlowLayout());
		timePanel.setPreferredSize(new Dimension(WIDTH,  HEIGHT / 6));
		startTime = new EnterPanel("Start Time: ", 5);
		endTime = new EnterPanel("End Time: ", 5);
		timePanel.add(startTime);
		timePanel.add(endTime);
		add(timePanel);
		
		location = new EnterPanel("Location: ", 20);
		tags = new EnterPanel("Tags: ", 20);
		
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
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
		
		reset = new JButton("Reset");
		save = new JButton("Save");
		
		edit = new JButton("Edit");
		ok = new JButton("Ok");
		
		editPanel = new JPanel();
		editPanel.setLayout(new BorderLayout());
		editPanel.add(reset, BorderLayout.WEST);
		editPanel.add(save, BorderLayout.EAST);
		
		viewPanel = new JPanel();
		viewPanel.setLayout(new BorderLayout());
		viewPanel.add(edit, BorderLayout.WEST);
		viewPanel.add(ok, BorderLayout.EAST);
		
		if(isEditing){
			bottomPanel.add(editPanel);
		}else{
			bottomPanel.add(viewPanel);
		}
		add(bottomPanel);
	}
	//end if init
	
	//get and set
	
	
	public void toggleEdit(){
		// TODO this is not working!!!
		if(isEditing){
			System.out.println("is editing right now");
			isEditing = false;
			updatePanel(bottomPanel, viewPanel, editPanel);
		}else{
			isEditing = true;
			updatePanel(bottomPanel, editPanel, viewPanel);
		}
		
		refreshUI();
	}
} // UIDelegate

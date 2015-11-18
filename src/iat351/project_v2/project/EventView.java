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
	private EnterPanel event, date, startTime, endTime, location, tags;
	
	public EventView() {
		super();
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================
	
	protected void preInit(){
		//change window size to different that the default
		WIDTH = 500;
		HEIGHT = 300;
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
		event = new EnterPanel("Event: ", 20);
		date =  new EnterPanel("Date: ", 20);
		startTime = new EnterPanel("Start Time: ", 20);
		endTime = new EnterPanel("End Time: ", 20);
		location = new EnterPanel("Location: ", 20);
		tags = new EnterPanel("Tags: ", 20);
		
		add(event);
		add(date);
		add(startTime);
		add(endTime);
		add(location);
		add(tags);
	}
	//end if init
} // UIDelegate

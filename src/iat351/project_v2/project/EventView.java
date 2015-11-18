package iat351.project_v2.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	
	public EventView() {
		super();
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================
	
	//init
	protected void initPanels() {	
		//init
		mainPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		//top
		topPanel.setBackground(Color.GRAY);
		//bottom
		bottomPanel.setLayout(new BorderLayout());
		
		//main (contains top and bottom)
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		
		//add to frame
		add(mainPanel, BorderLayout.CENTER);
	}
	
	protected void initWindow() {
		/*
		 * make window
		 */
		super.initWindow();
		setTitle("Note");
	}
	
	protected void preInit(){
		//change window size to different that the default
		WIDTH = 500;
		HEIGHT = 200;
		MIN_WINDOW_SIZE = new Dimension((int) (WIDTH / 1.5), (int) (HEIGHT / 1.5));
	}
	
	@Override
	protected void initToggleButtons() {
		// TODO Auto-generated method stub
		
	}
	//end if init
} // UIDelegate

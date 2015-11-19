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

public class NoteView extends UIDelegateFrame {
	/*
	 * The main JFrame
	 * Contains all view elements
	 */
	
	// Panels
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private NotebookView notebookView;
	
	public NoteView() {
		super();
	} // Constructor
	
	public NoteView(String name){
		this();
		setWindowTitle("Name");
	}

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
		topPanel.add(notebookView.getTopPanel());
		//bottom
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(notebookView.getBottomPanel(), BorderLayout.CENTER);
		
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
		setWindowTitle("Note");
	}
	
	protected void preInit(){
		notebookView = new NotebookView(this, new Dimension(0, 0));
	}

	public void setNotebookText(String note){
		notebookView.setNote(note);
		refreshUI();
	}
	
	public void setWindowTitle(String title){
		setTitle(title);
	}
}

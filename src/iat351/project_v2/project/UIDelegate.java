package iat351.project_v2.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Date;

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

public class UIDelegate extends UIDelegateFrame {
	/*
	 * The main JFrame
	 * Contains all view elements
	 */
	
	// Constants
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 600;
	private static final Dimension SIDE_PANEL_SIZE = new Dimension(WIDTH / 6, HEIGHT);

	//Model
	// TODO load from file
	private Model model = new Model();
	
	// Panels
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel sidePanel;
	private CalendarView calendarView;
	private NotebookView notebookView;

	// Buttons
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;
	
	public UIDelegate() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================

	private JToggleButton createToggleButton(String text) {
		JToggleButton btn = new JToggleButton(text);
		btn.setActionCommand(text);

		btn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// if a toggle button is pressed then change the state of the frame
				
				String btnName = btn.getActionCommand(); 
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					if (btnName.equals("Calendar")) {
						updateToggleButtons(btnCalendar, btnNotebook);
						//switch the panels from notebook to calendar
						updatePanel(topPanel, calendarView.getTopPanel(), notebookView.getTopPanel());
						updatePanel(bottomPanel, calendarView.getBottomPanel(), notebookView.getBottomPanel());
						updatePanel(sidePanel, calendarView.getSidePanel(), notebookView.getSidePanel());
						refreshUI();
					} else if (btnName.equals("Notebook")) {
						updateToggleButtons(btnNotebook, btnCalendar);
						//switch the panels from calendar to notebook
						updatePanel(topPanel, notebookView.getTopPanel(), calendarView.getTopPanel());
						updatePanel(bottomPanel, notebookView.getBottomPanel(), calendarView.getBottomPanel());
						updatePanel(sidePanel, notebookView.getSidePanel(), calendarView.getSidePanel());
						refreshUI();
					}
				}
			}
		}); // end of addItemListener

		return btn;
	} // end of createToggleButton

	// ===================================
	// View
	// ===================================
	
	protected void initToggleButtons() {
		btnCalendar = createToggleButton("Calendar");
		btnNotebook = createToggleButton("Notebook");
		updateToggleButtons(btnCalendar, btnNotebook);
	}
	
	protected void initPanels() {
		mainPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		sidePanel = new JPanel();
		
		//top
		topPanel.setBackground(Color.GRAY);
		topPanel.add(calendarView.getTopPanel());
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(calendarView.getBottomPanel(), BorderLayout.CENTER);
		
		//main
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		
		//side 
		sidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
		sidePanel.add(calendarView.getSidePanel());
		
		add(sidePanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
	}
	
	protected void initWindow() {
		/*
		 * make window
		 */
		super.initWindow();
		setTitle("Calendar Notebook");
	}
	
	
	protected void preInit(){
		calendarView = new CalendarView(this, SIDE_PANEL_SIZE);
		notebookView = new NotebookView(this, SIDE_PANEL_SIZE);
	}

	public void refreshUI() {
		revalidate();
		repaint();
	}
	
	public void newEvent(){
		model.newEvent();
	}
	
	public void newEvent(int year, int month, int day){
		model.newEvent(year, month, day);
	}
	
	public void newEvent(LocalDate date){
		model.newEvent(date);
	}
} // UIDelegate

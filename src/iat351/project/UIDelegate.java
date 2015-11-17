package iat351.project;

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

public class UIDelegate extends JFrame {
	/*
	 * The main JFrame
	 * Contains all view elements
	 */
	
	// Constants
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	private static final Dimension MIN_WINDOW_SIZE = new Dimension((int) (WIDTH / 1.5), (int) (HEIGHT / 1.5));
	private static final Dimension SIDE_PANEL_SIZE = new Dimension(WIDTH / 5, HEIGHT);

	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel sidePanel = new JPanel();
	private CalendarView calendarView = new CalendarView(this, SIDE_PANEL_SIZE);
	private NotebookView notebookView = new NotebookView(this, SIDE_PANEL_SIZE);

	// Buttons
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;
	
	public UIDelegate() {
		initToggleButtons();
		initPanels();
		initWindow();
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
				String btnName = btn.getActionCommand();
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					if (btnName.equals("Calendar")) {
						updateToggleButtons(btnCalendar, btnNotebook);
						updatePanel(topPanel, calendarView.getTopPanel(), notebookView.getTopPanel());
						updatePanel(bottomPanel, calendarView.getBottomPanel(), notebookView.getBottomPanel());
						updatePanel(sidePanel, calendarView.getSidePanel(), notebookView.getSidePanel());
						refreshUI();
					} else if (btnName.equals("Notebook")) {
						updateToggleButtons(btnNotebook, btnCalendar);
						updatePanel(topPanel, notebookView.getTopPanel(), calendarView.getTopPanel());
						updatePanel(bottomPanel, notebookView.getBottomPanel(), calendarView.getBottomPanel());
						updatePanel(sidePanel, notebookView.getSidePanel(), calendarView.getSidePanel());
						refreshUI();
					}
				}
			}
		}); // addItemListener

		return btn;
	} // createToggleButton
	
	public void updateToggleButtons(JToggleButton selectedBtn, JToggleButton unselectedBtn) {
		selectedBtn.setEnabled(false);
		unselectedBtn.setEnabled(true);
		unselectedBtn.setSelected(false);
	}

	// ===================================
	// View
	// ===================================
	
	private void initToggleButtons() {
		btnCalendar = createToggleButton("Calendar");
		btnNotebook = createToggleButton("Notebook");
		updateToggleButtons(btnCalendar, btnNotebook);
	}
	
	private void initPanels() {		
		topPanel.setBackground(Color.GRAY);
		topPanel.add(calendarView.getTopPanel());
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(calendarView.getBottomPanel(), BorderLayout.CENTER);
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		
		sidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
		sidePanel.add(calendarView.getSidePanel());
	}
	
	private void initWindow() {
		add(sidePanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
		setTitle("Calendar Notebook");
		setMinimumSize(MIN_WINDOW_SIZE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void updatePanel(JPanel panel, JPanel add, JPanel remove) {
		panel.add(add);
		panel.remove(remove);
	}

	public void refreshUI() {
		revalidate();
		repaint();
	}
} // UIDelegate

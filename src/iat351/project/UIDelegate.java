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
	// Constants
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	private static final Dimension MIN_WINDOW_SIZE = new Dimension((int) (WIDTH / 1.5), (int) (HEIGHT / 1.5));
	private static final Dimension SIDE_PANEL_SIZE = new Dimension(WIDTH / 5, HEIGHT);

	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel calendarTopPanel = new JPanel();
	private JPanel notebookTopPanel = new JPanel();
	
	private JPanel sidePanel = new JPanel();
	private JPanel calendarSidePanel = new JPanel();
	private JPanel notebookSidePanel = new JPanel();
	private JTextPane textPane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(textPane);

	// Buttons
	private JToggleButton btnCalendar;
	private JButton btnNewEvent;
	private JButton btnToday;
	private JToggleButton btnMonth;
	private JToggleButton btnAgenda;

	private JToggleButton btnNotebook;
	private JButton btnNewNotebook;
	
	public UIDelegate() {
		textPane.setText("This is a text pane");
		
		btnCalendar = createToggleButton("Calendar", true);
		btnNotebook = createToggleButton("Notebook", false);
		
		btnMonth = createToggleButton("Month", true);
		btnAgenda = createToggleButton("Agenda", false);
		btnToday = createButton("Today");
		calendarTopPanel.add(btnMonth);
		calendarTopPanel.add(btnAgenda);
		calendarTopPanel.add(btnToday);	
		
		// Calendar side panel
		btnNewEvent = createButton("New Event");
		calendarSidePanel.setLayout(new BoxLayout(calendarSidePanel, BoxLayout.PAGE_AXIS));
		calendarSidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		calendarSidePanel.add(btnNewEvent);
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "test");
			}
		});

		// Notebook side panel
		btnNewNotebook = createButton("New Notebook");
		notebookSidePanel.setLayout(new BoxLayout(notebookSidePanel, BoxLayout.PAGE_AXIS));
		notebookSidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		notebookSidePanel.add(btnNewNotebook);

		// Font families
		JComboBox<String> fontsDropdown = new JComboBox<String>();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontNames = ge.getAvailableFontFamilyNames();

		for (int i = 0; i < fontNames.length; i++) {
			fontsDropdown.addItem(fontNames[i]);
		}
		fontsDropdown.setSelectedItem("Arial");
		
		// Font size
		JComboBox<Float> fontSizeDropdown = new JComboBox<Float>();
		fontSizeDropdown.setEditable(true);
		for (int i = 4; i <= 24; i += 4) {
			fontSizeDropdown.addItem(new Float(i));
		}
		fontSizeDropdown.setSelectedItem(new Float(14));
		
		notebookTopPanel.add(fontsDropdown);
		notebookTopPanel.add(fontSizeDropdown);
		notebookTopPanel.add(createButton("B"));
		notebookTopPanel.add(createButton("I"));
		notebookTopPanel.add(createButton("U"));

		// TODO Refactor above code into classes/methods after figuring how to organize it
		initPanels();
		initWindow();
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================

	private JToggleButton createToggleButton(String text, boolean selected) {
		JToggleButton btn = new JToggleButton(text, selected);
		btn.setActionCommand(text);

		btn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					String btnName = btn.getActionCommand();

					if (btnName.equals("Calendar")) {
						btnNotebook.setSelected(false);
						updatePanel(topPanel, calendarTopPanel, notebookTopPanel);
						updatePanel(sidePanel, calendarSidePanel, notebookSidePanel);
						refreshUI();
					} else if (btnName.equals("Notebook")) {
						btnCalendar.setSelected(false);
						updatePanel(topPanel, notebookTopPanel, calendarTopPanel);
						updatePanel(sidePanel, notebookSidePanel, calendarSidePanel);
						refreshUI();
					} else if (btnName.equals("Month")) {
						btnAgenda.setSelected(false);
					} else if (btnName.equals("Agenda")) {
						btnMonth.setSelected(false);
					}
				}
			}
		}); // addItemListener

		return btn;
	} // createToggleButton

	private JButton createButton(String text) {
		JButton btn = new JButton(text);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String btnName = btn.getActionCommand();

				if (btnName.equals("New Notebook")) {
					// Add node
					DefaultMutableTreeNode root = new DefaultMutableTreeNode("New Notebook");
					root.add(new DefaultMutableTreeNode("New Page"));
					JTree tree = new JTree(root);

					// Create right-click context menu
					JPopupMenu menu = new JPopupMenu();
					createMenuItem(menu, "Rename");
					createMenuItem(menu, "Delete");
					tree.setComponentPopupMenu(menu);

					// Update tree
					notebookSidePanel.add(tree);
					refreshUI();
				} else if (btnName.equals("New Event")) {
					System.out.println("TODO new event");
				}
			}
		}); // addActionListener

		return btn;
	} // createButton

	private void createMenuItem(JPopupMenu menu, String text) {
		JMenuItem item = new JMenuItem(text);

		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				if (action.equals("Rename")) {
					System.out.println("TODO rename node");
				} else if (action.equals("Delete")) {
					System.out.println("TODO delete node");
				}
			}
		});

		menu.add(item);
	} // createMenuItem

	// ===================================
	// View
	// ===================================

	private void initWindow() {
		add(sidePanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
		setTitle("Calendar Notebook");
		setMinimumSize(MIN_WINDOW_SIZE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void initPanels() {		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		topPanel.setBackground(Color.GRAY);
		bottomPanel.setLayout(new BorderLayout());
		
		topPanel.add(calendarTopPanel);
		bottomPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(bottomPanel, BorderLayout.CENTER);
		
		sidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
		sidePanel.add(calendarSidePanel);
	}
	
	private void updatePanel(JPanel panel, JPanel add, JPanel remove) {
		panel.add(add);
		panel.remove(remove);
	}

	private void refreshUI() {
		revalidate();
		repaint();
	}
} // UIDelegate

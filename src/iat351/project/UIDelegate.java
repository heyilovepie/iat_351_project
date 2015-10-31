package iat351.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	private JPanel sidePanel = new JPanel();
	private JPanel calendarSidePanel = new JPanel();
	private JPanel notebookSidePanel = new JPanel();
	
	private JPanel topPanel = new JPanel();

	// Buttons
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;

	private JButton btnNewNotebook;
	private JButton btnNewEvent;

	public UIDelegate() {
		// Calendar side panel
		btnNewEvent = createButton("New Event");
		calendarSidePanel.setLayout(new BoxLayout(calendarSidePanel, BoxLayout.PAGE_AXIS));
		calendarSidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		calendarSidePanel.add(btnNewEvent);

		// Notebook side panel
		btnNewNotebook = createButton("New Notebook");
		notebookSidePanel.setLayout(new BoxLayout(notebookSidePanel, BoxLayout.PAGE_AXIS));
		notebookSidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		notebookSidePanel.add(btnNewNotebook);

		// Main panel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		
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

		// Notebook main panel
		topPanel.setBackground(Color.GRAY);
		topPanel.add(fontsDropdown);
		topPanel.add(fontSizeDropdown);
		topPanel.add(createButton("B"));
		topPanel.add(createButton("I"));
		topPanel.add(createButton("U"));

		JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane);

		// Main panel
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// TODO Extract above code into methods like below after figuring how to organize it
		initSidePanel();
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
						sidePanel.add(calendarSidePanel);
						sidePanel.remove(notebookSidePanel);
						updateUI();
					} else if (btnName.equals("Notebook")) {
						btnCalendar.setSelected(false);
						sidePanel.add(notebookSidePanel);
						sidePanel.remove(calendarSidePanel);
						updateUI();
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
					updateUI();
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
		setMinimumSize(MIN_WINDOW_SIZE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initSidePanel() {
		btnCalendar = createToggleButton("Calendar", true);
		btnNotebook = createToggleButton("Notebook", false);

		sidePanel.setPreferredSize(SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
	}

	private void updateUI() {
		revalidate();
		repaint();
	}
} // UIDelegate

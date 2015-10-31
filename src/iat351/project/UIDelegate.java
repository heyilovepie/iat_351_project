package iat351.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UIDelegate extends JFrame {
	// Constants
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 600;
	private static final Dimension MIN_SIDE_PANEL_SIZE = new Dimension(WIDTH / 5, HEIGHT);

	// Panels
	private JPanel mainPanel = new JPanel();
	private JPanel sidePanel = new JPanel();
	private JPanel calendarSidePanel = new JPanel();
	private JPanel notebookSidePanel = new JPanel();

	// Buttons
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;
	
	private JButton btnNewNotebook;
	private JButton btnNewEvent;
	

	public UIDelegate() {
		// Calendar side panel
		btnNewEvent = createButton("New Event");
		calendarSidePanel.add(btnNewEvent);

		// Notebook side panel
		btnNewNotebook = createButton("New Notebook");
		notebookSidePanel.add(btnNewNotebook);

		// Main panel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);

		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GRAY);
		topPanel.add(new JButton("test"));
		JTextPane textPane = new JTextPane();

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(textPane, BorderLayout.CENTER);

		// TODO Extract above code into methods like below after figuring out how to organize this mess~
		initSidePanel();
		initWindow();
	} // Constructor

	// ===================================
	// View & Controller?
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
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initSidePanel() {
		btnCalendar = createToggleButton("Calendar", true);
		btnNotebook = createToggleButton("Notebook", false);

		sidePanel.setPreferredSize(MIN_SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.LIGHT_GRAY);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
	}

	private void updateUI() {
		revalidate();
		repaint();
	}
} // UIDelegate

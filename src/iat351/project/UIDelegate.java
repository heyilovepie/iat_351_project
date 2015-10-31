package iat351.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UIDelegate extends JFrame {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	private static final Dimension MIN_SIDE_PANEL_SIZE = new Dimension(WIDTH / 5, HEIGHT);

	private ArrayList<JToggleButton> modeBtns;
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;
	
	private JPanel calendarSidePanel = new JPanel();
	private JPanel notebookSidePanel = new JPanel();

	private JButton btnNewNotebook;
	private JButton btnNewEvent;


	private JPanel sidePanel = new JPanel();
	// private JPanel mainPanel = new JPanel();
	// private JScrollPane scrollPane = new JScrollPane();
	// private JSplitPane splitPane;

	public UIDelegate() {
		// Mode buttons
		btnCalendar = createToggleButton("Calendar", true);
		btnNotebook = createToggleButton("Notebook", false);
		modeBtns = new ArrayList<JToggleButton>();
		modeBtns.add(btnCalendar);
		modeBtns.add(btnNotebook);
		
		// Calendar side panel
		btnNewEvent = createButton("New Event");
		calendarSidePanel.add(btnNewEvent);
		
		// Notebook side panel
		btnNewNotebook = createButton("New Notebook");
		notebookSidePanel.add(btnNewNotebook);

		// General side panel
		sidePanel.setPreferredSize(MIN_SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.WHITE);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
		add(sidePanel, BorderLayout.WEST);

		// scrollPane.add(sidePanel);
		// splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel,
		// mainPanel);
		// add(splitPane, BorderLayout.CENTER);

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	} // Constructor

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
						sidePanel.add(calendarSidePanel);
						sidePanel.remove(notebookSidePanel);
						updateComponents();
					} else if (btnName.equals("Notebook")) {
						sidePanel.add(notebookSidePanel);
						sidePanel.remove(calendarSidePanel);
						updateComponents();
					}
					
					// Set other mode button to false
					for (JToggleButton b : modeBtns) {
						if (!b.getActionCommand().equals(btnName)) {
							b.setSelected(false);
							
						}
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
					updateComponents();
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
	
	private void updateComponents() {
		revalidate();
		repaint();
	}
} // View

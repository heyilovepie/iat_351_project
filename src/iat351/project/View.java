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
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class View extends JFrame {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 1000;
	private static final Dimension MIN_SIDE_PANEL_SIZE = new Dimension(WIDTH / 5, HEIGHT);

	private ArrayList<JToggleButton> modeBtns;
	private JToggleButton btnCalendar;
	private JToggleButton btnNotebook;

	private JButton btnNewNotebook;

	private JTree tree1;

	private JPanel sidePanel = new JPanel();
	// private JPanel mainPanel = new JPanel();
	// private JScrollPane scrollPane = new JScrollPane();
	// private JSplitPane splitPane;

	public View() {
		// Buttons
		btnCalendar = createToggleButton("Calendar", true);
		btnNotebook = createToggleButton("Notebook", false);
		modeBtns = new ArrayList<JToggleButton>();
		modeBtns.add(btnCalendar);
		modeBtns.add(btnNotebook);
		btnNewNotebook = createButton("New Notebook");

		// Side panel
		sidePanel.setPreferredSize(MIN_SIDE_PANEL_SIZE);
		sidePanel.setBackground(Color.WHITE);
		sidePanel.add(btnCalendar);
		sidePanel.add(btnNotebook);
		sidePanel.add(btnNewNotebook);
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

				// Set other button to false
				if (state == ItemEvent.SELECTED) {
					for (JToggleButton b : modeBtns) {
						if (!b.getActionCommand().equals(btn.getActionCommand())) {
							b.setSelected(false);
						}
					}
				}
			}
		});

		return btn;
	} // createToggleButton

	private JButton createButton(String text) {
		JButton btn = new JButton(text);

		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String btnName = btn.getActionCommand();
				
				if (btnName.equals("New Notebook")) {
					DefaultMutableTreeNode root = new DefaultMutableTreeNode("New Notebook");
					root.add(new DefaultMutableTreeNode("New Page"));
					sidePanel.add(new JTree(root));
					validate();
				}
			}
		});

		return btn;
	} // createToggleButton
} // View

package iat351.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class NotebookView extends ModeView {
	/*
	 * Stores the view components from the notebook mode so that it can be switched
	 */
	private JTextPane textPane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(textPane);
	
	private JButton btnNewNotebook;
	
	public NotebookView(UIDelegate uiDelegate, Dimension sidePanelSize) {
		super(uiDelegate);
		
		// TODO refactor constructor
		textPane.setText("This is a text pane");
		
		btnNewNotebook = createButton("New Notebook");
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.setPreferredSize(sidePanelSize);
		sidePanel.add(btnNewNotebook);
		
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

		topPanel.add(fontsDropdown);
		topPanel.add(fontSizeDropdown);
		topPanel.add(new JButton("B"));
		topPanel.add(new JButton("I"));
		topPanel.add(new JButton("U"));
		
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(scrollPane, BorderLayout.CENTER);
	} // Constructor
	
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

	@Override
	protected JButton createButton(String text) {
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
					sidePanel.add(tree);
					uiDelegate.refreshUI();
				}
			}
		}); // addActionListener

		return btn;
	} // createButton
} // NotebookView

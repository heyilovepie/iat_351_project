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

abstract public class UIDelegateFrame extends JFrame {
	/*
	 * The main JFrame
	 * Contains all view elements
	 */
	
	// Constants
	protected static int WIDTH = 1000;
	protected static int HEIGHT = 600;
	protected static Dimension MIN_WINDOW_SIZE = new Dimension((int) (WIDTH / 1.5), (int) (HEIGHT / 1.5));
	
	public UIDelegateFrame() {
		preInit();
		initWindow();
		initToggleButtons();
		initPanels();
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================
	
	public void updateToggleButtons(JToggleButton selectedBtn, JToggleButton unselectedBtn) {
		/*
		 * switch what button is selected
		 */
		selectedBtn.setEnabled(false);
		unselectedBtn.setEnabled(true);
		unselectedBtn.setSelected(false);
	}

	// ===================================
	// View
	// ===================================
	
	abstract protected void preInit();
	
	abstract protected void initToggleButtons();
	
	abstract protected void initPanels();
	
	protected void initWindow() {
		/*
		 * make window
		 */
		setMinimumSize(MIN_WINDOW_SIZE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void refreshUI() {
		revalidate();
		repaint();
	}
	
	public void updatePanel(JPanel panel, JPanel add, JPanel remove) {
		/*
		 * add and remove a panel from another panel
		 */
		panel.add(add);
		panel.remove(remove);
	}
} 

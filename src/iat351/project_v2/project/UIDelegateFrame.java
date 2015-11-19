package iat351.project_v2.project;


import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

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
	
	//init
	abstract protected void preInit();
	
	protected void initWindow() {
		/*
		 * make window
		 */
		setMinimumSize(MIN_WINDOW_SIZE);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
		
	abstract protected void initPanels();
	//end init

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

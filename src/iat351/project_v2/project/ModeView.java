package iat351.project_v2.project;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

abstract public class ModeView {
	/*
	 * Stores the view components from the modes so that they can be switched
	 */
	protected static final Color COLOR = new Color(80, 140, 190);
	
	protected UIDelegateFrame uiDelegate;
	protected JPanel topPanel = new JPanel();
	protected JPanel bottomPanel = new JPanel();
	protected JPanel sidePanel = new JPanel();
	
	public ModeView(UIDelegateFrame uiDelegate) {
		this.uiDelegate = uiDelegate;
		topPanel.setBackground(COLOR);
	}
	
	abstract protected JButton createButton(String text);
	
	public JPanel getTopPanel() {
		return topPanel;
	}
	
	public JPanel getBottomPanel() {
		return bottomPanel;
	}
	
	public JPanel getSidePanel() {
		return sidePanel;
	}
} // View

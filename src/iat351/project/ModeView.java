package iat351.project;

import javax.swing.JButton;
import javax.swing.JPanel;

abstract public class ModeView {
	protected UIDelegate uiDelegate;
	protected JPanel topPanel = new JPanel();
	protected JPanel bottomPanel = new JPanel();
	protected JPanel sidePanel = new JPanel();
	
	public ModeView(UIDelegate uiDelegate) {
		this.uiDelegate = uiDelegate;
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

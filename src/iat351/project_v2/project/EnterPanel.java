package iat351.project_v2.project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class EnterPanel extends JPanel{
	private static final Border BORDER = BorderFactory.createEmptyBorder(0, 0, 10, 0);
	private static final int LABEL_WIDTH = 70;
	
	JLabel label;
	JTextField textField;
	private boolean editable;
	Border editBorder;
	Border viewBorder;
	
	public EnterPanel(String name){
		this(name, true);
	}
	
	public EnterPanel(String name, boolean editable){
		this.editable = editable;
		
		//init panel
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		setBorder(BORDER);
		
		editBorder = BorderFactory.createLoweredBevelBorder();
		viewBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		
		//make components
		label = new JLabel(name, SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(LABEL_WIDTH, 0));
		
		textField = new JTextField();
		editableFormat();
		
		//at to panel
		add(label);
		add(Box.createRigidArea(new Dimension(10 ,0)));
		add(textField);
	}
	
	public String getText(){
		//get the text from the text field
		return textField.getText();
	}
	
	public void setText(String text){
		//get the text from the text field
		textField.setText(text);
	}
	
	public void toggleEditable(){
		editable = !editable;
		editableFormat();
	}
	
	public void editableFormat(){
		if(editable){
			textField.setEditable(true);
			textField.setBorder(editBorder);
		}else{
			textField.setEditable(false);
			textField.setBorder(viewBorder);
		}
	}

}

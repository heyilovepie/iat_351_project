package iat351.project_v2.project;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class EnterPanel extends JPanel{
	
	JLabel label;
	JTextField textField;
	private boolean editable;
	Border editBorder;
	Border viewBorder;
	
	public EnterPanel(String name, int text_width){
		this(name, text_width, true);
	}
	
	public EnterPanel(String name, int text_width, boolean editable){
		
		this.editable = editable;
		
		//init panel
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);
		
		editBorder = BorderFactory.createLoweredBevelBorder();
		viewBorder = BorderFactory.createEmptyBorder();
		
		//make components
		label = new JLabel(name);	
		textField = new JTextField(text_width);	
		editableFormat();
		
		//at to panel
		add(label);
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

package iat351.project_v2.project;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnterPanel extends JPanel{
	JLabel label;
	JTextField textField;
	
	public EnterPanel(String name, int text_width){
		
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);
		
		//make components
		label = new JLabel(name);	
		textField = new JTextField(text_width);	
		
		//at to panel
		add(label);
		add(textField);
	}
	
	public String getText(){
		//get the text from the text field
		return textField.getText();
	}

}

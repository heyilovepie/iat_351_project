package iat351.project_v2.project;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomSavePanel extends JPanel{
	/*
	 * This is the panel that is present in both the event and the note view
	 */
	
	private JPanel editPanel, viewPanel;
	public JButton save, reset, deleteView, deleteEdit, edit, ok;
	private boolean isEditing;
	
	public BottomSavePanel(boolean isEditing){
		super();
		this.isEditing = isEditing;
		
		deleteView = new JButton("Delete");
		deleteEdit = new JButton("Delete");
		reset = new JButton("Reset");
		save = new JButton("Save");
		edit = new JButton("Edit");
		ok = new JButton("Ok");
		
		editPanel = new JPanel();
		editPanel.setLayout(new FlowLayout());
		editPanel.add(reset);
		editPanel.add(deleteEdit);
		editPanel.add(save);
		
		viewPanel = new JPanel();
		viewPanel.setLayout(new FlowLayout());
		viewPanel.add(edit);
		viewPanel.add(deleteView);
		viewPanel.add(ok);
		
		if(isEditing){
			add(editPanel);
		}else{
			add(viewPanel);
		}
	}
	
	public void toggleEdit(){
		if(isEditing){
			isEditing = false;
			updatePanel(this, viewPanel, editPanel);
		}else{
			isEditing = true;
			updatePanel(this, editPanel, viewPanel);
		}
	}
	
	public void updatePanel(JPanel panel, JPanel add, JPanel remove) {
		/*
		 * add and remove a panel from another panel
		 */
		panel.add(add);
		panel.remove(remove);
	}
	
	public void addActionListeners(ActionListener saveL, ActionListener resetL, 
			ActionListener deleteL, ActionListener editL, ActionListener okL){
		save.addActionListener(saveL);
		reset.addActionListener(resetL);
		deleteView.addActionListener(deleteL);
		deleteEdit.addActionListener(deleteL);
		edit.addActionListener(editL);
		ok.addActionListener(okL);	
	}
}

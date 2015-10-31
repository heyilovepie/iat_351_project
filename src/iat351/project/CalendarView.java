package iat351.project;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class CalendarView extends ModeView {
	private JButton btnNewEvent;
	private JButton btnToday;
	private JToggleButton btnMonth;
	private JToggleButton btnAgenda;
	
	public CalendarView(UIDelegate uiDelegate, Dimension sidePanelSize) {
		super(uiDelegate);
		
		// TODO refactor constructor
		btnNewEvent = createButton("New Event");
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.setPreferredSize(sidePanelSize);
		sidePanel.add(btnNewEvent);
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "test");
			}
		});		
		
		btnMonth = createToggleButton("Month");
		btnAgenda = createToggleButton("Agenda");
		uiDelegate.updateToggleButtons(btnMonth, btnAgenda);
		btnToday = createButton("Today");
		
		topPanel.add(btnMonth);
		topPanel.add(btnAgenda);
		topPanel.add(btnToday);	
	} // Constructor
	
	private JToggleButton createToggleButton(String text) {
		JToggleButton btn = new JToggleButton(text);
		btn.setActionCommand(text);

		btn.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					String btnName = btn.getActionCommand();
					if (btnName.equals("Month")) {
						uiDelegate.updateToggleButtons(btnMonth, btnAgenda);
					} else if (btnName.equals("Agenda")) {
						uiDelegate.updateToggleButtons(btnAgenda, btnMonth);
					}
				}
			}
		}); // addItemListener

		return btn;
	} // createToggleButton

	@Override
	protected JButton createButton(String text) {
		return new JButton(text);
	}
} // CalendarView

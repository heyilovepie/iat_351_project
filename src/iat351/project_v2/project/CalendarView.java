package iat351.project_v2.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;

public class CalendarView extends ModeView {
	/*
	 * Stores the view components from the calendar mode so that it can be switched
	 */
	private JButton btnNewEvent;
	private JButton btnToday;
	private JToggleButton btnMonth;
	private JToggleButton btnAgenda;
	private UIDelegate uiDelegate;
	
	public CalendarView(UIDelegate uiDelegate, Dimension sidePanelSize) {
		super((UIDelegateFrame) uiDelegate);
		createSidePanel(sidePanelSize);
		setupEventListener(uiDelegate);		
		createTopPanel(uiDelegate);
		createCalendar();
	} // Constructor
	
	private void setupEventListener(UIDelegate uiDelegate) {
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiDelegate.newEvent();
			}
		});
	}

	private void createSidePanel(Dimension sidePanelSize) {
		btnNewEvent = createButton("New Event");
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.setPreferredSize(sidePanelSize);
		sidePanel.add(btnNewEvent);
	}

	private void createTopPanel(UIDelegate uiDelegate) {
		JLabel monthLabel = new JLabel("November 2015");
		
		btnMonth = createToggleButton("Month");
		btnAgenda = createToggleButton("Agenda");
		uiDelegate.updateToggleButtons(btnMonth, btnAgenda);
		btnToday = createButton("Today");
		
		topPanel.add(monthLabel);
		topPanel.add(btnMonth);
		topPanel.add(btnAgenda);
		topPanel.add(btnToday);
	}

	private void createCalendar() {
		// Setup gridbag constraints
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		// Add day JPanels to a grid
		int i = 1;
		boolean isCurrMonth = true;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 7; col++) {
				// Reset the day to 1 if it reaches 31
				if (i == 31) {
					i = 1;
					isCurrMonth = false;
				}
				
				bottomPanel.add(createDayPanel(i, isCurrMonth), c);
				c.gridx++;
				i++;
			}
			c.gridx = 0;
			c.gridy++;
		}
	} // createCalendar
	
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
	
	private JPanel createDayPanel(int day, boolean currMonth) {
		// Create JPanel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		
		// Create day label
		JLabel dayLabel = new JLabel(Integer.toString(day));
		if (!currMonth) {
			dayLabel.setForeground(Color.LIGHT_GRAY);
		}
		panel.add(dayLabel);
		
		return panel;
	}
	
	@Override
	protected JButton createButton(String text) {
		return new JButton(text);
	}
} // CalendarView

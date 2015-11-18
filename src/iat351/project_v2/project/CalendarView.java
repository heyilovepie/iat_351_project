package iat351.project_v2.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class CalendarView extends ModeView {
	/*
	 * Stores the view components from the calendar mode so that it can be
	 * switched
	 */
	private static final int MAX_ROWS = 5;
	private static final int MAX_COLUMNS = 7;
	
	private JButton btnNewEvent;
	private JButton btnToday;
	private JToggleButton btnMonth;
	private JToggleButton btnAgenda;
	private UIDelegate uiDelegate;

	private GridBagConstraints c = new GridBagConstraints();
	private ArrayList<JPanel> days = new ArrayList<JPanel>(); // Used for adding events

	public CalendarView(UIDelegate uiDelegate, Dimension sidePanelSize) {
		super((UIDelegateFrame) uiDelegate);
		this.uiDelegate = uiDelegate;

		createSidePanel(sidePanelSize);
		setupEventListener();
		createTopPanel();
		createCalendar();

		// addEventToCalendar();
	} // Constructor

	private void setupEventListener() {
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiDelegate.newEvent();
			}
		});
	}

	private void createSidePanel(Dimension sidePanelSize) {
		btnNewEvent = createButton("New Event");
		JLabel tagsLabel = new JLabel("Tags");
		// JSeparator separator = new JSeparator();
		JCheckBox placeholderCheckBox = new JCheckBox("IAT 351");

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.setPreferredSize(sidePanelSize);
		sidePanel.add(btnNewEvent);
		sidePanel.add(tagsLabel);
		// sidePanel.add(separator); // separator hides the components under it
		// for some reason
		sidePanel.add(placeholderCheckBox);
	}

	private void createTopPanel() {
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
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;

		// Add day JPanels to calendar grid
		createDayPanels();
		int i = 0;
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLUMNS; col++) {
				bottomPanel.add(days.get(i), c);
				c.gridx++;
				i++;
			}
			c.gridx = 0;
			c.gridy++;
		}
	} // createCalendar
	
	private void createDayPanels() {
		int i = 1;
		boolean isCurrMonth = true; // For font colour
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLUMNS; col++) {
				// Reset the day to 1 if it reaches 31
				if (i == 31) {
					i = 1;
					isCurrMonth = false;
				}
				
				days.add(createDayPanel(i, isCurrMonth));
				i++;
			}
		}
	} //createDayPanels

	private JPanel createDayPanel(int day, boolean isCurrMonth) {
		// Create JPanel with mouse listener
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println(day + " pressed");
			}
		});

		// Create day label
		JLabel dayLabel = new JLabel(Integer.toString(day));
		if (!isCurrMonth) {
			dayLabel.setForeground(Color.LIGHT_GRAY);
		}
		panel.add(dayLabel);

		return panel;
	}
	
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
		});

		return btn;
	} // createToggleButton

	@Override
	protected JButton createButton(String text) {
		return new JButton(text);
	}
} // CalendarView

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
	private static final String[] dayNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	
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
		createTopPanel();
		createCalendar();
		setupEventListener();
	} // Constructor
	
	private void addEvents(ArrayList<Event> events) {
		for (Event event : events) {
			// Get event title and date
			String title = event.getTitle();
			int day = event.getDay(); // TODO will need to change this to e.getDate() for other months/years
			
			// Create event label
			JButton eventLabel = new JButton(title);
			eventLabel.setOpaque(true);
			eventLabel.setBackground(Color.CYAN);
			
			//add action listener
			ActionListener eventAction = new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					//open the event window
					uiDelegate.openEvent(event); 
				}
			};
			
			eventLabel.addActionListener(eventAction);
			
			// Add event label to calendar
			days.get(day - 1).add(eventLabel);
		}
	}
	
	public void setEvents(ArrayList<Event> events) {
		//reset
		bottomPanel.removeAll(); //clear the bottomPanel
		days.clear(); //clear the list of JPanel days
		//create new
		createCalendar(); //create a new calendar with days added
		//add events
		addEvents(events);
	}

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
//		JLabel tagsLabel = new JLabel("Tags");
//		// JSeparator separator = new JSeparator();
//		JCheckBox placeholderCheckBox = new JCheckBox("IAT 351");

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
		sidePanel.setPreferredSize(sidePanelSize);
		sidePanel.add(btnNewEvent);
//		sidePanel.add(tagsLabel);
		// sidePanel.add(separator); // separator hides the components under it for some reason
//		sidePanel.add(placeholderCheckBox);
	}

	private void createTopPanel() {
		JLabel monthLabel = new JLabel("November 2015");
		monthLabel.setFont(monthLabel.getFont().deriveFont(20.0f));
		monthLabel.setForeground(Color.WHITE);

		btnMonth = createToggleButton("Month");
		btnAgenda = createToggleButton("Agenda");
		uiDelegate.updateToggleButtons(btnMonth, btnAgenda);
		btnToday = createButton("Today");

		topPanel.setBackground(Color.GRAY);
		topPanel.add(monthLabel);
		// TODO implement after milestone 3
//		topPanel.add(btnMonth);
//		topPanel.add(btnAgenda);
//		topPanel.add(btnToday);
	}

	private void createCalendar() {		
		// Setup panel
		bottomPanel.setLayout(new GridBagLayout());
		bottomPanel.setBackground(Color.GRAY);
		bottomPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		// Setup gridbag constraints
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		// Add day names above calendar
		int i = 0;
		for (int col = 0; col < MAX_COLUMNS; col++) {
			JLabel label = new JLabel(dayNames[i]);
			label.setOpaque(true);
			label.setBackground(new Color(200, 230, 200));
			bottomPanel.add(label, c);
			c.gridx++;
			i++;
		}

		// Add day JPanels to calendar grid
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		createDayPanels();
		i = 0;
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
				uiDelegate.newEvent(2015, 11, day);
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

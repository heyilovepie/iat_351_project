package iat351.project_v2.project;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

public class EventView extends UIDelegateFrame {
	private static final Border BORDER = BorderFactory.createEmptyBorder(30, 20, 0, 20);
	
	/*
	 * The Event JFrame
	 */

	// Panels
	public EnterPanel title, location, date, startTime, endTime, tags;
	public EnterPanel notes = new EnterPanel("Notes"); // TODO temp var add buttons

	private JPanel addNotePanel;
	private BottomSavePanel bottomSavePanel;

	public JButton save, reset, addNote, edit, ok;

	public boolean isEditing;

	public EventView(boolean isEditing) {
		super();
		this.isEditing = isEditing; // set editing variable
		super.constructor(); // call super constructor
	} // Constructor

	// ===================================
	// View & Controller
	// ===================================

	protected void constructor() {
		/* this is intentionally empty to override the parent class */
	}

	protected void preInit() {
		// change window size to different that the default
		WIDTH = 500;
		HEIGHT = 350;
		MIN_WINDOW_SIZE = new Dimension((int) WIDTH, (int) HEIGHT);
	}

	protected void initWindow() {
		// make the window
		super.initWindow();
		setTitle("Note");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		((JComponent) getContentPane()).setBorder(BORDER);
	}

	@Override
	protected void initToggleButtons() {
	}

	// init
	protected void initPanels() {
		title = new EnterPanel("Event:", isEditing);
		date = new EnterPanel("Date:", isEditing);

		// Place the start time and end time on the same line
		JPanel timePanel = new JPanel();
		timePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		startTime = new EnterPanel("Start Time:", isEditing);
		endTime = new EnterPanel("End Time:", isEditing);
		timePanel.add(startTime, c);
		c.gridx = 1;
		timePanel.add(endTime, c);

		location = new EnterPanel("Location:", isEditing);
		tags = new EnterPanel("Tags:", isEditing);

		add(title);
		add(date);
		add(timePanel);
		add(location);
		add(tags);

		addNotePanel = new JPanel();
		addNotePanel.setLayout(new FlowLayout());
		addNotePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 6));
		addNote = new JButton("Add Note");
		addNotePanel.add(addNote);
		add(addNotePanel);
		
		add(new JSeparator());

		bottomSavePanel = new BottomSavePanel(isEditing);
		// bottomSavePanel.add(new JButton("mew"));
		bottomSavePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 5));
		add(bottomSavePanel);
	}
	// end if init

	// get and set

	public void toggleEdit() {
		isEditing = !isEditing;
		bottomSavePanel.toggleEdit();
		toggleEnterPanels();
		refreshUI();
	}

	protected void toggleEnterPanels() {
		title.toggleEditable();
		location.toggleEditable();
		date.toggleEditable();
		startTime.toggleEditable();
		endTime.toggleEditable();
		tags.toggleEditable();
	}

	public void addActionListeners(ActionListener saveL, ActionListener resetL, ActionListener deleteL,
			ActionListener editL, ActionListener okL) {

		bottomSavePanel.addActionListeners(saveL, resetL, deleteL, editL, okL);

	}
} // UIDelegate

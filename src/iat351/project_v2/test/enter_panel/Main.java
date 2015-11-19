package iat351.project_v2.test.enter_panel;


import java.awt.FlowLayout;

import iat351.project_v2.project.InputField;
import iat351.project_v2.project.Note;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	/*
	 * This is a test for an EnterPanel.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setSize(500, 200);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setLayout(new FlowLayout());
				frame.add(new InputField("Event: ", 20));
				frame.add(new InputField("Time: ", 20));
				frame.add(new InputField("Location: ", 20));
			}
		});
	} // main
}
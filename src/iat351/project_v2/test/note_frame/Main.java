package iat351.project_v2.test.note_frame;


import iat351.project_v2.project.NoteView;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new NoteView();
			}
		});
	} // main
}
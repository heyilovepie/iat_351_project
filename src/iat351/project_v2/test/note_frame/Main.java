package iat351.project_v2.test.note_frame;


import iat351.project_v2.project.Note;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Note note = new Note();
				note.getNoteFrame();
			}
		});
	} // main
}
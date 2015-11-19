package iat351.project_v2.test.event_frame;


import iat351.project_v2.project.Event;
import iat351.project_v2.project.Model;
import iat351.project_v2.project.UIDelegate;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Model model = new Model();
				Event event = new Event(model);
				event.getEventFrame(new UIDelegate());
			}
		});
	} // main
}
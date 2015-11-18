package iat351.project_v2.test.time;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Iterator();
			}
		});
	} // main
}

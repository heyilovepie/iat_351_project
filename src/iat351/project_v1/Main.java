package iat351.project_v1;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UIDelegate();
			}
		});
	} // main
}

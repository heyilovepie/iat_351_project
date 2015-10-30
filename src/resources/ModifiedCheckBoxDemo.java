package resources;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class ModifiedCheckBoxDemo extends JFrame {
	JCheckBox checkBox;

	public ModifiedCheckBoxDemo() {
		initComponents();
		pack();
	}

	private void initComponents() {
		Icon normal = new MyCheckBoxIcon(Color.black);
		Icon selected = new MyCheckBoxIcon(Color.green);
		checkBox = new JCheckBox("Test", normal);
		checkBox.setSelectedIcon(selected);
		this.getContentPane().add(checkBox);

	}

	public static void main(String[] args) {
		new ModifiedCheckBoxDemo().setVisible(true);
	}
}
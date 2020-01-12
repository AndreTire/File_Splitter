package FP_GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container conn = frame.getContentPane();
		// conn.add(null);
		
		// frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

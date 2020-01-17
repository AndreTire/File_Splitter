package FP_GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
	private static Menu m = new Menu();
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container conn = frame.getContentPane();
		conn.add(new Objects());
		
		// frame.pack();
		frame.setSize(800, 600);
		frame.setJMenuBar(m.getMenu());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}

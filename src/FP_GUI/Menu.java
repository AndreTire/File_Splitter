package FP_GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Classe per il menu del frame
 * @author Tirelli Andrea
 *
 */
public class Menu extends Component{
	//Where the GUI is created:
	private JMenuBar menuBar;
	private JMenu menu, submenu;
	private JMenuItem menuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private JCheckBoxMenuItem cbMenuItem;

	public Menu() {
		super();
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Menu gestione file
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		// menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		// Selezione file
		menuItem = new JMenuItem("Select File...", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		// menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);
		
		// Rimuovi file
		menuItem = new JMenuItem("Remove File...", KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		// menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);
		
		// Proprietà del sistema
		menu.addSeparator();
		menuItem = new JMenuItem("Proprietà", KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.ALT_MASK));
		menu.add(menuItem);
		
		// Restart del software
		menu.addSeparator();
		menuItem = new JMenuItem("Restart", KeyEvent.VK_0);
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F5"));
		menu.add(menuItem);
		

		/* menuItem = new JMenuItem("Both text and icon", new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem); */
		
		
		/*
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu); */

		// Menu Modifica
		menu = new JMenu("Modifica");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		menuBar.add(menu);
		
		// Menu Modifica
		menu = new JMenu("Run");
		menu.setMnemonic(KeyEvent.VK_O);
		menu.getAccessibleContext().setAccessibleDescription("Run/Stop program");
		menuBar.add(menu);

	}
	
	public JMenuBar getMenu() { return menuBar; }
}

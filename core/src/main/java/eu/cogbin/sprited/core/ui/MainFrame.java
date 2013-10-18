package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eu.cogbin.sprited.core.AppContext;

/**
 * 
 * @author Danny
 * 
 */
public class MainFrame extends JFrame {

	public MainFrame() {
		super("Sprited");

		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new MainPanel(), BorderLayout.CENTER);

		add(createMenuBar(), BorderLayout.NORTH);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menu = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);

		// TODO add keylisteners for shortcuts like ctrl+s

		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppContext.getInstance().getFileManager().newProject();
			}
		});
		fileMenu.add(newItem);

		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppContext.getInstance().getFileManager().openProject();
			}
		});
		fileMenu.add(openItem);

		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppContext.getInstance().getFileManager().saveProject();
			}
		});
		fileMenu.add(saveItem);

		return menu;
	}
}

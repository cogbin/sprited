package eu.cogbin.sprited.core;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import eu.cogbin.sprited.core.model.Bitmap;
import eu.cogbin.sprited.core.model.Frame;
import eu.cogbin.sprited.core.model.Layer;
import eu.cogbin.sprited.core.model.Project;
import eu.cogbin.sprited.core.model.Sprite;
import eu.cogbin.sprited.core.ui.MainPanel;

/**
 * Obviously the main entry point for the application.
 * 
 * @author Danny
 * 
 */
public class Main {

	public static void main(String[] args) {

		// OK this is nasty, but it's just to test, I promise
		final Project testProject = new Project();
		testProject.setSprite(new Sprite());
		Frame testFrame1 = new Frame();
		Frame testFrame2 = new Frame();
		Frame testFrame3 = new Frame();
		testProject.getSprite().getFrames().add(testFrame1);
		testProject.getSprite().getFrames().add(testFrame2);
		testProject.getSprite().getFrames().add(testFrame3);
		testFrame1.setLayer(new Layer());
		testFrame2.setLayer(new Layer());
		testFrame3.setLayer(new Layer());
		testFrame1.getLayer().setBitmap(new Bitmap(32, 32));
		testFrame2.getLayer().setBitmap(new Bitmap(32, 32));
		testFrame3.getLayer().setBitmap(new Bitmap(32, 32));

		// Fire up the UI on the EDT
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("pixanim");
				frame.setSize(800, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new MainPanel(testProject), BorderLayout.CENTER);
				frame.setVisible(true);
			}
		});
	}
}

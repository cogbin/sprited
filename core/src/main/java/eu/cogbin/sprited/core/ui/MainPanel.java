package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.AppContext;
import eu.cogbin.sprited.core.AppContext.ProjectListener;
import eu.cogbin.sprited.core.model.Project;

/**
 * 
 * @author Danny
 * 
 */
public class MainPanel extends JPanel implements ProjectListener {

	private final SpritePanel spritePanel;

	public MainPanel() {

		setLayout(new BorderLayout());
		setOpaque(true);
		setBackground(Color.DARK_GRAY);

		spritePanel = new SpritePanel();
		add(spritePanel, BorderLayout.CENTER);

		// TODO there should be a better way to do this, maybe Swing keybinding
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(new KeyEventDispatcher() {
					public boolean dispatchKeyEvent(KeyEvent ev) {
						// FIXME should act like press instead of keydown
						if (KeyEvent.VK_Z == ev.getKeyCode()) {
							if (ev.isControlDown()) {
								if (!ev.isShiftDown()) {
									// ctrl + z
									AppContext.getInstance()
											.getActionProcessor().undo();
								} else {
									// ctrl + shift + z
									AppContext.getInstance()
											.getActionProcessor().redo();
								}
							}
						}
						if (KeyEvent.VK_Y == ev.getKeyCode()) {
							if (ev.isControlDown()) {
								// ctrl + y
								AppContext.getInstance().getActionProcessor()
										.redo();
							}
						}
						return false;
					}
				});

		AppContext.getInstance().addProjectListener(this);

	}

	public void onProjectChanged(Project project) {
		spritePanel.setSprite(project.getSprite());
	}
}

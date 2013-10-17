package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.model.Frame;
import eu.cogbin.sprited.core.model.Sprite;
import eu.cogbin.sprited.core.ui.SpriteFrameSelectorPanel.FrameSelectionListener;

/**
 * 
 * @author Danny
 * 
 */
public class SpritePanel extends JPanel implements FrameSelectionListener {

	private final FramePanel framePanel;
	private final SpriteFrameSelectorPanel frameSelector;

	public SpritePanel() {

		setLayout(new BorderLayout());

		framePanel = new FramePanel();
		add(framePanel, BorderLayout.CENTER);

		frameSelector = new SpriteFrameSelectorPanel();
		frameSelector.addFrameSelectionListener(this);
		add(frameSelector, BorderLayout.NORTH);

	}

	public void setSprite(Sprite sprite) {
		frameSelector.setSprite(sprite);
	}

	public void onFrameSelected(Frame frame) {
		framePanel.setFrame(frame);
	}

}

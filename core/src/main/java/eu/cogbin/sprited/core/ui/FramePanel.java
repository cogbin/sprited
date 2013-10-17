package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.model.Frame;

/**
 * 
 * @author Danny
 * 
 */
public class FramePanel extends JPanel {

	private final LayerPanel layerPanel;

	public FramePanel() {

		setLayout(new BorderLayout());

		layerPanel = new LayerPanel();
		add(layerPanel, BorderLayout.CENTER);

	}

	public void setFrame(Frame frame) {
		// TODO use layer selector and select first (or last??) layer by default
		layerPanel.setLayer(frame.getLayer());
	}

}

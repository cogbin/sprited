package eu.cogbin.sprited.core.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import eu.cogbin.sprited.core.model.Frame;
import eu.cogbin.sprited.core.model.Sprite;

/**
 * 
 * @author Danny
 * 
 */
public class SpriteFrameSelectorPanel extends JPanel {

	private final JButton copyLastFrameButton;

	public SpriteFrameSelectorPanel() {

		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

		copyLastFrameButton = new JButton("+");
		copyLastFrameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				copyLastFrame();
			}
		});

	}

	private Sprite sprite;

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;

		removeAll();
		for (final Frame frame : sprite.getFrames()) {
			BitmapPreviewPanel previewPanel = new BitmapPreviewPanel();
			// TODO preview panel should be a layer preview panel
			previewPanel.setBitmap(frame.getLayer().getBitmap());
			add(previewPanel);

			previewPanel.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent ev) {
					selectFrame(frame);
				}
			});
		}

		// Select first frame by default
		if (sprite.getFrames().size() > 0) {
			selectFrame(sprite.getFrames().get(0));
		}

		add(copyLastFrameButton);
	}

	private void selectFrame(Frame frame) {
		// TODO show which frame is selected with something like a border around
		// it

		fireFrameSelected(frame);
	}

	private void copyLastFrame() {
		// TODO refactor to use an Action and listeners on the sprite model
		if (sprite != null) {
			if (sprite.getFrames().size() > 0) {
				Frame lastFrame = sprite.getFrames().get(
						sprite.getFrames().size() - 1);
				Frame newFrame = lastFrame.clone();
				sprite.addFrame(newFrame);
				// TODO this is nasty, improve it asap
				setSprite(sprite);
				selectFrame(newFrame);
				validate();
			}
		}

	}

	public interface FrameSelectionListener {

		void onFrameSelected(Frame frame);

	}

	private List<FrameSelectionListener> frameSelectionListeners = new CopyOnWriteArrayList<FrameSelectionListener>();

	public void addFrameSelectionListener(FrameSelectionListener l) {
		frameSelectionListeners.add(l);
	}

	public void removeFrameSelectionListener(FrameSelectionListener l) {
		frameSelectionListeners.remove(l);
	}

	private void fireFrameSelected(Frame frame) {
		for (FrameSelectionListener l : frameSelectionListeners) {
			// TODO catch exceptions per listener
			l.onFrameSelected(frame);
		}
	}

}

package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.model.Layer;

/**
 * 
 * @author Danny
 * 
 */
public class LayerPanel extends JPanel {

	private final BitmapPreviewPanel bitmapPreview;
	private final BitmapPixelEditorPanel bitmapEditor;

	public LayerPanel() {

		setLayout(new BorderLayout());

		bitmapPreview = new BitmapPreviewPanel();
		FlowLayout eastLayout = new FlowLayout();
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(eastLayout);
		eastPanel.add(bitmapPreview);
		add(eastPanel, BorderLayout.EAST);

		bitmapEditor = new BitmapPixelEditorPanel();
		add(bitmapEditor, BorderLayout.CENTER);

	}

	public void setLayer(Layer layer) {
		bitmapPreview.setBitmap(layer.getBitmap());
		bitmapEditor.setBitmap(layer.getBitmap());
		// TODO show layer name somewhere?
	}
}

package eu.cogbin.sprited.core.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eu.cogbin.sprited.core.model.Layer;

/**
 * 
 * @author Danny
 * 
 */
public class LayerPanel extends JPanel {

	private final BitmapPreviewPanel bitmapPreview;
	private final BitmapPixelEditorPanel bitmapEditor;
	private final ZoomBar zoomBar;

	public LayerPanel() {

		setLayout(new BorderLayout());

		bitmapPreview = new BitmapPreviewPanel();
		FlowLayout eastLayout = new FlowLayout();
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(eastLayout);
		eastPanel.add(bitmapPreview);
		add(eastPanel, BorderLayout.EAST);

		bitmapEditor = new BitmapPixelEditorPanel();
		JPanel bitmapEditorPanel = new JPanel();
		bitmapEditorPanel.add(bitmapEditor);
		add(new JScrollPane(bitmapEditorPanel), BorderLayout.CENTER);

		zoomBar = new ZoomBar();
		add(zoomBar, BorderLayout.SOUTH);

	}

	public void setLayer(Layer layer) {
		bitmapPreview.setBitmap(layer.getBitmap());
		bitmapEditor.setBitmap(layer.getBitmap());
		// TODO show layer name somewhere?
	}
}

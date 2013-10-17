package eu.cogbin.sprited.core.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.model.AbstractModel.ModelListener;
import eu.cogbin.sprited.core.model.Bitmap;

/**
 * Shows a given bitmap at it's real size.
 * 
 * @author Danny
 * 
 */
public class BitmapPreviewPanel extends JPanel {

	public BitmapPreviewPanel() {
		setOpaque(true);
	}

	private Bitmap bitmap;

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		bitmap.addListener(new ModelListener() {
			public void onChange() {
				repaint();
			}
		});
		repaint();
		// TODO if a bitmap was already set remove listener

	}

	@Override
	public Dimension getPreferredSize() {
		if (bitmap != null) {
			return new Dimension(bitmap.getCols(), bitmap.getRows());
		} else {
			return new Dimension(64, 64);// TODO reconsider
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponents(g); ???

		// TODO this way of painting could probably not be any more inefficient
		// find a decent way to do this

		Graphics2D g2d = (Graphics2D) g.create();

		if (bitmap != null) {

			double colWidth = ((double) getWidth() / (double) bitmap.getCols());
			double rowHeight = ((double) getHeight() / (double) bitmap
					.getRows());

			for (int row = 0; row < bitmap.getRows(); row++) {
				for (int col = 0; col < bitmap.getCols(); col++) {
					Integer color = bitmap.getColor(col, row);
					if (color != null) {
						g2d.setColor(new Color(color));
						g2d.fillRect((int) (colWidth * col),
								(int) (rowHeight * row), ((int) colWidth) + 1,
								((int) rowHeight) + 1);
					} else {
						g2d.clearRect((int) (colWidth * col),
								(int) (rowHeight * row), ((int) colWidth) + 1,
								((int) rowHeight) + 1);
					}
				}
			}

		}

		g2d.dispose();
	}
}

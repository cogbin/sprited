package eu.cogbin.sprited.core.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.AppContext;
import eu.cogbin.sprited.core.action.SetBitmapPixelAction;
import eu.cogbin.sprited.core.model.AbstractModel.ModelListener;
import eu.cogbin.sprited.core.model.Bitmap;
import eu.cogbin.sprited.core.session.EditSession.EditSessionListener;

/**
 * 
 * @author Danny
 * 
 */
public class BitmapPixelEditorPanel extends JPanel {

	private int zoomLevel = 0;

	public BitmapPixelEditorPanel() {
		setOpaque(true);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				handleClick(ev.getPoint(), ev.getButton());
			}
		});

		AppContext.getInstance().getEditSession()
				.addEditSessionListener(new EditSessionListener() {
					public void onZoomLevelChanged(int zoomLevel) {
						BitmapPixelEditorPanel.this.zoomLevel = zoomLevel;
						revalidate();
						repaint();
					}
				});
	}

	private Bitmap bitmap;

	public void setBitmap(Bitmap bitmap) {
		if (this.bitmap != null) {
			// Remove listener on previous bitmap
			this.bitmap.removeListener(bitmapChangeListener);
		}

		this.bitmap = bitmap;

		// Start listenening for changes on the new bitmap
		bitmap.addListener(bitmapChangeListener);

		revalidate();
		repaint();
	}

	private ModelListener bitmapChangeListener = new ModelListener() {
		public void onChange() {
			repaint();
		}
	};

	private double getVisiblePixelWidth() {
		return (double) (zoomLevel + 1);
	}

	private double getVisiblePixelHeight() {
		return (double) (zoomLevel + 1);
	}

	@Override
	public Dimension getPreferredSize() {
		if (bitmap != null) {
			return new Dimension((zoomLevel + 1) * bitmap.getCols(),
					(zoomLevel + 1) * bitmap.getRows());
		} else {
			return new Dimension(1, 1);
		}
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponents(g); ???

		Graphics2D g2d = (Graphics2D) g.create();

		if (bitmap != null) {

			double colWidth = getVisiblePixelWidth();
			double rowHeight = getVisiblePixelHeight();

			BufferedImage image = bitmap.getImage();
			g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);

			// Draw grid, TODO add grid toggle
			{
				g2d.setColor(Color.DARK_GRAY);

				for (int col = 0; col <= bitmap.getCols(); col++) {

					g2d.drawLine((int) (colWidth * col), 0,
							(int) (colWidth * col), getHeight());

				}

				for (int row = 0; row <= bitmap.getRows(); row++) {

					g2d.drawLine(0, (int) (rowHeight * row), getWidth(),
							(int) (rowHeight * row));

				}
			}

		}

		g2d.dispose();
	}

	private void handleClick(Point p, int mouseButton) {

		// TODO allow dragging to paint more fluidly?

		double colWidth = getVisiblePixelWidth();
		double rowHeight = getVisiblePixelHeight();

		int col = (int) (p.getX() / colWidth);
		int row = (int) (p.getY() / rowHeight);

		Color color = null;

		switch (mouseButton) {
		case MouseEvent.BUTTON1:
			// TODO use selected color, using an editable color palette
			color = Color.BLUE;
			break;
		case MouseEvent.BUTTON2:
			color = Color.RED;
			break;
		case MouseEvent.BUTTON3:
			color = Color.WHITE;
			break;

		}

		AppContext
				.getInstance()
				.getActionProcessor()
				.performAction(
						new SetBitmapPixelAction(bitmap, col, row, color));

	}

}

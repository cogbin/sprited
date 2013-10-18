package eu.cogbin.sprited.core.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import eu.cogbin.sprited.core.AppContext;
import eu.cogbin.sprited.core.action.SetBitmapPixelAction;
import eu.cogbin.sprited.core.model.AbstractModel.ModelListener;
import eu.cogbin.sprited.core.model.Bitmap;

/**
 * 
 * @author Danny
 * 
 */
public class BitmapPixelEditorPanel extends JPanel {

	public BitmapPixelEditorPanel() {
		setOpaque(true);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				handleClick(ev.getPoint(), ev.getButton());
			}
		});
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
		// TODO if bitmap already set remove listener

	}

	@Override
	public void paintComponent(Graphics g) {
		// super.paintComponents(g); ???

		Graphics2D g2d = (Graphics2D) g.create();

		if (bitmap != null) {

			double colWidth = ((double) getWidth() / (double) bitmap.getCols());
			double rowHeight = ((double) getHeight() / (double) bitmap
					.getRows());

			// TODO just use bitmap.getImage(); and scale it

			for (int row = 0; row < bitmap.getRows(); row++) {
				for (int col = 0; col < bitmap.getCols(); col++) {
					Color color = bitmap.getColor(col, row);
					if (color != null) {
						g2d.setColor(color);
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

		double colWidth = ((double) getWidth() / (double) bitmap.getCols());
		double rowHeight = ((double) getHeight() / (double) bitmap.getRows());

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

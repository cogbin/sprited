package eu.cogbin.sprited.core.model;

import java.awt.Color;

/**
 * 
 * @author Danny
 *
 */
public class Bitmap extends AbstractModel implements Cloneable {

	private int cols;
	private int rows;
	private Color[][] bitmap;

	public Bitmap(int cols, int rows) {
		this.cols = cols;
		this.rows = rows;
		bitmap = new Color[cols][rows];
	}

	public Color getColor(int col, int row) {
		// TODO check out of bounds
		return bitmap[col][row];
	}

	/**
	 * 
	 * @param col
	 * @param row
	 * @param color
	 *            null indicates empty / fully transparent
	 */
	public void setColor(int col, int row, Color color) {
		// TODO check out of bounds
		bitmap[col][row] = color;

		fireChange();
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	@Override
	public Bitmap clone() {
		Bitmap clone = new Bitmap(cols, rows);
		// TODO use arraycopy?
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				clone.setColor(col, row, getColor(col, row));
			}
		}
		return clone;
	}
}

package eu.cogbin.sprited.core.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 
 * @author Danny
 * 
 */
public class Bitmap extends AbstractModel implements Cloneable {

	private BufferedImage image;

	public Bitmap() {
		this(1, 1);
	}

	public Bitmap(int cols, int rows) {
		image = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_ARGB);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				image.setRGB(col, row, Color.WHITE.getRGB());
			}
		}
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	@JsonIgnore
	public Color getColor(int col, int row) {
		return new Color(image.getRGB(col, row));
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
		// TODO handle null color
		image.setRGB(col, row, color.getRGB());

		fireChange();
	}

	@JsonIgnore
	public int getCols() {
		return image.getWidth();
	}

	@JsonIgnore
	public int getRows() {
		return image.getHeight();
	}

	@Override
	public Bitmap clone() {
		Bitmap clone = new Bitmap(image.getWidth(), image.getHeight());
		// TODO just clone the bufferedimage
		for (int row = 0; row < image.getHeight(); row++) {
			for (int col = 0; col < image.getWidth(); col++) {
				clone.setColor(col, row, getColor(col, row));
			}
		}
		return clone;
	}
}

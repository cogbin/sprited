package eu.cogbin.sprited.core.action;

import eu.cogbin.sprited.core.model.Bitmap;

/**
 * 
 * @author Danny
 * 
 */
public class SetBitmapPixelAction implements Action {

	private final Bitmap bitmap;
	private final int col;
	private final int row;
	private final Integer color;

	public SetBitmapPixelAction(Bitmap bitmap, int col, int row, Integer color) {
		this.bitmap = bitmap;
		this.col = col;
		this.row = row;
		this.color = color;
	}

	private Integer previousColor;

	public void perform() {
		previousColor = bitmap.getColor(col, row);
		bitmap.setColor(col, row, color);
	}

	public void undo() {
		bitmap.setColor(col, row, previousColor);
	}

}

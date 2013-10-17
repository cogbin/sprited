package eu.cogbin.sprited.core.model;

/**
 * 
 * @author Danny
 *
 */
public class Layer implements Cloneable {

	private Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	@Override
	public Layer clone() {
		Layer clone = new Layer();
		clone.setBitmap(bitmap.clone());
		return clone;
	}

}

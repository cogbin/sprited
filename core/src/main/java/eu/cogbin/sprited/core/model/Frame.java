package eu.cogbin.sprited.core.model;

/**
 * 
 * @author Danny
 *
 */
public class Frame implements Cloneable {

	private Layer layer;

	public Layer getLayer() {
		return layer;
	}

	public void setLayer(Layer layer) {
		this.layer = layer;
	}

	@Override
	public Frame clone() {
		Frame clone = new Frame();
		clone.setLayer(layer.clone());
		return clone;
	}

}

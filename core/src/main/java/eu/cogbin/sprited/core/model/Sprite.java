package eu.cogbin.sprited.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Danny
 *
 */
public class Sprite {

	private List<Frame> frames = new ArrayList<Frame>();

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public void addFrame(Frame frame) {
		frames.add(frame);
	}

}

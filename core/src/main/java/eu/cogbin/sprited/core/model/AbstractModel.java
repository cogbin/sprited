package eu.cogbin.sprited.core.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author Danny
 *
 */
public abstract class AbstractModel {

	public interface ModelListener {
		// TODO this is probably way too generic, but whatever, we'll get to
		// that
		void onChange();
	}

	private List<ModelListener> listeners = new CopyOnWriteArrayList<ModelListener>();

	public void addListener(ModelListener l) {
		listeners.add(l);
	}

	public void removeListener(ModelListener l) {
		listeners.remove(l);
	}

	protected void fireChange() {
		for (ModelListener l : listeners) {
			try {
				l.onChange();
			} catch (Exception ex) {
				// Naughty listener, throwing an exception like that without
				// considering all the other little listeners
				ex.printStackTrace();
				// Why not implement a listener.punish for this kind of crap :P
			}
		}
	}
}

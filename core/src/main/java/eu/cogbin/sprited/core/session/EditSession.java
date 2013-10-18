package eu.cogbin.sprited.core.session;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author Danny
 * 
 */
public class EditSession {

	private int zoomLevel = 2;

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
		fireZoomLevelChanged(zoomLevel);
	}

	public interface EditSessionListener {

		void onZoomLevelChanged(int zoomLevel);

	}

	private List<EditSessionListener> listeners = new CopyOnWriteArrayList<EditSessionListener>();

	public void addEditSessionListener(EditSessionListener l) {
		listeners.add(l);
	}

	public void removeEditSessionListener(EditSessionListener l) {
		listeners.remove(l);
	}

	private void fireZoomLevelChanged(int zoomLevel) {
		for (EditSessionListener l : listeners) {
			// TODO handle exceptions per listener
			l.onZoomLevelChanged(zoomLevel);
		}
	}

}

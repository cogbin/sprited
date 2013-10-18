package eu.cogbin.sprited.core.ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Danny
 * 
 */
public class ZoomBar extends JPanel {

	public static final String PROP_ZOOM_LEVEL = "zoomLevel";

	private int zoomLevel = 0;

	private final JSlider slider = new JSlider();

	public ZoomBar() {
		slider.setValue(zoomLevel);
		// TODO make zoom boundaries globally configurable
		slider.setMaximum(30);
		slider.setMinimum(0);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				int oldValue = zoomLevel;
				int newValue = slider.getValue();
				zoomLevel = newValue;

				firePropertyChange(PROP_ZOOM_LEVEL, oldValue, newValue);
			}
		});

		// TODO use a more global zoom controller and listen for changes in it
		// to adjust the slider accordingly

		// TODO add indicator with zoom percentage

		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(slider);
	}

}

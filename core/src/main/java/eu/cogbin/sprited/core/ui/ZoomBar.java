package eu.cogbin.sprited.core.ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.cogbin.sprited.core.AppContext;

/**
 * 
 * @author Danny
 * 
 */
public class ZoomBar extends JPanel {

	private final JSlider slider = new JSlider();

	public ZoomBar() {
		slider.setValue(AppContext.getInstance().getEditSession()
				.getZoomLevel());
		// TODO make zoom boundaries globally configurable
		slider.setMaximum(30);
		slider.setMinimum(0);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				AppContext.getInstance().getEditSession()
						.setZoomLevel(slider.getValue());
			}
		});

		// TODO add indicator with zoom percentage

		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(slider);
	}

}

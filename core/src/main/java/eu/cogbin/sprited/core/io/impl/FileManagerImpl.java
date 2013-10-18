package eu.cogbin.sprited.core.io.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import eu.cogbin.sprited.core.AppContext;
import eu.cogbin.sprited.core.io.FileManager;
import eu.cogbin.sprited.core.model.Bitmap;
import eu.cogbin.sprited.core.model.Frame;
import eu.cogbin.sprited.core.model.Layer;
import eu.cogbin.sprited.core.model.Project;
import eu.cogbin.sprited.core.model.Sprite;

/**
 * 
 * @author Danny
 * 
 */
public class FileManagerImpl implements FileManager {

	public void saveProject() {
		// TODO off EDT

		// TODO specify extension
		JFileChooser saveFile = new JFileChooser();
		int result = saveFile.showSaveDialog(null);

		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			// Nothing to do
			break;
		case JFileChooser.APPROVE_OPTION:
			doSave(saveFile.getSelectedFile());
			break;
		case JFileChooser.ERROR_OPTION:
			// TODO find a better way to handle this?
			JOptionPane.showMessageDialog(null, "Save dialog error");
			break;
		}
	}

	public void openProject() {
		// TODO off EDT
		// TODO ask for confirmation if there are any unsaved changes

		// TODO specify extension and filter
		JFileChooser openFile = new JFileChooser();
		int result = openFile.showOpenDialog(null);

		switch (result) {
		case JFileChooser.CANCEL_OPTION:
			// Nothing to do
			break;
		case JFileChooser.APPROVE_OPTION:
			doOpen(openFile.getSelectedFile());
			break;
		case JFileChooser.ERROR_OPTION:
			// TODO find a better way to handle this?
			JOptionPane.showMessageDialog(null, "Open dialog error");
			break;
		}
	}

	public void newProject() {
		// TODO off EDT
		// TODO this doesn't really seem to belong here now does it?
		// TODO ask for confirmation if there are any unsaved changes

		Project project = new Project();
		Sprite sprite = new Sprite();
		project.setSprite(sprite);
		Frame frame = new Frame();
		sprite.getFrames().add(frame);
		Layer layer = new Layer();
		frame.setLayer(layer);
		layer.setBitmap(new Bitmap(32, 32));

		AppContext.getInstance().setCurrentProject(project);
	}

	private final ObjectMapper objectMapper;

	public FileManagerImpl() {
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("Sprited", new Version(0, 1, 0,
				null));
		module.addSerializer(new BufferedImageSerializer());
		module.addDeserializer(BufferedImage.class,
				new BufferedImageDeserializer());
		objectMapper.registerModule(module);
	}

	private void doSave(File toFile) {
		// TODO off EDT
		// TODO show progress and block saving again until this save is done or
		// failed

		Project project = AppContext.getInstance().getCurrentProject();
		if (project != null) {
			try {
				objectMapper.writeValue(toFile, project);

				// TODO handle exceptions properly
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void doOpen(File fromFile) {
		// TODO show progress and block opening again until this save is done
		// or failed

		try {
			Project project = objectMapper.readValue(fromFile, Project.class);
			AppContext.getInstance().setCurrentProject(project);

			// TODO handle exceptions properly
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

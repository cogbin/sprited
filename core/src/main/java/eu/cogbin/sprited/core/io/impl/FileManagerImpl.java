package eu.cogbin.sprited.core.io.impl;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.cogbin.sprited.core.AppContext;
import eu.cogbin.sprited.core.io.FileManager;
import eu.cogbin.sprited.core.model.Project;

/**
 * 
 * @author Danny
 * 
 */
public class FileManagerImpl implements FileManager {

	public void save() {
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

	public void open() {
		// TODO off EDT
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

	private final ObjectMapper objectMapper;

	public FileManagerImpl() {
		objectMapper = new ObjectMapper();
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

package eu.cogbin.sprited.core;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import eu.cogbin.sprited.core.action.ActionProcessor;
import eu.cogbin.sprited.core.action.impl.ActionProcessorImpl;
import eu.cogbin.sprited.core.io.FileManager;
import eu.cogbin.sprited.core.io.impl.FileManagerImpl;
import eu.cogbin.sprited.core.model.Project;
import eu.cogbin.sprited.core.session.EditSession;

/**
 * This is a nasty old singleton (just for now, hopefully)
 * 
 * @author Danny
 * 
 */
public class AppContext {

	private static final AppContext INSTANCE = new AppContext();

	public static AppContext getInstance() {
		return INSTANCE;
	}

	private final ActionProcessor actionProcessor = new ActionProcessorImpl();
	private final FileManager fileManager = new FileManagerImpl();
	private final EditSession editSession = new EditSession();

	private Project currentProject;

	private AppContext() {

	}

	public ActionProcessor getActionProcessor() {
		return actionProcessor;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public EditSession getEditSession() {
		return editSession;
	}

	public Project getCurrentProject() {
		return currentProject;
	}

	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
		fireProjectChanged(currentProject);
	}

	public interface ProjectListener {

		void onProjectChanged(Project project);

	}

	private List<ProjectListener> projectListeners = new CopyOnWriteArrayList<ProjectListener>();

	public void addProjectListener(ProjectListener l) {
		projectListeners.add(l);
	}

	public void removeProjectListener(ProjectListener l) {
		projectListeners.remove(l);
	}

	private void fireProjectChanged(Project project) {
		for (ProjectListener l : projectListeners) {
			// TODO handle exceptions per listener
			l.onProjectChanged(project);
		}
	}

}

package eu.cogbin.sprited.core;

import eu.cogbin.sprited.core.action.ActionProcessor;
import eu.cogbin.sprited.core.action.impl.ActionProcessorImpl;

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

	private AppContext() {

	}

	public ActionProcessor getActionProcessor() {
		return actionProcessor;
	}
}

package eu.cogbin.sprited.core.action.impl;

import java.util.Stack;
import java.util.logging.Logger;

import eu.cogbin.sprited.core.action.Action;
import eu.cogbin.sprited.core.action.ActionProcessor;

/**
 * 
 * @author Danny
 * 
 */
public class ActionProcessorImpl implements ActionProcessor {

	private Logger log = Logger.getLogger(ActionProcessorImpl.class.getName());

	private Stack<Action> performedActions = new Stack<Action>();
	private Stack<Action> undoneActions = new Stack<Action>();

	public void performAction(Action action) {

		// TODO consider performing actions in the background, off the EDT
		// TODO limit total number of actions kept in memory?

		log.info("Performing action: " + action);

		try {
			action.perform();
			performedActions.push(action);

			// Redo stack is no longer usable
			undoneActions.clear();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void undo() {
		if (performedActions.isEmpty()) {
			return;
		}
		Action action = performedActions.pop();

		log.info("Undoing action: " + action);

		try {
			action.undo();
			undoneActions.push(action);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void redo() {
		if (undoneActions.isEmpty()) {
			return;
		}
		Action action = undoneActions.pop();

		log.info("Redoing action: " + action);

		try {
			action.perform();
			performedActions.push(action);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

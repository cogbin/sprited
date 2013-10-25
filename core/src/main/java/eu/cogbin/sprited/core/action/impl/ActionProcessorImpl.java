package eu.cogbin.sprited.core.action.impl;

import java.util.Stack;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
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

	public void performAction(final Action action) {

		// TODO limit total number of actions kept in memory?

		doInBackground(new Runnable() {
			public void run() {
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
		});

	}

	public void undo() {

		doInBackground(new Runnable() {
			public void run() {
				if (performedActions.isEmpty()) {
					return;
				}
				final Action action = performedActions.pop();

				log.info("Undoing action: " + action);

				try {
					action.undo();
					undoneActions.push(action);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	public void redo() {

		doInBackground(new Runnable() {
			public void run() {
				if (undoneActions.isEmpty()) {
					return;
				}
				final Action action = undoneActions.pop();

				log.info("Redoing action: " + action);

				try {
					action.redo();
					performedActions.push(action);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}

	private Executor executor = new ThreadPoolExecutor(1, 1, 0,
			TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

	private synchronized void doInBackground(final Runnable r) {
		// TODO show progress dialog if the action specifies it wants one
		executor.execute(r);
	}

}

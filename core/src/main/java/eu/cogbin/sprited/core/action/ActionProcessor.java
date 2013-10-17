package eu.cogbin.sprited.core.action;

/**
 * 
 * @author Danny
 * 
 */
public interface ActionProcessor {

	void performAction(Action action);

	void undo();

	void redo();

}

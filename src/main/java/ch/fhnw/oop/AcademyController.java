package ch.fhnw.oop;


import ch.fhnw.oop.views.DetailView;

import java.util.ArrayDeque;
import java.util.Deque;

public class AcademyController {
	private final AcademyModel model;
	private final AcademyView view;

	private final Deque<ICommand> undoStack = new ArrayDeque<>();
	private final Deque<ICommand> redoStack = new ArrayDeque<>();

	public AcademyController(AcademyModel model) {
		this.model = model;
		this.view = new AcademyView(model, this);
	}

	public void initializeView(){
		view.createAndShow();
	}

	public void undo() {
		if (undoStack.isEmpty()) {
			return;
		}
		ICommand cmd = undoStack.pop();
		redoStack.push(cmd);
		setUndoRedoStatus();

		cmd.undo();
	}

	public void redo() {
		if (redoStack.isEmpty()) {
			return;
		}
		ICommand cmd = redoStack.pop();
		undoStack.push(cmd);
		setUndoRedoStatus();

		cmd.execute();
	}

	private void execute(ICommand cmd) {
		undoStack.push(cmd);
		redoStack.clear();
		setUndoRedoStatus();
		cmd.execute();
	}

	private void setUndoRedoStatus() {
		model.setRedoAvailable(!redoStack.isEmpty());
		model.setUndoAvailable(!undoStack.isEmpty());
	}

	public void updateView(){
		view.createAndShow();
	}

}

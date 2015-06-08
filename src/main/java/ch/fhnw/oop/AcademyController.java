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

	public void setValueAtSelectedRow(String value, int col) {
		model.setValueAt(value, model.getSelectedMovieId(), col);
	}

	public void setValueAt(String value, int index, int col) {
		model.setValueAt(value, index, col);
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


	public void setNumberOfOscarsAtSelectedMovie(int amount) {
		model.setNumberOfOscarsAtSelectedMovie(amount);
	}

	public void addNewMovie(){
		Movie movie = new Movie();
		model.add(movie);
	}

	public void removeMovie(){
//		model.remove(index);
	}


	public void onChangeCountry(String value) {
		model.getMovieById(model.getSelectedMovieId()).setCountry(value);
	}

	public void onChangeFsk(int value) {
		model.getMovieById(model.getSelectedMovieId()).setFsk(value);
	}

	public void onChangeMainActor(String value) {
		model.getMovieById(model.getSelectedMovieId()).setMainActor(value);
	}

	public void onChangeDirector(String value) {
		model.getMovieById(model.getSelectedMovieId()).setDirector(value);
	}

	public void onChangeDuration(int value) {
		model.getMovieById(model.getSelectedMovieId()).setDuration(value);
	}

	public void onChangeGenre(String value) {
		model.getMovieById(model.getSelectedMovieId()).setGenre(value);
	}

	public void onChangeNumberOfOscars(int value) {
		model.getMovieById(model.getSelectedMovieId()).setNumberOfOscars(value);
	}

	public void onChangeStartDate(String value) {
		model.getMovieById(model.getSelectedMovieId()).setStartDate(value);
	}

	public void onChangeTitle(String value) {
		model.getMovieById(model.getSelectedMovieId()).setTitle(value);
	}

	public void onChangeTitleEnglish(String value) {
		model.getMovieById(model.getSelectedMovieId()).setTitleEnglish(value);
	}

	public void onChangeYearOfAward(String value) {
		model.getMovieById(model.getSelectedMovieId()).setYearOfAward(value);
	}

	public void onChangeYearOfProduction(String value) {
		model.getMovieById(model.getSelectedMovieId()).setYearOfProduction(value);
	}


}

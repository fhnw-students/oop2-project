package ch.fhnw.oop;

public class AcademyController {
    private final AcademyModel model;
    private final AcademyView view;

    public AcademyController(AcademyModel model) {
        this.model = model;
        this.view = new AcademyView(model, this);
    }

    public void setValueAtSelectedRow(String value, int col) {
        model.setValueAt(value, model.getSelectedMovieId(), col);
    }

    public void addNewMovie() {
        Movie movie = new Movie();
        model.add(movie);
    }

    public void removeMovie() {
        model.removeById(model.getSelectedMovieId());
    }

    public void onChangeCountry(String value) {
        model.setSelectedMovieCountry(value);
    }

    public void onChangeFsk(int value) {
        model.setSelectedMovieFsk(value);
    }

    public void onChangeMainActor(String value) {
        model.setSelectedMovieMainActor(value);
    }

    public void onChangeDirector(String value) {
        model.setSelectedMovieDirector(value);
    }

    public void onChangeDuration(int value) {
        model.setSelectedMovieDuration(value);
    }

    public void onChangeGenre(String value) {
        model.setSelectedMovieGenre(value);
    }

    public void onChangeNumberOfOscars(int value) {
        model.setSelectedMovieNumberOfOscars(value);
    }

    public void onChangeStartDate(String value) {
        model.setSelectedMovieStartDate(value);
    }

    public void onChangeTitle(String value) {
        model.setSelectedMovieTitle(value);
    }

    public void onChangeTitleEnglish(String value) {
        model.setSelectedMovieTitleEnglish(value);
    }

    public void onChangeYearOfAward(String value) {
        model.setSelectedMovieYearOfAward(value);
    }

    public void onChangeYearOfProduction(String value) {
        model.setSelectedMovieYearOfProduction(value);
    }

    public void save() {
        this.model.exportListToCsv();
    }

    public void onChangeSearch(String value) {
        this.model.setSearchValue(value);
    }
}

    /////////////////////////////////
//    private final Deque<ICommand> undoStack = new ArrayDeque<>();
//    private final Deque<ICommand> redoStack = new ArrayDeque<>();
//    private void setUndoRedoStatus() {
//        model.setRedoAvailable(!redoStack.isEmpty());
//        model.setUndoAvailable(!undoStack.isEmpty());
//    }
//    public void undo() {
//        if (undoStack.isEmpty()) {
//            return;
//        }
//        ICommand cmd = undoStack.pop();
//        redoStack.push(cmd);
//        setUndoRedoStatus();
//
//        cmd.undo();
//    }
//
//    public void redo() {
//        if (redoStack.isEmpty()) {
//            return;
//        }
//        ICommand cmd = redoStack.pop();
//        undoStack.push(cmd);
//        setUndoRedoStatus();
//
//        cmd.execute();
//    }
//
//    private void execute(ICommand cmd) {
//        undoStack.push(cmd);
//        redoStack.clear();
//        setUndoRedoStatus();
//        cmd.execute();
//    }
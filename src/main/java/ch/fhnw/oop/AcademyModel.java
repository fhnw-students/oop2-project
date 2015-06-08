package ch.fhnw.oop;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcademyModel implements Observable {

    private static final String FILE_PATH = "./resources/data/movies.csv";

    private final Set<Observer> observers = new HashSet<>();
    private boolean isUndoAvailable = false;
    private boolean isRedoAvailable = false;

    private int selectedMovieId;

    private List<Movie> list = new ArrayList<>();

    public AcademyModel() throws IOException, URISyntaxException {
        list = readCSVFile(AcademyModel.class.getResource(FILE_PATH).toURI());
        selectedMovieId = list.get(0).getId();
    }

    public void setActorsAtSelectedMovie(String actor){
        Movie movie = list.get(selectedMovieId);
        movie.setMainActor(actor);
        list.set(selectedMovieId, movie);
        notifyObservers();
    }

    public List<Movie> getList(){
        return list;
    }

    public Movie getRow(int index) {
        return list.get(index);
    }

    public String getValueAt(int index, int col) {
        Movie movie = list.get(index);
        switch (col) {
            case 0:
                return movie.isHasModified() ? "X":"";
            case 1:
                return movie.getYearOfAward();
            case 2:
                return movie.getTitle();
            case 3:
                return movie.getDirector();
            default:
                return movie.getMainActor();
        }
    }

    public void setValueAt(String value, int index, int col) {
        Movie movie = list.get(index);
        switch (col) {
            case 0:
                movie.setHasModified((value == "X"));
                break;
            case 1:
                movie.setYearOfAward(value);
                break;
            case 2:
                movie.setTitle(value);
                break;
            case 3:
                movie.setDirector(value);
                break;
            default:
                movie.setMainActor(value);
        }
        list.set(index, movie);
        notifyObservers();
    }

    private static List<Movie> readCSVFile(URI csvFileName) throws IOException {

        String line;
        BufferedReader stream = null;
        List<Movie> csvData = new ArrayList<>();

        try {
            stream = new BufferedReader(new FileReader(new File(csvFileName)));
            stream.readLine();
            while ((line = stream.readLine()) != null) {
                csvData.add(new Movie(line));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {


        } finally {
            if (stream != null)
                stream.close();
        }

        return csvData;

    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public boolean isUndoAvailable() {
        return isUndoAvailable;
    }

    public void setUndoAvailable(boolean undoAvailable) {
        if (isUndoAvailable == undoAvailable) {
            return;
        }
        isUndoAvailable = undoAvailable;
        notifyObservers();
    }

    public boolean isRedoAvailable() {
        return isRedoAvailable;
    }

    public void setRedoAvailable(boolean redoAvailable) {
        if (isRedoAvailable == redoAvailable) {
            return;
        }
        isRedoAvailable = redoAvailable;
        notifyObservers();
    }


    public int getSelectedMovieId() {
        return selectedMovieId;
    }

    public void setSelectedMovieId(int selectedMovieId) {
        this.selectedMovieId = selectedMovieId;
        notifyObservers();
    }

    public Movie getModel(int row) {
        Movie movie = list.get(row);
        return movie;
    }

    public void add(Movie movie) {
        movie.setId(list.size());
        list.add(movie);
        setSelectedMovieId(movie.getId());
    }

    public void removeByIndex(int index){
        list.remove(index);
    }

    public void removeById(int id){
        int index = getIndexById(id);
        list.remove(index);
    }

    public void removeByMovie(Movie movie){
        int index = getIndexByMovie(movie);
        list.remove(index);
    }

    public int getIndexById(int id){
        int counter = 0;
        for (Movie m: list) {
            if(m.getId() == id){
                return counter;
            }
            ++counter;
        }
        return 0;
    }

    public int getIndexByMovie(Movie movie){
        return getIndexById(movie.getId());
    }

    public Movie getMovieById(int id){
        int index = getIndexById(id);
        return list.get(index);
    }

    public Movie getMovieByIndex(int index){
        return list.get(index);
    }

    public void setNumberOfOscarsAtSelectedMovie(int amount) {
        Movie movie = getMovieById(getSelectedMovieId());
        movie.setNumberOfOscars(amount);
        notifyObservers();
    }



}

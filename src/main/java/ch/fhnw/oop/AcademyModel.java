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


    private List<Movie> list = new ArrayList<>();

    public AcademyModel() throws IOException, URISyntaxException {
        list = readCSVFile(AcademyModel.class.getResource(FILE_PATH).toURI());

    }


    public List<Movie> getList(){
        return list;
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
            case 1:
                movie.setYearOfAward(value);
            case 2:
                movie.setTitle(value);
            case 3:
                movie.setDirector(value);
            default:
                movie.setMainActor(value);
        }
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

//    public int getCounter() {
//        return counter;
//    }
//
//    public void setCounter(int counter) {
//        this.counter = counter;
//    }
//
//    public void setInputValid(boolean inputValid) {
//        this.isInputValid = inputValid;
//    }

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


}

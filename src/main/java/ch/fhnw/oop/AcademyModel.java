package ch.fhnw.oop;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class AcademyModel implements Observable {

    private static final String FILE_PATH = "./resources/data/movies.csv";
    private static final String STRING_BOL_TRUE = "X";
    private static final String STRING_BOL_FALSE = "";
    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_DELETE = "delete";
    public static final String ACTION_PRISTINE = "pristine";
    public static final String ACTION_SEARCH = "search";
    public static final String ACTION_NONE = "";
    private static String csvFileHeader;
    private static boolean aMovieHasBeenRemoved = false;

    private final Set<Observer> observers = new HashSet<>();
    private boolean isUndoAvailable = false;
    private boolean isRedoAvailable = false;
    private int selectedMovieId;
    public int observerIndex;
    public String observerAction = "";
    private List<Movie> list = new ArrayList<>();
    private List<Movie> filterdList = new ArrayList<>();
    private String searchValue = "";

    public AcademyModel() throws IOException, URISyntaxException {
        filterdList = list = readCSVFile(AcademyModel.class.getResource(FILE_PATH).toURI());
        selectedMovieId = list.get(0).getId();
    }

    public List<Movie> getList() {
        return this.list;
    }

    public List<Movie> getFilteredList() {
//        Collections.sort(filterdList);
        return filterdList;
    }

    public String getValueAt(int index, int col) {
        Movie movie = filterdList.get(index);
        switch (col) {
            case 0:
                return movie.isHasModified() ? STRING_BOL_TRUE : STRING_BOL_FALSE;
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
        Movie movie = filterdList.get(index);
        switch (col) {
            case 0:
                movie.setHasModified((value.equals(STRING_BOL_TRUE)));
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
        filterdList.set(index, movie);
        notifyObservers();
    }

    private static List<Movie> readCSVFile(URI csvFileName) throws IOException {
        String line;
        BufferedReader stream = null;
        List<Movie> csvData = new ArrayList<>();

        try {
            stream = new BufferedReader(new FileReader(new File(csvFileName)));
            csvFileHeader = stream.readLine();
            while ((line = stream.readLine()) != null) {
                csvData.add(new Movie(line));
            }

        } catch (IOException e) {
            e.printStackTrace();

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

    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public int getSelectedMovieId() {
        return selectedMovieId;
    }

    public void setSelectedMovieId(int selectedMovieId) {
        this.selectedMovieId = selectedMovieId;
        notifyObservers();
    }

    public boolean hasModelBeenChanged() {
        long counter = list.stream()
                .map(Movie::isHasModified)
                .filter(a -> a)
                .count();
        return counter > 0 || aMovieHasBeenRemoved;
    }

    public void add(Movie movie) {
        final Comparator<Movie> comp = (p1, p2) -> Integer.compare(p1.getId(), p2.getId());
        int maxId = this.list.stream().max(comp).get().getId();
        movie.setId(maxId + 1);
        this.list.add(movie);
        this.selectedMovieId = movie.getId();
        this.observerIndex = getIndexByMovie(movie);
        this.observerAction = ACTION_INSERT;
        notifyObservers();
    }

    public void removeById(int id) {
        Integer index = getIndexById(id);
        if (index.equals(this.list.size() - 1)) {
            this.selectedMovieId = getMovieByIndex(index - 1).getId();
        } else {
            this.selectedMovieId = getMovieByIndex(index + 1).getId();
        }
        this.list.remove((int) index);
        this.observerIndex = index;
        this.observerAction = ACTION_DELETE;
        aMovieHasBeenRemoved = true;
        notifyObservers();
    }

    public int getIndexById(int id) {
        int counter = 0;
        for (Movie m : list) {
            if (m.getId() == id) {
                return counter;
            }
            ++counter;
        }
        return 0;
    }

    public int getIndexByMovie(Movie movie) {
        return getIndexById(movie.getId());
    }

    public Movie getMovieById(int id) {
        int index = getIndexById(id);
        return list.get(index);
    }

    public Movie getMovieByIndex(int index) {
        return filterdList.get(index);
    }

    public void setSelectedMovieCountry(String value) {
        this.getMovieById(this.getSelectedMovieId()).setCountry(value);
        this.notifyObservers();
    }

    public void setSelectedMovieFsk(int value) {
        this.getMovieById(this.getSelectedMovieId()).setFsk(value);
        this.notifyObservers();
    }

    public void setSelectedMovieMainActor(String value) {
        this.getMovieById(this.getSelectedMovieId()).setMainActor(value);
        this.notifyObservers();
    }

    public void setSelectedMovieDirector(String value) {
        this.getMovieById(this.getSelectedMovieId()).setDirector(value);
        this.notifyObservers();
    }

    public void setSelectedMovieDuration(int value) {
        this.getMovieById(this.getSelectedMovieId()).setDuration(value);
        this.notifyObservers();
    }

    public void setSelectedMovieGenre(String value) {
        this.getMovieById(this.getSelectedMovieId()).setGenre(value);
        this.notifyObservers();
    }

    public void setSelectedMovieNumberOfOscars(int value) {
        this.getMovieById(this.getSelectedMovieId()).setNumberOfOscars(value);
        this.notifyObservers();
    }

    public void setSelectedMovieStartDate(String value) {
        this.getMovieById(this.getSelectedMovieId()).setStartDate(value);
        this.notifyObservers();
    }

    public void setSelectedMovieTitle(String value) {
        this.getMovieById(this.getSelectedMovieId()).setTitle(value);
        this.notifyObservers();
    }

    public void setSelectedMovieTitleEnglish(String value) {
        this.getMovieById(this.getSelectedMovieId()).setTitleEnglish(value);
        this.notifyObservers();
    }

    public void setSelectedMovieYearOfAward(String value) {
        this.getMovieById(this.getSelectedMovieId()).setYearOfAward(value);
        this.notifyObservers();
    }

    public void setSelectedMovieYearOfProduction(String value) {
        this.getMovieById(this.getSelectedMovieId()).setYearOfProduction(value);
        this.notifyObservers();
    }

    private void pristineList() {
        this.list.stream().forEach(movie -> movie.setHasModified(false));
        this.observerAction = ACTION_PRISTINE;
        aMovieHasBeenRemoved = false;
        this.notifyObservers();
    }

    public void exportListToCsv() {
        BufferedWriter br;
        try {
            try {
                br = new BufferedWriter(
                        new FileWriter(
                                new File(
                                        AcademyModel.class.getResource(FILE_PATH).toURI()
                                )
                        )
                );

                // Add header line
                br.write(csvFileHeader);
                br.newLine();
                // Add body
                for (Movie movie : this.list) {
                    try {
                        br.write(movie.toString());
                        br.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                br.close();
                this.pristineList();

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        if (searchValue.length() > 2) {
            this.searchValue = searchValue;
            FuzzySearch fuzzySearch = new FuzzySearch(this);
            this.filterdList = fuzzySearch.filter(searchValue);
            this.setSelectedMovieId(this.filterdList.get(0).getId());
        } else {
            this.searchValue = "";
            this.filterdList = this.list;
        }

        this.observerAction = ACTION_SEARCH;
        notifyObservers();

    }

    public boolean areAllMoviesValid(){
        long counter = this.list.stream()
                .map(Movie::isValid)
                .filter(b -> !b)
                .count();
        return counter == 0;
    }

    public boolean editorIsValid() {
        Movie movie = getMovieById(getSelectedMovieId());

        movie.setIsValid(false);

        if (!MovieValidator.isValidYear(movie.getYearOfAward())) {
            return false;
        }

        if (!MovieValidator.isRequired(movie.getTitle())) {
            return false;
        }

        if (!MovieValidator.isRequired(movie.getDirector())) {
            return false;
        }

        if (!MovieValidator.isRequired(movie.getMainActor())) {
            return false;
        }

        if (!MovieValidator.isDate(movie.getStartDate())) {
            return false;
        }

        if (!MovieValidator.isFlag(movie.getCountry())) {
            return false;
        }

        if (!MovieValidator.isNumber(movie.getFsk().toString())) {
            return false;
        }

        if (!MovieValidator.isNumber(movie.getDuration().toString())) {
            return false;
        }

        if (!MovieValidator.isNumber(movie.getNumberOfOscars().toString()) || movie.getNumberOfOscars() < 1) {
            return false;
        }

        movie.setIsValid(true);
        return true;
    }

}
    //    public boolean isUndoAvailable() {
//        return isUndoAvailable;
//    }

//    public void setUndoAvailable(boolean undoAvailable) {
//        if (isUndoAvailable == undoAvailable) {
//            return;
//        }
//        isUndoAvailable = undoAvailable;
//        notifyObservers();
//    }

//    public boolean isRedoAvailable() {
//        return isRedoAvailable;
//    }

//    public void setRedoAvailable(boolean redoAvailable) {
//        if (isRedoAvailable == redoAvailable) {
//            return;
//        }
//        isRedoAvailable = redoAvailable;
//        notifyObservers();
//    }
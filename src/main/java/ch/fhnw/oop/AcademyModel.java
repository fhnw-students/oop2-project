package ch.fhnw.oop;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class AcademyModel implements Observable {

//    private static final String FILE_PATH = "/Users/HirschBookPro/Documents/fhnw/projects/oop2-project/out/ch/fhnw/oop/resources/data/movies.csv";
    private static final String FILE_PATH = "./resources/data/movies.csv";
    private static final String DELIMITER_NEXT_ROW = "\\n";
    private static final String DELIMITER_NEXT_COL = ";";

    private final Set<Observer> observers = new HashSet<>();
    private boolean isUndoAvailable = false;
    private boolean isRedoAvailable = false;

//    private ArrayList<String> data = new ArrayList<>();
//    private Stream<String> stream;

    private List<List<String>> data = new ArrayList<List<String>>();

    public AcademyModel() throws IOException, URISyntaxException {
        System.out.println(AcademyModel.class.getResource(FILE_PATH).toURI());

        data = readCSVFile(AcademyModel.class.getResource(FILE_PATH).toURI());

        System.out.println(data);
    }

    private static List<List<String>> readCSVFile(URI csvFileName) throws IOException {

        String line = null;
        BufferedReader stream = null;
        List<List<String>> csvData = new ArrayList<List<String>>();

        try {
            stream = new BufferedReader(new FileReader(new File(csvFileName)));
            line = stream.readLine();
            while ((line = stream.readLine()) != null) {
                String[] splitted = line.split(DELIMITER_NEXT_COL);
                List<String> dataLine = new ArrayList<String>(splitted.length);

                dataLine.add(" ");
                for (String data : splitted)
                    dataLine.add(data);

                csvData.add(dataLine);
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

    private InputStreamReader getInputStreamReader(String fileName) throws UnsupportedEncodingException {
        InputStream inputStream = AcademyModel.class.getResourceAsStream(fileName);
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }

    private Stream<String> getStreamOfLines(String fileName) {
        try {
            return Files.lines(Paths.get(AcademyModel.class.getResource(fileName).toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
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

//    public Object[][] getList() {
//        return data;
//    }
}

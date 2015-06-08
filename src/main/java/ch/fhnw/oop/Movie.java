package ch.fhnw.oop;

import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    /**
     * Variables
     */
    private static final String DELIMITER_NEXT_DATA = ";";

    private boolean hasModified = false;

    //#id;Title;yearOfAward;director;mainActor;titleEnglish;yearOfProduction;country;duration;fsk;genre;startDate
    private Integer id;
    private String title;
    private String yearOfAward;
    private String director;
    private String mainActor;
    private String titleEnglish;
    private String yearOfProduction;
    private String country;
    private Integer duration;
    private Integer fsk;
    private String genre;
    private String startDate;
    private Integer numberOfOscars = 1;
    private ImageIcon poster;

    /**
     * Constructors
     */
    public Movie() {
        this.hasModified = true;
        this.fsk = 0;
        this.duration = 1;
        this.numberOfOscars = 1;
        this.startDate = "-";
    }

    public Movie(Integer id) {
        this.id = id;
        this.hasModified = true;
        this.fsk = 0;
        this.duration = 1;
        this.numberOfOscars = 1;
        this.startDate = "-";
    }

    public Movie(String csvLine) {
        String[] splitted = csvLine.split(DELIMITER_NEXT_DATA);
        this.id = Integer.parseInt(splitted[0]);
        this.title = splitted[1];
        this.yearOfAward = splitted[2];
        this.director = splitted[3];
        this.mainActor = splitted[4];
        this.titleEnglish = splitted[5];
        this.yearOfProduction = splitted[6];
        this.country = splitted[7];
        this.duration = Integer.parseInt(splitted[8]);
        this.fsk = Integer.parseInt(splitted[9]);
        this.genre = splitted[10];
        this.startDate = splitted[11];
    }

    /**
     * API
     */
    public boolean isHasModified() {
        return hasModified;
    }

    public void setHasModified(boolean hasModified) {
        this.hasModified = hasModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.setHasModified((this.validateChange(title, this.title)) || this.hasModified);
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.setHasModified((this.validateChange(id, this.id)) || this.hasModified);
        this.id = id;
    }

    public String getYearOfAward() {
        return yearOfAward;
    }

    public void setYearOfAward(String yearOfAward) {
        this.setHasModified((this.validateChange(yearOfAward, this.yearOfAward)) || this.hasModified);
        this.yearOfAward = yearOfAward;

    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.setHasModified((this.validateChange(director, this.director)) || this.hasModified);
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.setHasModified((this.validateChange(mainActor, this.mainActor)) || this.hasModified);
        this.mainActor = mainActor;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.setHasModified((this.validateChange(titleEnglish, this.titleEnglish)) || this.hasModified);
        this.titleEnglish = titleEnglish;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.setHasModified((this.validateChange(yearOfProduction, this.yearOfProduction)) || this.hasModified);
        this.yearOfProduction = yearOfProduction;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.setHasModified((this.validateChange(country, this.country)) || this.hasModified);
        this.country = country;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.setHasModified((this.validateChange(duration, this.duration)) || this.hasModified);
        this.duration = duration;
    }

    public Integer getFsk() {
        return fsk;
    }

    public void setFsk(Integer fsk) {
        this.setHasModified((this.validateChange(fsk, this.fsk)) || this.hasModified);
        this.fsk = fsk;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.setHasModified((this.validateChange(genre, this.genre)) || this.hasModified);
        this.genre = genre;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.setHasModified((this.validateChange(startDate, this.startDate)) || this.hasModified);
        this.startDate = startDate;
    }

    public Integer getNumberOfOscars() {
        return this.numberOfOscars;
    }

    public void setNumberOfOscars(Integer numberOfOscars) {
        this.setHasModified((this.validateChange(numberOfOscars, this.numberOfOscars)) || this.hasModified);
        this.numberOfOscars = numberOfOscars < 1 ? 1 : numberOfOscars;
    }

    public void setPoster(ImageIcon poster) {
        this.poster = poster;
    }

    public ImageIcon getPoster() {
        return this.poster;
    }

    private boolean validateChange(Object newValue, Object oldValue) {
        return !newValue.equals(oldValue);
    }

    @Override
    public String toString() {
        List<String> rows = new ArrayList<>();
        rows.add(this.id.toString());
        rows.add(this.title);
        rows.add(this.yearOfAward);
        rows.add(this.director);
        rows.add(this.mainActor);
        rows.add(this.titleEnglish);
        rows.add(this.yearOfProduction);
        rows.add(this.country);
        rows.add(this.duration.toString());
        rows.add(this.fsk.toString());
        rows.add(this.genre);
        rows.add(this.startDate);
        rows.add(this.numberOfOscars.toString());
        return StringUtils.join(rows, DELIMITER_NEXT_DATA);
    }

}

package ch.fhnw.oop;

public class Movie {

    /**
     * Variables
     */
    private static final String DELIMITER_NEXT_DATA = ";";

    private boolean hasModified = false;

    //#id;Title;yearOfAward;director;mainActor;titleEnglish;yearOfProduction;country;duration;fsk;genre;startDate
    private int id;
    private String title;
    private String yearOfAward;
    private String director;
    private String mainActor;
    private String titleEnglish;
    private String yearOfProduction;
    private String country;
    private Integer duration;
    private String fsk;
    private String genre;
    private String startDate;

    /**
     * Constructors
     */
    public Movie() {
        hasModified = true;
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
        this.fsk = splitted[9];
        this.genre = splitted[10];
        this.startDate = splitted[11];
    }


    /**
     * API
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        hasModified = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        hasModified = true;
    }

    public boolean isHasModified() {
        return hasModified;
    }

    public void setHasModified(boolean hasModified) {
        this.hasModified = hasModified;
    }

    public String getYearOfAward() {
        return yearOfAward;
    }

    public void setYearOfAward(String yearOfAward) {
        this.yearOfAward = yearOfAward;
        hasModified = true;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
        hasModified = true;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
        hasModified = true;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
        hasModified = true;
    }

    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
        hasModified = true;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        hasModified = true;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
        hasModified = true;
    }

    public String getFsk() {
        return fsk;
    }

    public void setFsk(String fsk) {
        this.fsk = fsk;
        hasModified = true;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        hasModified = true;
    }

}

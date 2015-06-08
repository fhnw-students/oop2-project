package ch.fhnw.oop;

/**
 * Created by HirschBookPro on 08/06/15.
 */
public class FuzzySearchItem {

    // Properties
    private Movie movie;
    private Integer distance;

    // Constructors
    FuzzySearchItem(Movie movie, Integer distance) {
        this.movie = movie;
        this.distance = distance;
    }

    FuzzySearchItem(Movie movie) {
        this.movie = movie;
        this.distance = null;
    }

    // API
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}

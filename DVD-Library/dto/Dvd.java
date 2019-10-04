package com.sg.dvdlibrary.dto;

/**
 *
 * @author brian russick
 */
public class Dvd {                    // dvd properties with getters/setters

    private String title;
    private String releaseDate;
    private String rating;
    private String directorName;
    private String studio;
    private String userReview;

    public Dvd(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }
}
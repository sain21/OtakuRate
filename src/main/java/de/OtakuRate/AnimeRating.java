package de.OtakuRate;

public class AnimeRating {
    private String animeTitle;
    private int rating;
    private String experience;

    public AnimeRating(String animeTitle, int rating, String experience) {
        this.animeTitle = animeTitle;
        this.rating = rating;
        this.experience = experience;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public int getRating() {
        return rating;
    }

    public String getExperience() {
        return experience;
    }
}
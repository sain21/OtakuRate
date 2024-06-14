package de.OtakuRate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AnimeRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String animeTitle;
    private int rating;
    private String experience;


    public AnimeRating(String animeTitle, int rating, String experience) {
        this.animeTitle = animeTitle;
        this.rating = rating;
        this.experience = experience;
    }

    public AnimeRating() {
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
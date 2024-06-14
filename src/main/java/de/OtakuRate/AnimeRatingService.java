package de.OtakuRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeRatingService {
    @Autowired
    AnimeRatingRepo animeRatingRepo;

    public AnimeRating saveOrUpdateAnimeRating(AnimeRating animeRating) {
        return animeRatingRepo.save(animeRating);
    }

    public Iterable<AnimeRating> findAllAnimeRatings() {
        return animeRatingRepo.findAll();
    }
}

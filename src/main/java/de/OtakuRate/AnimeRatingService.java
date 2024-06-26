package de.OtakuRate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeRatingService {
    @Autowired
    AnimeRatingRepo animeRatingRepo;

    public AnimeRating saveAnimeRating(AnimeRating animeRating) {
        return animeRatingRepo.save(animeRating);
    }

    public Iterable<AnimeRating> findAllAnimeRatings() {
        return animeRatingRepo.findAll();
    }

    public void deleteAnimeRatingById(Long id) {
        animeRatingRepo.deleteById(id);
    }
public AnimeRating updateAnimeRating(Long id, AnimeRating newAnimeRating) {
        return animeRatingRepo.findById(id)
                .map(animeRating ->{
                    animeRating.setAnimeTitle(newAnimeRating.getAnimeTitle());
                    animeRating.setRating(newAnimeRating.getRating());
                    animeRating.setExperience(newAnimeRating.getExperience());
                    return animeRatingRepo.save(animeRating);
                })
                .orElseGet(() -> {
                    newAnimeRating.setId(id);
                    return animeRatingRepo.save(newAnimeRating);
                });
    }


}

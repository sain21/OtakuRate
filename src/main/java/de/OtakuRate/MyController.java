package de.OtakuRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = {"https://otakurate-frontend.onrender.com"})
@RequestMapping("/rate")
public class MyController {


   @Autowired
    private AnimeRatingService animeRatingService;
   @PostMapping
    public AnimeRating createAnimeRating(@RequestBody AnimeRating animeRating) {
        return animeRatingService.saveAnimeRating(animeRating);
    }

    @GetMapping
    public List<AnimeRating> findAllAnimeRatings() {
        return (List<AnimeRating>) animeRatingService.findAllAnimeRatings();
    }
    @DeleteMapping("/{id}")
    public void deleteAnimeRatingById(@PathVariable Long id) {
        animeRatingService.deleteAnimeRatingById(id);
    }
@PutMapping("/{id}")
    public AnimeRating updateAnimeRating(@PathVariable Long id, @RequestBody AnimeRating animeRating) {
        return animeRatingService.updateAnimeRating(id, animeRating);
    }



}

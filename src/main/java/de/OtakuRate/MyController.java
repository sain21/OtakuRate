package de.OtakuRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = {"https://otakurate-frontend.onrender.com/"})
@RequestMapping("/rate")
public class MyController {


   @Autowired
    private AnimeRatingService animeRatingService;
   @PostMapping
    public AnimeRating createAnimeRating(@RequestBody AnimeRating animeRating) {
        return animeRatingService.saveOrUpdateAnimeRating(animeRating);
    }

    @GetMapping
    public List<AnimeRating> findAllAnimeRatings() {
        return (List<AnimeRating>) animeRatingService.findAllAnimeRatings();
    }




}

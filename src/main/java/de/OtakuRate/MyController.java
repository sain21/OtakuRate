package de.OtakuRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@CrossOrigin(origins = {"https://otakurate-frontend.onrender.com"})
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
    @GetMapping("/searchAnime")
    public String searchAnime(@RequestParam String query) {
        String url = "https://api.jikan.moe/v4/anime?q=" + query;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}






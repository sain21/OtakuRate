package de.OtakuRate;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/rate")

public class MyController {

    @GetMapping("/rate")
    public List<AnimeRating> index() {
        AnimeRating rating1 = new AnimeRating("Naruto", 100, "Great experience!");
        AnimeRating rating2 = new AnimeRating("One Piece", 80, "Good but could be better");
        AnimeRating rating3 = new AnimeRating("Attack on Titan", 95, "Amazing!");
        AnimeRating rating4 = new AnimeRating("Bleach", 2, "Disappointing");
        AnimeRating rating5 = new AnimeRating("Erased", 70, "Enjoyed it a lot!");

        return List.of(rating1, rating2, rating3, rating4, rating5);
    }
}

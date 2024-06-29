package de.OtakuRate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MyController.class)
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimeRatingService animeRatingService;

    @Test
    @DisplayName("should return all anime ratings")
    void should_return_all_anime_ratings() throws Exception {
        // given
        List<AnimeRating> ratings = Arrays.asList(
                new AnimeRating("Naruto", 8, "Very good"),
                new AnimeRating("One Piece", 9, "Excellent")
        );
        doReturn(ratings).when(animeRatingService).findAllAnimeRatings();

        // when & then
        mockMvc.perform(get("/rate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].animeTitle").value("Naruto"))
                .andExpect(jsonPath("$[0].rating").value(8))
                .andExpect(jsonPath("$[0].experience").value("Very good"))
                .andExpect(jsonPath("$[1].animeTitle").value("One Piece"))
                .andExpect(jsonPath("$[1].rating").value(9))
                .andExpect(jsonPath("$[1].experience").value("Excellent"));
    }

    @Test
    @DisplayName("should create a new anime rating")
    void should_create_a_new_anime_rating() throws Exception {
        // given
        AnimeRating newRating = new AnimeRating("Bleach", 7, "Good");
        doReturn(newRating).when(animeRatingService).saveAnimeRating(any(AnimeRating.class));

        // when & then
        mockMvc.perform(post("/rate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"animeTitle\":\"Bleach\",\"rating\":7,\"experience\":\"Good\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animeTitle").value("Bleach"))
                .andExpect(jsonPath("$.rating").value(7))
                .andExpect(jsonPath("$.experience").value("Good"));
    }

    @Test
    @DisplayName("should delete an anime rating by id")
    void should_delete_an_anime_rating_by_id() throws Exception {
        // given
        Long idToDelete = 1L;
        doNothing().when(animeRatingService).deleteAnimeRatingById(idToDelete);

        // when & then
        mockMvc.perform(delete("/rate/" + idToDelete))
                .andExpect(status().isOk());
        verify(animeRatingService).deleteAnimeRatingById(idToDelete);
    }

    @Test
    @DisplayName("should update an anime rating")
    void should_update_an_anime_rating() throws Exception {
        // given
        Long idToUpdate = 1L;
        AnimeRating updatedRating = new AnimeRating("Naruto Shippuden", 10, "Outstanding");
        doReturn(updatedRating).when(animeRatingService).updateAnimeRating(eq(idToUpdate), any(AnimeRating.class));

        // when & then
        mockMvc.perform(put("/rate/" + idToUpdate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"animeTitle\":\"Naruto Shippuden\",\"rating\":10,\"experience\":\"Outstanding\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.animeTitle").value("Naruto Shippuden"))
                .andExpect(jsonPath("$.rating").value(10))
                .andExpect(jsonPath("$.experience").value("Outstanding"));
    }
}

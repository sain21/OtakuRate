package de.OtakuRate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.WithAssertions;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimeRatingServiceTest implements WithAssertions {

    @Mock
    private AnimeRatingRepo animeRatingRepo;

    @InjectMocks
    private AnimeRatingService underTest;

    @Test
    @DisplayName("should save and return anime rating successfully")
    void should_save_and_return_anime_rating_successfully() {
        // given
        AnimeRating animeRating = new AnimeRating("Naruto", 8, "Very good");
        doReturn(animeRating).when(animeRatingRepo).save(any(AnimeRating.class));

        // when
        AnimeRating savedAnimeRating = underTest.saveAnimeRating(animeRating);

        // then
        verify(animeRatingRepo).save(animeRating);
        assertThat(savedAnimeRating).isEqualTo(animeRating);
    }

    @Test
    @DisplayName("should delete anime rating by id")
    void should_delete_anime_rating_by_id() {
        // given
        Long givenId = 1L;
        doNothing().when(animeRatingRepo).deleteById(givenId);

        // when
        underTest.deleteAnimeRatingById(givenId);

        // then
        verify(animeRatingRepo).deleteById(givenId);
    }

    @Test
    @DisplayName("should find all anime ratings")
    void should_find_all_anime_ratings() {
        // when
        underTest.findAllAnimeRatings();

        // then
        verify(animeRatingRepo).findAll();
    }

    @Test
    @DisplayName("should update existing anime rating")
    void should_update_existing_anime_rating() {
        // given
        Long givenId = 1L;
        AnimeRating existingAnimeRating = new AnimeRating("Naruto", 8, "Good");
        AnimeRating updatedAnimeRating = new AnimeRating("Naruto Shippuden", 9, "Great");
        doReturn(Optional.of(existingAnimeRating)).when(animeRatingRepo).findById(givenId);
        doReturn(updatedAnimeRating).when(animeRatingRepo).save(any(AnimeRating.class));

        // when
        AnimeRating result = underTest.updateAnimeRating(givenId, updatedAnimeRating);

        // then
        verify(animeRatingRepo).findById(givenId);
        verify(animeRatingRepo).save(existingAnimeRating);
        assertThat(result.getAnimeTitle()).isEqualTo("Naruto Shippuden");
        assertThat(result.getRating()).isEqualTo(9);
        assertThat(result.getExperience()).isEqualTo("Great");
    }

    @Test
    @DisplayName("should return new anime rating if not existing when updating")
    void should_return_new_anime_rating_if_not_existing_when_updating() {
        // given
        Long givenId = 2L;
        AnimeRating newAnimeRating = new AnimeRating("One Piece", 10, "Excellent");
        doReturn(Optional.empty()).when(animeRatingRepo).findById(givenId);
        doReturn(newAnimeRating).when(animeRatingRepo).save(any(AnimeRating.class));

        // when
        AnimeRating result = underTest.updateAnimeRating(givenId, newAnimeRating);

        // then
        verify(animeRatingRepo).findById(givenId);
        verify(animeRatingRepo).save(newAnimeRating);
        assertThat(result).isEqualTo(newAnimeRating);
    }
}

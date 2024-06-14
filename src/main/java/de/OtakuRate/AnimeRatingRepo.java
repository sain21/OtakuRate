package de.OtakuRate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRatingRepo extends CrudRepository<AnimeRating, Long>{
}

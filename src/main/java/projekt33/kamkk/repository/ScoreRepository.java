package projekt33.kamkk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}

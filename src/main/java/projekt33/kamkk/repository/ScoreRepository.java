package projekt33.kamkk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
  List<Score> findAllByUserId(Long id);
  List<Score> findAllByCardId(Long id);

  void deleteAllByUserId(Long id);
  void deleteAllByCardId(Long id);
}

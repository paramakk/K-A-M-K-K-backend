package projekt33.kamkk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
  List<Card> findAllByCardGroupId(Long id);
}

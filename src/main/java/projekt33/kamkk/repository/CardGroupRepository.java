package projekt33.kamkk.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.CardGroup;

@Repository
public interface CardGroupRepository extends JpaRepository<CardGroup, Long> {
  List<CardGroup> findAllByAuthorId(Long id);
  List<CardGroup> findAllByThemeId(Long id);

  void deleteByIdAndCardsIsNull(Long id);
  void deleteAllByThemeIdAndCardsIsNull(Long id);
  void deleteAllByAuthorIdAndCardsIsNull(Long id);
}

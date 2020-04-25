package projekt33.kamkk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
  void deleteByIdAndScoresIsNullAndCreatedCardsIsNull(Long id);
}

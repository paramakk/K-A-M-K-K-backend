package projekt33.kamkk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByCardGroupId( Long id);

    void deleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(Long id);
    void deleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(Long id);

}

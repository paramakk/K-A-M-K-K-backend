package projekt33.kamkk;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.Score;
import projekt33.kamkk.entity.UserEntity;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ImageRepository;
import projekt33.kamkk.repository.ScoreRepository;
import projekt33.kamkk.repository.UserEntityRepository;

@SpringBootTest
public class ScoreTests {
  @Autowired
  private UserEntityRepository userEntityRepository;

  @Autowired
  private ScoreRepository scoreRepository;

  @Autowired
  private CardRepository cardRepository;

  private Long scoreId;
  private Long userId;
  private Long cardId;
  private final Long notFoundId = 9999L;

  @BeforeEach
  void init() {
    UserEntity userEntity = userEntityRepository.save(
      UserEntity
        .builder()
        .name("Test Test")
        .email("test@test.com")
        .createdAt(Calendar.getInstance().getTime())
        .build()
    );
    userId = userEntity.getId();

    Card card = cardRepository.save(
      Card
        .builder()
        .question("Test")
        .answer("I hope so.")
        .createdAt(Calendar.getInstance().getTime())
        .maxPoints(10)
        .build()
    );
    cardId = card.getId();

    Score score = scoreRepository.save(
      Score
        .builder()
        .user(userEntity)
        .card(card)
        .reviewedAt(Calendar.getInstance().getTime())
        .submittedScore(11)
        .revisionNumber(11)
        .build()
    );
    scoreId = score.getId();
  }

  @AfterEach
  void clean() {
    scoreRepository.deleteAll();
    userEntityRepository.deleteAll();
  }

  @Test
  void testScore() {
    List<Score> scores = scoreRepository.findAll();
    assertEquals(1, scores.size());
  }

  @Test
  void successfulFindById() {
    Optional<Score> optionalScore = scoreRepository.findById(scoreId);
    assertTrue(optionalScore.isPresent());
  }

  @Test
  void unsuccessfulFindById() {
    Optional<Score> optionalScore = scoreRepository.findById(notFoundId);
    assertFalse(optionalScore.isPresent());
  }

  @Test
  void successfulFindAllByUserId() {
    List<Score> scores = scoreRepository.findAllByUserId(userId);
    assertEquals(1, scores.size());
  }

  @Test
  void unsuccessfulFindAllByUserId() {
    List<Score> scores = scoreRepository.findAllByUserId(notFoundId);
    assertEquals(0, scores.size());
  }

  @Test
  void successfulFindAllByCardId() {
    List<Score> scores = scoreRepository.findAllByCardId(cardId);
    assertEquals(1, scores.size());
  }

  @Test
  void unsuccessfulFindAllByCardId() {
    List<Score> scores = scoreRepository.findAllByCardId(notFoundId);
    assertEquals(0, scores.size());
  }

  @Test
  void successfulDeleteById() {
    scoreRepository.deleteById(scoreId);
    List<Score> images = scoreRepository.findAll();
    assertEquals(0, images.size());
  }

  @Test
  void unsuccessfulDeleteById() {
    try {
      scoreRepository.delete(
        scoreRepository
          .findById(notFoundId)
          .orElseThrow(EntityNotFoundException::new)
      );
    } catch (EntityNotFoundException ex) {
      assertTrue(true);
    }
  }

  @Test
  @Transactional
  void successfulDeleteAllByUserId() {
    scoreRepository.deleteAllByUserId(userId);
    List<Score> scores = scoreRepository.findAll();
    assertEquals(0, scores.size());
  }

  @Test
  void unsuccessfulDeleteAllByUserId() {
    scoreRepository.deleteAllByUserId(notFoundId);
    List<Score> scores = scoreRepository.findAll();
    assertEquals(1, scores.size());
  }

  @Test
  @Transactional
  void successfulDeleteAllByCardId() {
    scoreRepository.deleteAllByCardId(cardId);
    List<Score> scores = scoreRepository.findAll();
    assertEquals(0, scores.size());
  }

  @Test
  void unsuccessfulDeleteAllByCardId() {
    scoreRepository.deleteAllByCardId(notFoundId);
    List<Score> scores = scoreRepository.findAll();
    assertEquals(1, scores.size());
  }
}

package projekt33.kamkk;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;

@SpringBootTest
public class CardTests {
  @Autowired
  private CardGroupRepository cardGroupRepository;

  @Autowired
  private CardRepository cardRepository;

  private Long cardId;
  private final Long notFoundId = 9999L;

  @BeforeEach
  void init() {
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
  }

  @AfterEach
  void clean() {
    cardRepository.deleteAll();
    cardGroupRepository.deleteAll();
  }

  @Test
  void testCardGroup() {
    List<Card> cards = cardRepository.findAll();
    assertEquals(1, cards.size());
  }

  @Test
  void successfulFindById() {
    Optional<Card> optionalCard = cardRepository.findById(cardId);
    assertTrue(optionalCard.isPresent());
  }

  @Test
  void unsuccessfulFindById() {
    Optional<Card> optionalCard = cardRepository.findById(notFoundId);
    assertFalse(optionalCard.isPresent());
  }

  @Test
  void successfulFindAllByCardGroupId() {
    CardGroup cardGroup = cardGroupRepository.save(
      CardGroup.builder().creationDate(Calendar.getInstance().getTime()).build()
    );
    cardRepository.save(
      Card
        .builder()
        .cardGroup(
          cardGroupRepository
            .findById(cardGroup.getId())
            .orElseThrow(EntityNotFoundException::new)
        )
        .build()
    );

    List<Card> cards = cardRepository.findAllByCardGroupId(cardGroup.getId());
    assertEquals(1, cards.size());
  }

  @Test
  void unsuccessfulFindAllByCardGroupId() {
    List<Card> cards = cardRepository.findAllByCardGroupId(notFoundId);
    assertEquals(0, cards.size());
  }
}

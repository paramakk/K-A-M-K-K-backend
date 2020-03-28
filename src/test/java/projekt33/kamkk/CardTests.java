package projekt33.kamkk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Image;
import projekt33.kamkk.entity.Score;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ImageRepository;
import projekt33.kamkk.repository.ScoreRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CardTests {

    @Autowired
    private CardGroupRepository cardGroupRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    private Long cardId;
    private final Long notFoundId = 9999L;

    @BeforeEach
    void init() {
        Card card = cardRepository.save(Card.builder()
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
        scoreRepository.deleteAll();
        imageRepository.deleteAll();
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
        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .build()
        );
        cardRepository.save(Card.builder()
                .cardGroup(cardGroupRepository.findById(cardGroup.getId()).orElseThrow(EntityNotFoundException::new))
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

    @Test
    @Transactional
    void successfulDeleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull() {
        cardRepository.deleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(cardId);
        List<Card> cards = cardRepository.findAll();
        assertEquals(0, cards.size());
    }

    @Test
    void unsuccessfulDeleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull() {
        cardRepository.deleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(notFoundId);
        List<Card> cards = cardRepository.findAll();
        assertEquals(1, cards.size());
    }

    @Test
    void unsuccessfulDeleteByIdAndUserScoresIsNotNullAndAnswerImagesIsNotNullAndQuestionImagesIsNotNull() {
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);
        imageRepository.save(Image.builder().associatedCard(card).build());
        imageRepository.save(Image.builder().associatedCard(card).build());
        scoreRepository.save(Score.builder().card(card).build());
        cardRepository.deleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(card.getId());
        List<Card> cards = cardRepository.findAll();
        assertEquals(1, cards.size());
    }

    @Test
    @Transactional
    void successfulDeleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull() {
        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .build()
        );
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);
        card.setCardGroup(cardGroup);
        cardRepository.save(card);
        cardRepository.deleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(cardGroup.getId());
        List<Card> cards = cardRepository.findAll();
        assertEquals(0, cards.size());
    }

    @Test
    void unsuccessfulDeleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull() {
        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .build()
        );
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);
        card.setCardGroup(cardGroup);
        cardRepository.save(card);
        cardRepository.deleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(notFoundId);
        List<Card> cards = cardRepository.findAll();
        assertEquals(1, cards.size());
    }

    @Test
    void unsuccessfulDeleteAllByCardGroupIdAndUserScoresIsNotNullAndAnswerImagesIsNotNullAndQuestionImagesIsNotNull() {
        Card card = cardRepository.findById(cardId).orElseThrow(EntityNotFoundException::new);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card);
        imageRepository.save(Image.builder().associatedCard(card).build());
        imageRepository.save(Image.builder().associatedCard(card).build());
        scoreRepository.save(Score.builder().card(card).build());
        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .cards(cards)
                .build()
        );
        cardRepository.deleteAllByCardGroupIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(cardGroup.getId());
        List<Card> cardsByFindAll = cardRepository.findAll();
        assertEquals(1, cardsByFindAll.size());
    }
}

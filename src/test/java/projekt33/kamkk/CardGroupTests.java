package projekt33.kamkk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.UserEntity;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ThemeRepository;
import projekt33.kamkk.repository.UserEntityRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardGroupTests {

    @Autowired
    private CardGroupRepository cardGroupRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private CardRepository cardRepository;

    private Long userId;
    private Long cardGroupId;
    private Long themeId;
    private final Long notFoundId = 9999L;

    @BeforeEach
    void init(){
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .name("Test Test")
                .email("test@test.com")
                .createdAt(Calendar.getInstance().getTime())
                .scores(null)
                .createdCards(null)
                .build()
        );
        userId = userEntity.getId();

        Theme theme = themeRepository.save(Theme.builder()
                .createdAt(Calendar.getInstance().getTime())
                .title("Theme Test")
                .build()
        );
        themeId = theme.getId();

        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .author(userEntity)
                .theme(theme)
                .build()
        );
        cardGroupId = cardGroup.getId();


    }

    @AfterEach
    void clean() {
        cardRepository.deleteAll();
        cardGroupRepository.deleteAll();
        themeRepository.deleteAll();
        userEntityRepository.deleteAll();
    }

    @Test
    void testCardGroup() {
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(1, cardGroups.size());
    }

    @Test
    void successfulFindById() {
        Optional<CardGroup> optionalCardGroup = cardGroupRepository.findById(cardGroupId);
        assertTrue(optionalCardGroup.isPresent());
    }

    @Test
    void unsuccessfulFindById() {
        Optional<CardGroup> optionalCardGroup = cardGroupRepository.findById(notFoundId);
        assertFalse(optionalCardGroup.isPresent());
    }

    @Test
    void successfulFindAllByAuthorId() {
        cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .author(userEntityRepository.findById(userId).orElseThrow(EntityNotFoundException::new))
                .theme(themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new))
                .build()
        );

        List<CardGroup> cardGroups = cardGroupRepository.findAllByAuthorId(userId);
        assertEquals(2, cardGroups.size());
    }

    @Test
    void unsuccessfulFindAllByAuthorId() {
        List<CardGroup> cardGroups = cardGroupRepository.findAllByAuthorId(notFoundId);
        assertEquals(   0, cardGroups.size());
    }

    @Test
    void successfulFindAllByThemeId() {
        cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .author(userEntityRepository.findById(userId).orElseThrow(EntityNotFoundException::new))
                .theme(themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new))
                .build()
        );

        List<CardGroup> cardGroups = cardGroupRepository.findAllByThemeId(themeId);
        assertEquals(2, cardGroups.size());
    }

    @Test
    void unsuccessfulFindAllByThemeId() {
        List<CardGroup> cardGroups = cardGroupRepository.findAllByThemeId(notFoundId);
        assertEquals(   0, cardGroups.size());
    }

    @Test
    @Transactional
    void successfulDeleteByIdAndCardsIsNull() {
        cardGroupRepository.deleteByIdAndCardsIsNull(cardGroupId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(0, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteByIdAndCardsIsNull() {
        cardGroupRepository.deleteByIdAndCardsIsNull(notFoundId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteByIdAndCardsIsNotNull() {
        cardRepository.save(Card.builder()
                .question("Will Test work?")
                .answer("I hope so.")
                .createdAt(Calendar.getInstance().getTime())
                .maxPoints(10)
                .cardGroup(cardGroupRepository.findById(cardGroupId).orElseThrow(EntityNotFoundException::new))
                .build()
        );
        cardGroupRepository.deleteByIdAndCardsIsNull(cardGroupId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

    @Test
    @Transactional
    void successfulDeleteAllByThemeIdAndCardsIsNull() {
        cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .author(userEntityRepository.findById(userId).orElseThrow(EntityNotFoundException::new))
                .theme(themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new))
                .build()
        );
        cardGroupRepository.deleteAllByThemeIdAndCardsIsNull(themeId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(0, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteAllByThemeIdAndCardsIsNull() {
        cardGroupRepository.deleteAllByThemeIdAndCardsIsNull(notFoundId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteAllByThemeIdAndCardsIsNotNull() {
        cardRepository.save(Card.builder()
                .question("Will Test work?")
                .answer("I hope so.")
                .createdAt(Calendar.getInstance().getTime())
                .maxPoints(10)
                .cardGroup(cardGroupRepository.findById(cardGroupId).orElseThrow(EntityNotFoundException::new))
                .build()
        );
        cardGroupRepository.deleteAllByThemeIdAndCardsIsNull(themeId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

    @Test
    @Transactional
    void successfulDeleteAllByAuthorIdAndCardsIsNull() {
        cardGroupRepository.save(CardGroup.builder()
                .creationDate(Calendar.getInstance().getTime())
                .author(userEntityRepository.findById(userId).orElseThrow(EntityNotFoundException::new))
                .theme(themeRepository.findById(themeId).orElseThrow(EntityNotFoundException::new))
                .build()
        );
        cardGroupRepository.deleteAllByAuthorIdAndCardsIsNull(userId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(0, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteAllByAuthorIdAndCardsIsNull() {
        cardGroupRepository.deleteAllByAuthorIdAndCardsIsNull(notFoundId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

    @Test
    void unsuccessfulDeleteAllByAuthorIdAndCardsIsNotNull() {
        cardRepository.save(Card.builder()
                .question("Will Test work?")
                .answer("I hope so.")
                .createdAt(Calendar.getInstance().getTime())
                .maxPoints(10)
                .cardGroup(cardGroupRepository.findById(cardGroupId).orElseThrow(EntityNotFoundException::new))
                .build()
        );
        cardGroupRepository.deleteAllByAuthorIdAndCardsIsNull(userId);
        List<CardGroup> cardGroups = cardGroupRepository.findAll();
        assertEquals(   1, cardGroups.size());
    }

}

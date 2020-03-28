package projekt33.kamkk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projekt33.kamkk.entity.*;
import projekt33.kamkk.repository.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class KamkkBackendApplicationTests {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private CardGroupRepository cardGroupRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ScoreRepository scoreRepository;

    @BeforeEach
    void init() {
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .name("Test Test")
                .email("test@test.com")
                .createdAt(Calendar.getInstance().getTime())
                .build()
        );

        Category category = categoryRepository.save(Category.builder()
                .title("Category Test")
                .build()
        );

        Theme theme = themeRepository.save(Theme.builder()
                .category(category)
                .createdAt(Calendar.getInstance().getTime())
                .title("Theme Test")
                .build()

        );

        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .author(userEntity)
                .creationDate(Calendar.getInstance().getTime())
                .theme(theme)
                .build()

        );

        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cards.add(Card.builder()
                    .question("Will Test" + i + " work?")
                    .answer("I hope so.")
                    .createdAt(Calendar.getInstance().getTime())
                    .maxPoints(10)
                    .cardGroup(cardGroup)
                    .build()
            );
        }
        cardRepository.saveAll(cards);
        System.out.println("dfwsdfas");

        List<Score> scores = new ArrayList<>();
        for (Card card : cards) {
            scores.add(Score.builder()
                    .user(userEntity)
                    .card(card)
                    .reviewedAt(Calendar.getInstance().getTime())
                    .submittedScore(11)
                    .revisionNumber(11)
                    .build()
            );
        }
        scoreRepository.saveAll(scores);

    }

    @Test
    void contextLoads() {
    }

    @Test
    void testUser() {
        List<UserEntity> userEntities = userEntityRepository.findAll();
        assertEquals(1, userEntities.size());
    }

    @AfterEach
    void clean() {
        scoreRepository.deleteAll();
        cardRepository.deleteAll();
        cardGroupRepository.deleteAll();
        themeRepository.deleteAll();
        categoryRepository.deleteAll();
        userEntityRepository.deleteAll();


    }

}

package projekt33.kamkk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Score;
import projekt33.kamkk.entity.UserEntity;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ScoreRepository;
import projekt33.kamkk.repository.UserEntityRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserEntityTests {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private CardGroupRepository cardGroupRepository;

    private Long userId;

    @BeforeEach
    void init() {
        UserEntity userEntity = userEntityRepository.save(UserEntity.builder()
                .name("Test Test")
                .email("test@test.com")
                .createdAt(Calendar.getInstance().getTime())
                .scores(null)
                .createdCards(null)
                .build()
        );

        userId = userEntity.getId();
    }

    @AfterEach
    void clean() {
        scoreRepository.deleteAll();
        cardGroupRepository.deleteAll();
        userEntityRepository.deleteAll();
    }

    @Test
    void testUser() {
        List<UserEntity> userEntities = userEntityRepository.findAll();
        assertEquals(1, userEntities.size());
    }

    @Test
    void successfulFindById() {
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(userId);
        assertTrue(optionalUserEntity.isPresent());
    }

    @Test
    void unsuccessfulFindById() {
        Long notFoundUserId = 9999L;
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(notFoundUserId);
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    @Transactional
    void successfulDeleteByIdAndScoresIsNullAndCreatedCardsIsNull() {
        userEntityRepository.deleteByIdAndScoresIsNullAndCreatedCardsIsNull(userId);
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findById(userId);
        assertFalse(optionalUserEntity.isPresent());
    }

    @Test
    @Transactional
    void unsuccessfulDeleteByIdAndScoresIsNullAndCreatedCardsIsNull() {
        UserEntity userEntity = userEntityRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        ArrayList<CardGroup> cardGroups = new ArrayList<>();
        CardGroup cardGroup = cardGroupRepository.save(CardGroup.builder()
                .author(userEntity)
                .creationDate(Calendar.getInstance().getTime())
                .build()

        );
        cardGroups.add(cardGroup);

        ArrayList<Score> scores = new ArrayList<>();
        Score score = scoreRepository.save(Score.builder()
                .user(userEntity)
                .reviewedAt(Calendar.getInstance().getTime())
                .submittedScore(11)
                .revisionNumber(11)
                .build()
        );
        scores.add(score);

        userEntity.setScores(scores);
        userEntity.setCreatedCards(cardGroups);
        userEntityRepository.save(userEntity);

        userEntityRepository.deleteByIdAndScoresIsNullAndCreatedCardsIsNull(userId);
        Optional<UserEntity> userEntities = userEntityRepository.findById(userId);
        assertTrue(userEntities.isPresent());
    }
}

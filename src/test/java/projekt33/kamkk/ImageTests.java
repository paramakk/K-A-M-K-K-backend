package projekt33.kamkk;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.Image;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ImageRepository;

@SpringBootTest
public class ImageTests {
  @Autowired
  private ImageRepository imageRepository;

  @Autowired
  private CardRepository cardRepository;

  private Long imageId;
  private Long cardId;
  private final Long notFoundId = 9999L;

  @BeforeEach
  void init() {
    Card card = cardRepository.save(
      Card
        .builder()
        .question("Test?")
        .answer("I hope so.")
        .createdAt(Calendar.getInstance().getTime())
        .maxPoints(10)
        .build()
    );
    cardId = card.getId();
    Image image = imageRepository.save(
      Image
        .builder()
        .name("image")
        .mimeType("mime")
        .extension("extension")
        .associatedCard(card)
        .build()
    );
    imageId = image.getId();
  }

  @AfterEach
  void clean() {
    imageRepository.deleteAll();
    cardRepository.deleteAll();
  }

  @Test
  void testImage() {
    List<Image> cards = imageRepository.findAll();
    assertEquals(1, cards.size());
  }

  @Test
  void successfulFindById() {
    Optional<Image> optionalImage = imageRepository.findById(imageId);
    assertTrue(optionalImage.isPresent());
  }

  @Test
  void unsuccessfulFindById() {
    Optional<Image> optionalImage = imageRepository.findById(notFoundId);
    assertFalse(optionalImage.isPresent());
  }

  @Test
  void successfulDeleteById() {
    imageRepository.deleteById(imageId);
    List<Image> images = imageRepository.findAll();
    assertEquals(0, images.size());
  }

  @Test
  void unsuccessfulDeleteById() {
    try {
      imageRepository.delete(
        imageRepository
          .findById(notFoundId)
          .orElseThrow(EntityNotFoundException::new)
      );
    } catch (EntityNotFoundException ex) {
      assertTrue(true);
    }
  }

  @Test
  void successfulFindAllByAssociatedCardId() {
    List<Image> images = imageRepository.findAllByAssociatedCardId(cardId);
    assertEquals(1, images.size());
  }

  @Test
  void unsuccessfulFindAllByAssociatedCardId() {
    List<Image> images = imageRepository.findAllByAssociatedCardId(notFoundId);
    assertEquals(0, images.size());
  }
}

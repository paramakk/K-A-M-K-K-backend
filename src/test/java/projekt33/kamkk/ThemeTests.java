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
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Category;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CategoryRepository;
import projekt33.kamkk.repository.ThemeRepository;

@SpringBootTest
public class ThemeTests {
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ThemeRepository themeRepository;

  @Autowired
  private CardGroupRepository cardGroupRepository;

  private Long categoryId;
  private Long themeId;
  private final Long notFoundId = 9999L;

  @BeforeEach
  void init() {
    Category category = categoryRepository.save(
      Category.builder().title("Category Test").build()
    );
    categoryId = category.getId();
    Theme theme = themeRepository.save(
      Theme
        .builder()
        .category(category)
        .createdAt(Calendar.getInstance().getTime())
        .title("Theme Test")
        .build()
    );
    themeId = theme.getId();
  }

  @AfterEach
  void clean() {
    cardGroupRepository.deleteAll();
    themeRepository.deleteAll();
    categoryRepository.deleteAll();
  }

  @Test
  void testTheme() {
    List<Theme> themes = themeRepository.findAll();
    assertEquals(1, themes.size());
  }

  @Test
  void successfulFindById() {
    Optional<Theme> optionalTheme = themeRepository.findById(themeId);
    assertTrue(optionalTheme.isPresent());
  }

  @Test
  void unsuccessfulFindById() {
    Optional<Theme> optionalTheme = themeRepository.findById(notFoundId);
    assertFalse(optionalTheme.isPresent());
  }

  @Test
  void successfulFindAllByCategoryId() {
    List<Theme> themes = themeRepository.findAllByCategoryId(categoryId);
    assertEquals(1, themes.size());
  }

  @Test
  void unsuccessfulFindAllByCategoryId() {
    List<Theme> themes = themeRepository.findAllByCategoryId(notFoundId);
    assertEquals(0, themes.size());
  }

  @Test
  @Transactional
  void successfulDeleteByIdAndCardGroupsIsNull() {
    themeRepository.deleteByIdAndCardGroupsIsNull(themeId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(0, themes.size());
  }

  @Test
  void unsuccessfulDeleteByIdAndCardGroupsIsNull() {
    themeRepository.deleteByIdAndCardGroupsIsNull(notFoundId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(1, themes.size());
  }

  @Test
  void unsuccessfulDeleteByIdAndCardGroupsIsNotNull() {
    cardGroupRepository.save(
      CardGroup
        .builder()
        .creationDate(Calendar.getInstance().getTime())
        .theme(
          themeRepository
            .findById(themeId)
            .orElseThrow(EntityNotFoundException::new)
        )
        .build()
    );
    themeRepository.deleteByIdAndCardGroupsIsNull(themeId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(1, themes.size());
  }

  @Test
  @Transactional
  void successfulDeleteAllByCategoryIdAndCardGroupsIsNull() {
    themeRepository.deleteAllByCategoryIdAndCardGroupsIsNull(categoryId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(0, themes.size());
  }

  @Test
  void unsuccessfulDeleteAllByCategoryIdAndCardGroupsIsNull() {
    themeRepository.deleteAllByCategoryIdAndCardGroupsIsNull(notFoundId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(1, themes.size());
  }

  @Test
  void unsuccessfulDeleteAllByCategoryIdAndCardGroupsIsNotNull() {
    cardGroupRepository.save(
      CardGroup
        .builder()
        .creationDate(Calendar.getInstance().getTime())
        .theme(
          themeRepository
            .findById(themeId)
            .orElseThrow(EntityNotFoundException::new)
        )
        .build()
    );
    themeRepository.deleteAllByCategoryIdAndCardGroupsIsNull(categoryId);
    List<Theme> themes = themeRepository.findAll();
    assertEquals(1, themes.size());
  }
}

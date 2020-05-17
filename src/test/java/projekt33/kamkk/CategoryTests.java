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
import projekt33.kamkk.entity.Category;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.exception.InvalidSecretException;
import projekt33.kamkk.repository.CategoryRepository;
import projekt33.kamkk.repository.ThemeRepository;

@SpringBootTest
public class CategoryTests {
  private final Long notFoundId = 9999L;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ThemeRepository themeRepository;

  private Long categoryId;

  @BeforeEach
  void init() {
    Category category = categoryRepository.save(
      Category.builder().title("Category Test").secret("secret").build()
    );
    categoryId = category.getId();
  }

  @AfterEach
  void clean() {
    themeRepository.deleteAll();
    categoryRepository.deleteAll();
  }

  @Test
  void testCategory() {
    List<Category> categories = categoryRepository.findAll();
    assertEquals(1, categories.size());
  }

  @Test
  void successfulFindById() {
    Optional<Category> optionalCategory = categoryRepository.findById(
      categoryId
    );
    assertTrue(optionalCategory.isPresent());
  }

  @Test
  void unsuccessfulFindById() {
    Optional<Category> optionalCategory = categoryRepository.findById(
      notFoundId
    );
    assertFalse(optionalCategory.isPresent());
  }

  @Test
  @Transactional
  void successfulDeleteByIdAndThemesIsNull() {
    categoryRepository.deleteByIdAndThemesIsNull(categoryId);
    List<Category> categories = categoryRepository.findAll();
    assertEquals(0, categories.size());
  }

  @Test
  void unsuccessfulDeleteByIdAndThemesIsNull() {
    categoryRepository.deleteByIdAndThemesIsNull(notFoundId);
    List<Category> categories = categoryRepository.findAll();
    assertEquals(1, categories.size());
  }

  @Test
  void unsuccessfulDeleteByIdAndThemesIsNotNull() {
    themeRepository.save(
      Theme
        .builder()
        .category(
          categoryRepository
            .findById(categoryId)
            .orElseThrow(EntityNotFoundException::new)
        )
        .createdAt(Calendar.getInstance().getTime())
        .title("Theme Test")
        .build()
    );
    categoryRepository.deleteByIdAndThemesIsNull(categoryId);
    List<Category> categories = categoryRepository.findAll();
    assertEquals(1, categories.size());
  }

  @Test
  void successfulUpdate() {
    CategoryDTO categoryDTO = CategoryDTO
      .builder()
      .title("Category Test")
      .secret("secret")
      .build();

    Category category = categoryRepository
      .findById(categoryId)
      .orElseThrow(EntityNotFoundException::new);
    try {
      secretCheck(categoryDTO, category);
      assertTrue(true);
    } catch (InvalidSecretException ex) {
      fail();
    }
  }

  @Test
  void unsuccessfulUpdate() {
    CategoryDTO categoryDTO = CategoryDTO
      .builder()
      .title("Category Test")
      .secret("fail")
      .build();

    Category category = categoryRepository
      .findById(categoryId)
      .orElseThrow(EntityNotFoundException::new);
    try {
      secretCheck(categoryDTO, category);
      fail();
    } catch (InvalidSecretException ex) {
      assertTrue(true);
    }
  }

  private void secretCheck(CategoryDTO categoryDTO, Category category) {
    if (!categoryDTO.getSecret().equals(category.getSecret())) {
      throw new InvalidSecretException();
    }
  }
}

package projekt33.kamkk.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Category;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.exception.EntityNotFoundException;
import projekt33.kamkk.exception.InvalidSecretException;
import projekt33.kamkk.repository.CategoryRepository;
import projekt33.kamkk.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;

  private ModelMapper modelMapper;

  Base64.Encoder encoder = Base64.getEncoder();

  public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {

    modelMapper.createTypeMap(Theme.class, ThemeDTO.class).addMappings(mapper->{
      mapper.skip(ThemeDTO::setCardGroups);
    });

    this.categoryRepository = categoryRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public CategoryDTO getById(Long id) {
    Category category = categoryRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(id));
    category.setSecret(null);
    return modelMapper.map(category, CategoryDTO.class);
  }

  @Override
  public CategoryDTO create(CategoryDTO entity) {
    if (entity.getSecret() == null) {
      throw new InvalidSecretException();
    }
    entity.setSecret(encoder.encodeToString(entity.getSecret().getBytes()));
    Category category = categoryRepository.save(
      modelMapper.map(entity, Category.class)
    );
    category.setSecret(null);
    return modelMapper.map(category, CategoryDTO.class);
  }

  @Override
  public CategoryDTO update(Long id, CategoryDTO entity) {
    Category category = categoryRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(id));
    if (entity.getSecret() == null) {
      throw new InvalidSecretException();
    }
    entity.setSecret(encoder.encodeToString(entity.getSecret().getBytes()));
    secretCheck(entity, category);
    entity.setId(id);
    categoryRepository.save(modelMapper.map(entity, Category.class));
    entity.setSecret(null);
    return modelMapper.map(entity, CategoryDTO.class);
  }

  @Override
  public void delete(Long id) {
    categoryRepository.delete(
      categoryRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id))
    );
  }

  private void secretCheck(CategoryDTO categoryDTO, Category category) {
    if (!categoryDTO.getSecret().equals(category.getSecret())) {
      throw new InvalidSecretException();
    }
  }

  @Override
  public List<CategoryDTO> findAll() {
    return categoryRepository.findAll().stream().map(ct -> modelMapper.map(ct, CategoryDTO.class)).collect(Collectors.toList());
  }
}

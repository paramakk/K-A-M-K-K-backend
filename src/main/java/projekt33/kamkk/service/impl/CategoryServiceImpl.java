package projekt33.kamkk.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Category;
import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.repository.CategoryRepository;
import projekt33.kamkk.service.CategoryService;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDTO getById(Long id) {
        return modelMapper.map(categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new), CategoryDTO.class);
    }

    @Override
    public CategoryDTO create(CategoryDTO entity) {
        return modelMapper.map(categoryRepository.save(modelMapper.map(entity, Category.class)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO entity) {
        entity.setId(id);
        return modelMapper.map(categoryRepository.save(modelMapper.map(entity, Category.class)), CategoryDTO.class);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }
}

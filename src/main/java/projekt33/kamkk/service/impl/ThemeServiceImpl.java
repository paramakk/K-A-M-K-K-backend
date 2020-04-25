package projekt33.kamkk.service.impl;

import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.repository.ThemeRepository;
import projekt33.kamkk.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {
  @Autowired
  ThemeRepository themeRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public ThemeDTO getById(Long id) {
    return modelMapper.map(
      themeRepository.findById(id).orElseThrow(EntityNotFoundException::new),
      ThemeDTO.class
    );
  }

  @Override
  public ThemeDTO create(ThemeDTO entity) {
    return modelMapper.map(
      themeRepository.save(modelMapper.map(entity, Theme.class)),
      ThemeDTO.class
    );
  }

  @Override
  public ThemeDTO update(Long id, ThemeDTO entity) {
    entity.setId(id);
    return modelMapper.map(
      themeRepository.save(modelMapper.map(entity, Theme.class)),
      ThemeDTO.class
    );
  }

  @Override
  public void delete(Long id) {
    themeRepository.delete(
      themeRepository.findById(id).orElseThrow(EntityNotFoundException::new)
    );
  }
}

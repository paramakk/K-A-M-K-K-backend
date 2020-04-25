package projekt33.kamkk.service.impl;

import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Image;
import projekt33.kamkk.entity.dto.ImageDTO;
import projekt33.kamkk.entity.dto.ImageDTO;
import projekt33.kamkk.repository.ImageRepository;
import projekt33.kamkk.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
  @Autowired
  ImageRepository imageRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public ImageDTO getById(Long id) {
    return modelMapper.map(
      imageRepository.findById(id).orElseThrow(EntityNotFoundException::new),
      ImageDTO.class
    );
  }

  @Override
  public ImageDTO create(ImageDTO entity) {
    return modelMapper.map(
      imageRepository.save(modelMapper.map(entity, Image.class)),
      ImageDTO.class
    );
  }

  @Override
  public ImageDTO update(Long id, ImageDTO entity) {
    entity.setId(id);
    return modelMapper.map(
      imageRepository.save(modelMapper.map(entity, Image.class)),
      ImageDTO.class
    );
  }

  @Override
  public void delete(Long id) {
    imageRepository.delete(
      imageRepository.findById(id).orElseThrow(EntityNotFoundException::new)
    );
  }
}

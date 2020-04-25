package projekt33.kamkk.service.impl;

import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Score;
import projekt33.kamkk.entity.dto.ScoreDTO;
import projekt33.kamkk.repository.ScoreRepository;
import projekt33.kamkk.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
  @Autowired
  ScoreRepository scoreRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public ScoreDTO getById(Long id) {
    return modelMapper.map(
      scoreRepository.findById(id).orElseThrow(EntityNotFoundException::new),
      ScoreDTO.class
    );
  }

  @Override
  public ScoreDTO create(ScoreDTO entity) {
    return modelMapper.map(
      scoreRepository.save(modelMapper.map(entity, Score.class)),
      ScoreDTO.class
    );
  }

  @Override
  public ScoreDTO update(Long id, ScoreDTO entity) {
    entity.setId(id);
    return modelMapper.map(
      scoreRepository.save(modelMapper.map(entity, Score.class)),
      ScoreDTO.class
    );
  }

  @Override
  public void delete(Long id) {
    scoreRepository.delete(
      scoreRepository.findById(id).orElseThrow(EntityNotFoundException::new)
    );
  }
}

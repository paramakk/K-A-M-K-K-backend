package projekt33.kamkk.service.impl;

import javax.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.service.CardService;

@Service
public class CardServiceImpl implements CardService {
  @Autowired
  CardRepository cardRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public CardDTO getById(Long id) {
    return modelMapper.map(
      cardRepository.findById(id).orElseThrow(EntityNotFoundException::new),
      CardDTO.class
    );
  }

  @Override
  public CardDTO create(CardDTO entity) {
    return modelMapper.map(
      cardRepository.save(modelMapper.map(entity, Card.class)),
      CardDTO.class
    );
  }

  @Override
  public CardDTO update(Long id, CardDTO entity) {
    entity.setId(id);
    return modelMapper.map(
      cardRepository.save(modelMapper.map(entity, Card.class)),
      CardDTO.class
    );
  }

  @Override
  public void delete(Long id) {
    cardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    cardRepository.deleteByIdAndUserScoresIsNullAndAnswerImagesIsNullAndQuestionImagesIsNull(
      id
    );
  }
}

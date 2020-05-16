package projekt33.kamkk.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.exception.EntityNotFoundException;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.service.CardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

  @Autowired
  CardRepository cardRepository;

  @Autowired
  CardGroupRepository cardGroupRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public CardDTO getById(Long id) {
    return modelMapper.map(
      cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)),
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
    cardRepository.delete(cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
  }

  @Override
  public List<CardDTO> findAllByAuthorIs(String author) {
    List<CardGroup> cardGroups = cardGroupRepository.findAllByAuthorIs(author);
    List<CardDTO> cardDTOS = new ArrayList<>();
    for(CardGroup cardGroup : cardGroups){
      List<Card> cards = cardRepository.findAllByCardGroupId(cardGroup.getId());
      for(Card card: cards){
        cardDTOS.add(modelMapper.map(card, CardDTO.class));
      }
    }
    return cardDTOS;
  }

  @Override
  public List<CardDTO> findAllByCardGroupId(Long id) {
    CardGroup cardGroup = cardGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    List<Card> cards = cardRepository.findAllByCardGroupId(cardGroup.getId());
    List<CardDTO> cardDTOS = new ArrayList<>();
    for(Card card: cards){
      cardDTOS.add(modelMapper.map(card, CardDTO.class));
    }
    return cardDTOS;
  }
}

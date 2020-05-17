package projekt33.kamkk.service.impl;

import java.util.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.exception.EntityNotFoundException;
import projekt33.kamkk.exception.InvalidSecretException;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.service.CardGroupService;

@Service
public class CardGroupServiceImpl implements CardGroupService {

  private CardGroupRepository cardGroupRepository;

  private ModelMapper modelMapper;

  @Autowired
  public CardGroupServiceImpl(
    CardGroupRepository cardGroupRepository,
    ModelMapper modelMapper
  ) {
    this.cardGroupRepository = cardGroupRepository;
    this.modelMapper = modelMapper;

  }

  @Override
  public CardGroupDTO getById(Long id) {
    CardGroup cardGroup = cardGroupRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(id));
    cardGroup.setSecret(null);
    return modelMapper.map(cardGroup, CardGroupDTO.class);
  }

  @Override
  public CardGroupDTO create(CardGroupDTO entity) {
    entity.setSecret(Base64.getEncoder().encodeToString(entity.getSecret().getBytes()));
    CardGroup cardGroup = cardGroupRepository.save(
      modelMapper.map(entity, CardGroup.class)
    );
    cardGroup.setSecret(null);
    return modelMapper.map(cardGroup, CardGroupDTO.class);
  }

  @Override
  public CardGroupDTO update(Long id, CardGroupDTO entity) {
    CardGroup cardGroup = cardGroupRepository
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundException(id));
    entity.setSecret(Base64.getEncoder().encodeToString(entity.getSecret().getBytes()));
    secretCheck(entity, cardGroup);
    entity.setId(id);
    cardGroupRepository.save(modelMapper.map(entity, CardGroup.class));
    entity.setSecret(null);
    return modelMapper.map(entity, CardGroupDTO.class);
  }

  @Override
  public void delete(Long id) {
    cardGroupRepository.delete(
      cardGroupRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException(id))
    );
  }

  private void secretCheck(CardGroupDTO cardGroupDTO, CardGroup cardGroup) {
    if (!cardGroupDTO.getSecret().equals(cardGroup.getSecret())) {
      throw new InvalidSecretException();
    }
  }
}

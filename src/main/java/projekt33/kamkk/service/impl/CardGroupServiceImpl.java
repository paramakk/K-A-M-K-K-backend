package projekt33.kamkk.service.impl;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.service.CardGroupService;

@Service
public class CardGroupServiceImpl implements CardGroupService {

    private CardGroupRepository cardGroupRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CardGroupServiceImpl(CardGroupRepository cardGroupRepository, ModelMapper modelMapper) {

        this.cardGroupRepository = cardGroupRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public CardGroupDTO getById(Long id) {
        return modelMapper.map(
                cardGroupRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new),
                CardGroupDTO.class
        );
    }

    @Override
    public CardGroupDTO create(CardGroupDTO entity) {
        return modelMapper.map(
                cardGroupRepository.save(modelMapper.map(entity, CardGroup.class)),
                CardGroupDTO.class
        );
    }

    @Override
    public CardGroupDTO update(Long id, CardGroupDTO entity) {
        entity.setId(id);
        return modelMapper.map(
                cardGroupRepository.save(modelMapper.map(entity, CardGroup.class)),
                CardGroupDTO.class
        );
    }

    @Override
    public void delete(Long id) {
        cardGroupRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        cardGroupRepository.deleteByIdAndCardsIsNull(id);
    }
}

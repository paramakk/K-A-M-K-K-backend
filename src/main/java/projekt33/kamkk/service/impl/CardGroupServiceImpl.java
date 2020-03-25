package projekt33.kamkk.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.service.CardGroupService;

import javax.persistence.EntityNotFoundException;

@Service
public class CardGroupServiceImpl implements CardGroupService {

    @Autowired
    CardGroupRepository cardGroupRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CardGroupDTO getById(Long id) {
        return modelMapper.map(cardGroupRepository.findById(id).orElseThrow(EntityNotFoundException::new), CardGroupDTO.class);
    }

    @Override
    public CardGroupDTO create(CardGroupDTO entity) {
        return modelMapper.map(cardGroupRepository.save(modelMapper.map(entity, CardGroup.class)), CardGroupDTO.class);
    }

    @Override
    public CardGroupDTO update(Long id, CardGroupDTO entity) {
        entity.setId(id);
        return modelMapper.map(cardGroupRepository.save(modelMapper.map(entity, CardGroup.class)), CardGroupDTO.class);
    }

    @Override
    public void delete(Long id) {
        cardGroupRepository.delete(cardGroupRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }
}

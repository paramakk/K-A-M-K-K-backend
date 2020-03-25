package projekt33.kamkk.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.UserEntity;
import projekt33.kamkk.entity.dto.UserEntityDTO;
import projekt33.kamkk.entity.dto.UserEntityDTO;
import projekt33.kamkk.repository.UserEntityRepository;
import projekt33.kamkk.service.UserEntityService;

import javax.persistence.EntityNotFoundException;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    
    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserEntityDTO getById(Long id) {
        return modelMapper.map(userEntityRepository.findById(id).orElseThrow(EntityNotFoundException::new), UserEntityDTO.class);
    }

    @Override
    public UserEntityDTO create(UserEntityDTO entity) {
        return modelMapper.map(userEntityRepository.save(modelMapper.map(entity, UserEntity.class)), UserEntityDTO.class);
    }

    @Override
    public UserEntityDTO update(Long id, UserEntityDTO entity) {
        entity.setId(id);
        return modelMapper.map(userEntityRepository.save(modelMapper.map(entity, UserEntity.class)), UserEntityDTO.class);
    }

    @Override
    public void delete(Long id) {
        userEntityRepository.delete(userEntityRepository.findById(id).orElseThrow(EntityNotFoundException::new));

    }
}

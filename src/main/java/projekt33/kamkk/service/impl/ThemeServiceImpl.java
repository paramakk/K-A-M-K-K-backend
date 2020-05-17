package projekt33.kamkk.service.impl;

import java.util.Base64;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Category;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.exception.EntityNotFoundException;
import projekt33.kamkk.exception.InvalidSecretException;
import projekt33.kamkk.repository.ThemeRepository;
import projekt33.kamkk.service.ThemeService;

@Service
public class ThemeServiceImpl implements ThemeService {

    private ThemeRepository themeRepository;

    private ModelMapper modelMapper;

    Base64.Encoder encoder = Base64.getEncoder();

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository, ModelMapper modelMapper) {

        modelMapper.createTypeMap(CardGroup.class, CardGroupDTO.class).addMappings(mapper ->{
            mapper.skip(CardGroupDTO::setCards);
        });


        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ThemeDTO getById(Long id) {
        return modelMapper.map(
                themeRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(id)),
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
        Theme theme = themeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        entity.setSecret(encoder.encodeToString(entity.getSecret().getBytes()));
        secretCheck(entity, theme);
        entity.setId(id);
        return modelMapper.map(
                themeRepository.save(modelMapper.map(entity, Theme.class)),
                ThemeDTO.class
        );
    }

    @Override
    public void delete(Long id) {
        themeRepository.delete(
                themeRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(id))
        );
    }

    private void secretCheck(ThemeDTO themeDTO, Theme theme) {
        if (theme.getCategory() != null) {
            if (!themeDTO.getSecret().equals(theme.getCategory().getSecret())) {
                throw new InvalidSecretException();
            }
        }
    }
}

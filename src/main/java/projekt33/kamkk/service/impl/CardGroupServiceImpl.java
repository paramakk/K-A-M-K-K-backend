package projekt33.kamkk.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.exception.EntityNotFoundException;
import projekt33.kamkk.exception.InvalidSecretException;
import projekt33.kamkk.repository.CardGroupRepository;
import projekt33.kamkk.repository.CardRepository;
import projekt33.kamkk.repository.ThemeRepository;
import projekt33.kamkk.service.CardGroupService;

import javax.transaction.Transactional;

@Service
public class CardGroupServiceImpl implements CardGroupService {
    Base64.Encoder encoder = Base64.getEncoder();

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardGroupRepository cardGroupRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
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
    @Transactional
    public CardGroupDTO create(CardGroupDTO entity) {
        if (entity.getSecret() == null) {
            throw new InvalidSecretException();
        }
        entity.setCreationDate(new Date());
        entity.setSecret(encoder.encodeToString(entity.getSecret().getBytes()));


        CardGroup cardGroup = cardGroupRepository.save(
                CardGroup.builder()
                    .cards(entity.getCards().stream().map(cardDTO -> modelMapper.map(cardDTO, Card.class)).collect(Collectors.toList()))
                    .views(entity.getViews())
                    .creationDate(entity.getCreationDate())
                    .theme(themeRepository.findById(entity.getTheme().getId()).orElseThrow(() -> new EntityNotFoundException(entity.getTheme().getId())))
                    .author(entity.getAuthor())
                    .name(entity.getName())
                    .secret(entity.getSecret())
                .build()
        );
        cardRepository.saveAll(cardGroup.getCards().stream().peek(card -> card.setCardGroup(cardGroup)).collect(Collectors.toList()));
        cardGroup.setSecret(null);
        return modelMapper.map(cardGroup, CardGroupDTO.class);
    }

    @Override
    public CardGroupDTO update(Long id, CardGroupDTO entity) {
        CardGroup cardGroup = cardGroupRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        if (entity.getSecret() == null) {
            throw new InvalidSecretException();
        }
        entity.setSecret(encoder.encodeToString(entity.getSecret().getBytes()));
        secretCheck(entity, cardGroup);
        entity.setId(id);
        cardGroupRepository.save(modelMapper.map(entity, CardGroup.class));
        entity.setSecret(null);
        return modelMapper.map(entity, CardGroupDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CardGroup cardGroup = cardGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        cardGroup.getCards().forEach(card -> cardRepository.deleteById(card.getId()));
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

    @Override
    public CardGroupDTO saveAllCards(Long id, List<CardDTO> cardDTOS, String secret) {
        CardGroup cardGroup = cardGroupRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        if (!cardGroup.getSecret().equals(secret)) {
            throw new InvalidSecretException();
        }
        List<Card> cards = cardDTOS.stream().map(cardDTO -> modelMapper.map(cardDTO, Card.class)).collect(Collectors.toList());
        cardRepository.saveAll(cards);
        cardGroup.setCards(cards);
        return modelMapper.map(cardGroupRepository.save(cardGroup), CardGroupDTO.class);
    }

}

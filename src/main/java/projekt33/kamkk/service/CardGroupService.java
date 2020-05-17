package projekt33.kamkk.service;

import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.service.base.CrudService;

import java.util.List;

public interface CardGroupService extends CrudService<Long, CardGroupDTO> {

    CardGroupDTO saveAllCards(Long id, List<CardDTO> cardDTOS, String secret);
}

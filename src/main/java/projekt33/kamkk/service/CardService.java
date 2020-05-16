package projekt33.kamkk.service;

import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.service.base.CrudService;

import java.util.List;

public interface CardService extends CrudService<Long, CardDTO> {

    List<CardDTO> findAllByAuthorIs(String author);
    List<CardDTO> findAllByCardGroupId(Long id);
}

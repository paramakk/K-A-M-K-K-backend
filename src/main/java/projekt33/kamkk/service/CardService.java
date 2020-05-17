package projekt33.kamkk.service;

import java.util.List;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.service.base.CrudService;

public interface CardService extends CrudService<Long, CardDTO> {
  List<CardDTO> findAllByAuthorIs(String author);
  List<CardDTO> findAllByCardGroupId(Long id);
}

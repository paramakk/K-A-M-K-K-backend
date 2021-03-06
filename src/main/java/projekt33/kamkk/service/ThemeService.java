package projekt33.kamkk.service;

import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.service.base.CrudService;

import java.util.List;

public interface ThemeService extends CrudService<Long, ThemeDTO> {

    List<ThemeDTO> suggestedThemes();

}

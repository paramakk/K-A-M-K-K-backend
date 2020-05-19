package projekt33.kamkk.service;

import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.service.base.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<Long, CategoryDTO> {

    List<CategoryDTO> findAll();

}

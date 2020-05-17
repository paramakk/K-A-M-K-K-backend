package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.CategoryDTO;
import projekt33.kamkk.service.CategoryService;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController extends CrudController<Long, CategoryDTO> {

  @Autowired
  public CategoryController(CategoryService categoryService) {
    super(categoryService);
  }

  @Override
  @RequestMapping(
    value = "{id}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
    return super.getById(id);
  }

  @Override
  @RequestMapping(
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
    return super.create(dto);
  }

  @Override
  @RequestMapping(
    value = "{id}",
    method = RequestMethod.PUT,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CategoryDTO> update(
    @PathVariable Long id,
    @RequestBody CategoryDTO dto
  ) {
    return super.update(id, dto);
  }

  @Override
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return super.delete(id);
  }
}

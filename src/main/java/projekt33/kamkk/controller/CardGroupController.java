package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.entity.dto.CardGroupDTO;
import projekt33.kamkk.service.CardGroupService;

import java.util.List;

@RestController
@RequestMapping("api/v1/card-groups")
public class CardGroupController extends CrudController<Long, CardGroupDTO> {

  CardGroupService cardGroupService;

  @Autowired
  public CardGroupController(CardGroupService cardGroupService) {
    super(cardGroupService);
    this.cardGroupService = cardGroupService;
  }

  @Override
  @RequestMapping(
    value = "{id}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CardGroupDTO> getById(@PathVariable Long id) {
    return super.getById(id);
  }

  @Override
  @RequestMapping(
    method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CardGroupDTO> create(@RequestBody CardGroupDTO dto) {
    return super.create(dto);
  }

  @Override
  @RequestMapping(
    value = "{id}",
    method = RequestMethod.PUT,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CardGroupDTO> update(
    @PathVariable Long id,
    @RequestBody CardGroupDTO dto
  ) {
    return super.update(id, dto);
  }

  @Override
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    return super.delete(id);
  }

  @RequestMapping(
          value = "{id}/save-all",
          method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<CardGroupDTO> saveAllCards(@PathVariable Long id , @RequestBody List<CardDTO> dtos) {
    return new ResponseEntity<>(cardGroupService.saveAllCards(id,dtos), HttpStatus.OK);
  }
}

package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.CardDTO;
import projekt33.kamkk.service.CardService;

import java.util.List;

@RestController
@RequestMapping("api/v1/cards")
public class CardController extends CrudController<Long, CardDTO> {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        super(cardService);
        this.cardService = cardService;
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> create(@RequestBody CardDTO dto) {
        return super.create(dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDTO> update(@PathVariable Long id, @RequestBody CardDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> findAllByUserName(@PathVariable String author) {
        return new ResponseEntity<>(cardService.findAllByAuthorIs(author), HttpStatus.OK);
    }

    @RequestMapping(value = "/card-group/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDTO>> findAllByCardGroupId(@PathVariable Long id) {
        return new ResponseEntity<>(cardService.findAllByCardGroupId(id), HttpStatus.OK);
    }


}

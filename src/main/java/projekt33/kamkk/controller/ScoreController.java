package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.ScoreDTO;
import projekt33.kamkk.service.ScoreService;

@RestController
@RequestMapping("api/v1/scores")
public class ScoreController extends CrudController<Long, ScoreDTO> {

    @Autowired
    public ScoreController(ScoreService scoreService) {
        super(scoreService);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDTO> create(@RequestBody ScoreDTO dto) {
        return super.create(dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoreDTO> update(@PathVariable Long id, @RequestBody ScoreDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}

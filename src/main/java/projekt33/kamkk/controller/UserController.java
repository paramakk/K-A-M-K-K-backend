package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.UserEntityDTO;
import projekt33.kamkk.service.UserEntityService;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends CrudController<Long, UserEntityDTO> {

    @Autowired
    public UserController(UserEntityService userEntityService) {
        super(userEntityService);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntityDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntityDTO> create(@RequestBody UserEntityDTO dto) {
        return super.create(dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntityDTO> update(@PathVariable Long id, @RequestBody UserEntityDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}

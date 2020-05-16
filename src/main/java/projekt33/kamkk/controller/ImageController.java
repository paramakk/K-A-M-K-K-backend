package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.ImageDTO;
import projekt33.kamkk.service.ImageService;

@RestController
@RequestMapping("api/v1/images")
public class ImageController extends CrudController<Long, ImageDTO> {

    @Autowired
    public ImageController(ImageService imageService) {
        super(imageService);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDTO> create(@RequestBody ImageDTO dto) {
        return super.create(dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDTO> update(@PathVariable Long id, @RequestBody ImageDTO dto) {
        return super.update(id, dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}

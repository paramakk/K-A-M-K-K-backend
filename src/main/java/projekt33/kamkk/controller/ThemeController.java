package projekt33.kamkk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projekt33.kamkk.controller.base.CrudController;
import projekt33.kamkk.entity.dto.ThemeDTO;
import projekt33.kamkk.service.ThemeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/themes")
@CrossOrigin("http://localhost:3000")
public class ThemeController extends CrudController<Long, ThemeDTO> {

    private ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        super(themeService);
        this.themeService = themeService;
    }

    @Override
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ThemeDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ThemeDTO> create(@RequestBody ThemeDTO dto) {
        return super.create(dto);
    }

    @Override
    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ThemeDTO> update(
            @PathVariable Long id,
            @RequestBody ThemeDTO dto
    ) {
        return super.update(id, dto);
    }

    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }


    @GetMapping(value = "/suggestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ThemeDTO>> suggestedThemes() {
        return new ResponseEntity<>(themeService.suggestedThemes(), HttpStatus.OK);
    }


}

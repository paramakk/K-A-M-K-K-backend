package projekt33.kamkk.controller.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projekt33.kamkk.service.base.CrudService;

public class CrudController<IdType, DTOType> {

    protected CrudService<IdType, DTOType> crudService;

    public CrudController(CrudService<IdType, DTOType> crudService) {
        this.crudService = crudService;
    }


    public ResponseEntity<DTOType> getById(IdType id) {
        DTOType responseDTO = crudService.getById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    public ResponseEntity<DTOType> create(DTOType dto) {
        DTOType responseDTO = crudService.create(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    public ResponseEntity<DTOType> update(IdType id, DTOType dto) {
        DTOType responseDTO = crudService.update(id, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    public ResponseEntity<Void> delete(IdType id) {
        crudService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

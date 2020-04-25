package projekt33.kamkk.service.base;

public interface CrudService<IdType, DTOType> {
  DTOType getById(IdType id);

  DTOType create(DTOType entity);

  DTOType update(IdType id, DTOType entity);

  void delete(IdType id);
}

package projekt33.kamkk.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
  List<Image> findAllByAssociatedCardId(Long id);
}

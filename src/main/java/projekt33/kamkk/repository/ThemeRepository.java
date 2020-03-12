package projekt33.kamkk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projekt33.kamkk.entity.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    List<Theme> findAllByCategoryId(Long id);

    void deleteByIdAndCardGroupsIsNull(Long id);
    void deleteAllByCategoryIdAndCardGroupsIsNull(Long id);


}

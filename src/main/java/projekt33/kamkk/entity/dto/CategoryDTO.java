package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.Data;
import projekt33.kamkk.entity.Theme;

@Data
public class CategoryDTO {
  private Long id;

  private String title;

  @JsonManagedReference("category-themes")
  private List<Theme> themes;
}

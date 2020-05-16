package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
  private Long id;

  private String title;

  private String secret;

  @JsonManagedReference("category-themes")
  private List<ThemeDTO> themes;
}

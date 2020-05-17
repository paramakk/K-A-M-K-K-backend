package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDTO {
  private Long id;

  private String title;

  private String secret;

  @JsonManagedReference("category-themes")
  private List<ThemeDTO> themes;
}

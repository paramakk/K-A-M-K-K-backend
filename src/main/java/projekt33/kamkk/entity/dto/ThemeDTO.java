package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ThemeDTO {
  private Long id;

  private String title;

  private String secret;

  @JsonBackReference("category-themes")
  private CategoryDTO category;

  @JsonManagedReference("theme-cardgroups")
  private List<CardGroupDTO> cardGroups;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  )
  private Date createdAt;
}

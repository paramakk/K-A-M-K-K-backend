package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.List;
import lombok.Data;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Category;

@Data
public class ThemeDTO {
  private Long id;

  private String title;

  private Category category;

  private List<CardGroup> cardGroups;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  )
  private Date createdAt;
}

package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import projekt33.kamkk.entity.Card;

@Data
public class ImageDTO {
  private Long id;

  private String name;

  private String extension;

  private String mimeType;

  @JsonBackReference("card-images")
  private Card associatedCard;
}

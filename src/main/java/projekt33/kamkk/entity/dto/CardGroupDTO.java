package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardGroupDTO {
  private Long id;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  )
  private Date creationDate;

  private String author;

  private String secret;

  @JsonBackReference("theme-cardgroups")
  private ThemeDTO theme;

  private int views;

  @JsonManagedReference("cardgroup-cards")
  private List<CardDTO> cards;
}

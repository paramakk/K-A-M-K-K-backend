package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Score;

@Data
@NoArgsConstructor
public class UserEntityDTO {
  private Long id;

  private String name;

  private String email;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  )
  private Date createdAt;

  @JsonManagedReference("user-cards")
  @ElementCollection(fetch = FetchType.LAZY)
  private List<CardGroup> createdCards;

  @JsonManagedReference("user-scores")
  @ElementCollection(fetch = FetchType.LAZY)
  private List<Score> scores;
}

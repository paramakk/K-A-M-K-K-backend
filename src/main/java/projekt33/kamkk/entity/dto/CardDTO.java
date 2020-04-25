package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import lombok.Data;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Image;
import projekt33.kamkk.entity.Score;

@Data
public class CardDTO {
  private Long id;

  private String question;

  private CardGroup cardGroup;

  private String answer;

  private int maxPoints;

  @JsonFormat(
    shape = JsonFormat.Shape.STRING,
    pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
  )
  private Date createdAt;

  @JsonManagedReference("image-questions")
  private List<Image> questionImages;

  @JsonManagedReference("image-answers")
  private List<Image> answerImages;

  @JsonManagedReference("image-scores")
  private List<Score> userScores;
}

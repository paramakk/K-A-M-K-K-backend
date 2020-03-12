package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.UserEntity;

import java.util.Date;

@Data
public class ScoreDTO {

    private Long id;

    @JsonBackReference("user-scores")
    private UserEntity user;

    @JsonBackReference("card-scores")
    private Card card;

    private int submittedScore;

    private int revisionNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date reviewedAt;

}

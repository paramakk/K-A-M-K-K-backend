package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import projekt33.kamkk.entity.CardGroup;
import projekt33.kamkk.entity.Score;

import java.util.Date;
import java.util.List;

@Data
public class UserEntityDTO {

    private Long id;

    private String name;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date createdAt;

    @JsonManagedReference("user-cards")
    private List<CardGroup> createdCards;

    @JsonManagedReference("user-scores")
    private List<Score> scores;
}

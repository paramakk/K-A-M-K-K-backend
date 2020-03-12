package projekt33.kamkk.entity.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import projekt33.kamkk.entity.Card;
import projekt33.kamkk.entity.Theme;
import projekt33.kamkk.entity.UserEntity;

import java.util.Date;
import java.util.List;

@Data
public class CardGroupDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date creationDate;

    @JsonBackReference("user-cardgroups")
    private UserEntity author;

    private Theme theme;

    private int views;

    private List<Card> cards;

}

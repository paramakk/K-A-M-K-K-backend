package projekt33.kamkk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {


    @Id
    @GeneratedValue
    private Long id;

    private String question;

    @ManyToOne
    private CardGroup cardGroup;

    private String answer;

    private int maxPoints;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "associatedCard")
    private List<Image> questionImages;

    @OneToMany(mappedBy = "associatedCard")
    private List<Image> answerImages;

    @OneToMany(mappedBy = "card")
    private List<Score> userScores;

}

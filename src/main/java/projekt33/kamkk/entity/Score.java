package projekt33.kamkk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Card card;

    private int submittedScore;

    private int revisionNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewedAt;

}

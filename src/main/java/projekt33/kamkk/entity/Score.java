package projekt33.kamkk.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

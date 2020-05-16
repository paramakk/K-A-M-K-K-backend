package projekt33.kamkk.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
  
}

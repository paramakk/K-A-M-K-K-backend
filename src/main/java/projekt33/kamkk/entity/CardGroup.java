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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardGroup {
  @Id
  @GeneratedValue
  private Long id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  private String author;

  @ManyToOne
  private Theme theme;

  private int views;

  @OneToMany(mappedBy = "cardGroup")
  private List<Card> cards;
}

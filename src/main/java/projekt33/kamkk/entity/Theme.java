package projekt33.kamkk.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theme {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @ManyToOne
  private Category category;

  @OneToMany(mappedBy = "theme")
  private List<CardGroup> cardGroups;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;
}

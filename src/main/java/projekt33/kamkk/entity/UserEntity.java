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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String password;

  private String email;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @OneToMany(mappedBy = "author")
  private List<CardGroup> createdCards;

  @OneToMany(mappedBy = "user")
  private List<Score> scores;
}

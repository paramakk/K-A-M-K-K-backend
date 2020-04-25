package projekt33.kamkk.entity;

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
public class Category {
  @Id
  @GeneratedValue
  private Long id;

  private String title;

  @OneToMany(mappedBy = "category")
  private List<Theme> themes;
}

package projekt33.kamkk.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image { //TODO will be modified if we get to the point of using this class
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String extension;

  private String mimeType;

  @ManyToOne
  private Card associatedCard;
}

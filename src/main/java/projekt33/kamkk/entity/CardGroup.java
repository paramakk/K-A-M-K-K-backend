package projekt33.kamkk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private Theme theme;

    private int views;

    @OneToMany(mappedBy = "cardGroup")
    private List<Card> cards;



}

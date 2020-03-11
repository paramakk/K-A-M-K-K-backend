package projekt33.kamkk.entity;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

package lk.iuhs.crm.entity.cartentity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartid;
    private Integer menid;
    private Integer cusid;
    private String type;
}

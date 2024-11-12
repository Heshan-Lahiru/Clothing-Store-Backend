package lk.iuhs.crm.entity.kidclothentity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "KidCloths")
public class KidClothEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer kidid;
    private String name;
    private String type;
    private String image;
    private String price;
    private String qty;
    private String description;

    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isactive;
}

package lk.iuhs.crm.entity.menclothentity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "MenCloths")
public class MenClothEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Integer menid;
    private String name;
    private String type;
    private String image;
    private String price;
    private String qty;
    private String description;

    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isactive;
}

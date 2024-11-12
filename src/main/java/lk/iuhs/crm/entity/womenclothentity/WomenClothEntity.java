package lk.iuhs.crm.entity.womenclothentity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "WomenCloths")
public class WomenClothEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer womenid;
    private String name;
    private String type;
    private String image;
    private String price;
    private String qty;
    private String description;

    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private boolean isactive;
}

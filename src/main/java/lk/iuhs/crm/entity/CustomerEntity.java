package lk.iuhs.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name = "customer_register_details_table")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cus_id;
    private String name;
    private String email;
    private String image;
    private String gender;
    private String password;
    private String address;
    private String mobilenumber;
}

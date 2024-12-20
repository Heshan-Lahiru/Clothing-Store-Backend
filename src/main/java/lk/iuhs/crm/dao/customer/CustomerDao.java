package lk.iuhs.crm.dao.customer;

import lombok.Data;

@Data
public class CustomerDao {

    private Integer cus_id;
    private String name;
    private String email;
    private String image;
    private String gender;
    private String password;
    private String address;
    private String mobilenumber;

    private String token;
    private String role;
}

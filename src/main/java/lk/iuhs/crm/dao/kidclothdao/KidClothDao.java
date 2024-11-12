package lk.iuhs.crm.dao.kidclothdao;

import lombok.Data;

@Data
public class KidClothDao {

    private Integer kidid;
    private String name;
    private String type;
    private String image;
    private String price;
    private String qty;
    private String description;
    private boolean isactive;
    private String gendertype;
    private Integer cartsid;
}

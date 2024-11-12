package lk.iuhs.crm.dao.womenclothdao;

import lombok.Data;

@Data
public class WomenClothDao {

    private Integer womenid;
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

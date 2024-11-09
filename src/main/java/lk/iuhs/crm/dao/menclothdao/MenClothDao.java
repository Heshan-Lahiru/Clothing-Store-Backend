package lk.iuhs.crm.dao.menclothdao;

import lombok.Data;

@Data
public class MenClothDao {

    private String name;
    private String type;
    private String image;
    private String price;
    private String qty;
    private String description;
    private boolean isactive;
}

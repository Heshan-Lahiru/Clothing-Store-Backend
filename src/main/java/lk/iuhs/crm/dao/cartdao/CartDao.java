package lk.iuhs.crm.dao.cartdao;

import lombok.Data;

@Data
public class CartDao {
    private Integer cartid;
    private Integer menid;
    private Integer cusid;
    private String type;
}

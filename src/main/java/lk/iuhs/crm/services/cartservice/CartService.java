package lk.iuhs.crm.services.cartservice;

import lk.iuhs.crm.dao.cartdao.CartDao;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;

import java.util.List;

public interface CartService {
    CartDao addtocart(CartDao cartDao);

    List<Object> getcartdata(Integer id);

    void cartdelete(Integer cartid);
}

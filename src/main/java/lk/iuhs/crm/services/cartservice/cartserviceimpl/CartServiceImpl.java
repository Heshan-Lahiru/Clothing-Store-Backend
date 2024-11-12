package lk.iuhs.crm.services.cartservice.cartserviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.cartdao.CartDao;
import lk.iuhs.crm.dao.kidclothdao.KidClothDao;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.dao.womenclothdao.WomenClothDao;
import lk.iuhs.crm.dto.cartdto.CartDto;
import lk.iuhs.crm.dto.kidclothdto.KidClothDto;
import lk.iuhs.crm.dto.menclothdto.MenClothDto;
import lk.iuhs.crm.dto.womenclothdto.WomenClothDto;
import lk.iuhs.crm.entity.cartentity.CartEntity;
import lk.iuhs.crm.entity.kidclothentity.KidClothEntity;
import lk.iuhs.crm.entity.menclothentity.MenClothEntity;
import lk.iuhs.crm.entity.womenclothentity.WomenClothEntity;
import lk.iuhs.crm.services.cartservice.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final MenClothDto menClothDto;
    private final WomenClothDto womenClothDto;
    private final KidClothDto kidClothDto;
    private final CartDto cartDto;
    private final ObjectMapper objectMapper;

    @Override
    public CartDao addtocart(CartDao cartDao) {
        CartEntity cartEntity =objectMapper.convertValue(cartDao, CartEntity.class);
           cartDto.save(cartEntity);
           return objectMapper.convertValue(cartEntity,CartDao.class);
    }

    @Override
    public List<Object> getcartdata(Integer id) {
        Iterable<CartEntity> byCusId = cartDto.findByCusid(id);
        List<Object> cartCloths = new ArrayList<>();

        byCusId.forEach(cartEntity -> {
            if (cartEntity.getType().equalsIgnoreCase("men")) {
                Optional<MenClothEntity> menClothEntity = menClothDto.findById(cartEntity.getMenid());
                menClothEntity.ifPresent(entity -> {
                    MenClothDao menClothDao = objectMapper.convertValue(entity, MenClothDao.class);
                    menClothDao.setGendertype("men");
                    menClothDao.setCartsid(cartEntity.getCartid());
                    cartCloths.add(menClothDao);
                });
            } else if (cartEntity.getType().equalsIgnoreCase("women")) {
                Optional<WomenClothEntity> womenClothEntity = womenClothDto.findById(cartEntity.getMenid());
                womenClothEntity.ifPresent(entity -> {
                    WomenClothDao womenClothDao = objectMapper.convertValue(entity, WomenClothDao.class);
                    womenClothDao.setGendertype("women");
                    womenClothDao.setCartsid(cartEntity.getCartid());
                    cartCloths.add(womenClothDao);
                });
            } else if (cartEntity.getType().equalsIgnoreCase("child")) {
                Optional<KidClothEntity> kidClothEntity = kidClothDto.findById(cartEntity.getMenid());
                kidClothEntity.ifPresent(entity -> {
                    KidClothDao kidClothDao = objectMapper.convertValue(entity, KidClothDao.class);
                    kidClothDao.setGendertype("child");
                    kidClothDao.setCartsid(cartEntity.getCartid());
                    cartCloths.add(kidClothDao);
                });
            }
        });
            return cartCloths;
            }

    @Override
    public void cartdelete(Integer cartid) {
        cartDto.deleteById(cartid);
    }
}
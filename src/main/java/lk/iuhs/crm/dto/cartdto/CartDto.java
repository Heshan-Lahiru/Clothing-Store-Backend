package lk.iuhs.crm.dto.cartdto;

import lk.iuhs.crm.entity.cartentity.CartEntity;
import lk.iuhs.crm.entity.kidclothentity.KidClothEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartDto extends CrudRepository<CartEntity,Integer> {

    Iterable<CartEntity> findByCusid(Integer cusid);

}

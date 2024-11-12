package lk.iuhs.crm.dto.menclothdto;

import lk.iuhs.crm.entity.menclothentity.MenClothEntity;
import org.springframework.data.repository.CrudRepository;



public interface MenClothDto extends CrudRepository<MenClothEntity,Integer> {

    Iterable<MenClothEntity> findByType(String type);
}

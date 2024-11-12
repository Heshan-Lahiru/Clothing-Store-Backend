package lk.iuhs.crm.dto.womenclothdto;

import lk.iuhs.crm.entity.menclothentity.MenClothEntity;
import lk.iuhs.crm.entity.womenclothentity.WomenClothEntity;
import org.springframework.data.repository.CrudRepository;

public interface WomenClothDto extends CrudRepository<WomenClothEntity,Integer> {

    Iterable<WomenClothEntity> findByType(String type);
}

package lk.iuhs.crm.dto.kidclothdto;
import lk.iuhs.crm.entity.kidclothentity.KidClothEntity;
import org.springframework.data.repository.CrudRepository;

public interface KidClothDto extends CrudRepository<KidClothEntity,Integer> {
    Iterable<KidClothEntity> findByType(String type);
}

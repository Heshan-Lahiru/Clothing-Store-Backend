package lk.iuhs.crm.dto.customerdto;


import lk.iuhs.crm.entity.customerentity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerDto extends CrudRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByEmail(String email);

}

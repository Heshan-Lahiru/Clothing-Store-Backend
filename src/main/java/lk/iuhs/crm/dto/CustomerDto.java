package lk.iuhs.crm.dto;


import lk.iuhs.crm.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerDto extends CrudRepository<CustomerEntity, Integer> {

    Optional<CustomerEntity> findByEmail(String email);
}

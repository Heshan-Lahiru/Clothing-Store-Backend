package lk.iuhs.crm.dto;


import lk.iuhs.crm.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDto extends CrudRepository<CustomerEntity, Integer> {
}

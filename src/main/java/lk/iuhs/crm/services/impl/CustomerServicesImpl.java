package lk.iuhs.crm.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.login.LoginDao;
import lk.iuhs.crm.dto.CustomerDto;
import lk.iuhs.crm.entity.CustomerEntity; // Ensure this is imported
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";

    private final CustomerDto customerDto;
    private final ObjectMapper objectMapper;

    @Override
    public CustomerDao customerRegisterAction(CustomerDao customerDao, MultipartFile multipartFile) throws IOException {

        CustomerEntity customerEntity1 = objectMapper.convertValue(customerDao, CustomerEntity.class);

       if(customerDto.findByEmail(customerEntity1.getEmail()).isPresent()){throw  new CustomerException("Error: Email does not exist.");}

        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_DIRECTORY, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, multipartFile.getBytes());
            customerEntity1.setImage(fileName);
        }

        customerDto.save(customerEntity1);
        return objectMapper.convertValue(customerDao, CustomerDao.class);

    }

    @Override
    public CustomerDao logincheck(LoginDao loginDao) {
        Optional<CustomerEntity> byEmail = customerDto.findByEmail(loginDao.getEmail());
        if(byEmail.isEmpty()){throw new CustomerException("customer not found");}
        CustomerEntity customerEntity = byEmail.get();

        return  objectMapper.convertValue(customerEntity, CustomerDao.class);
    }
}

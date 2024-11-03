package lk.iuhs.crm.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.CustomerDao;
import lk.iuhs.crm.dto.CustomerDto;
import lk.iuhs.crm.entity.CustomerEntity; // Ensure this is imported
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CustomerServicesImpl implements CustomerServices {
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";

    @Autowired
    private CustomerDto customerDto;

    @Autowired
    ObjectMapper objectMapper;

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
        CustomerDao customerDao1 = objectMapper.convertValue(customerDao, CustomerDao.class);
        return customerDao1;
    }
}

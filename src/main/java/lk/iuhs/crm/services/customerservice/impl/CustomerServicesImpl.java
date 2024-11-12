package lk.iuhs.crm.services.customerservice.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.iuhs.crm.Component.JwtComponent;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.kidclothdao.KidClothDao;
import lk.iuhs.crm.dao.login.LoginDao;
import lk.iuhs.crm.dto.customerdto.CustomerDto;
import lk.iuhs.crm.entity.customerentity.CustomerEntity;
import lk.iuhs.crm.entity.kidclothentity.KidClothEntity;
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.customerservice.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {
    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/users";

    private final CustomerDto customerDto;
    private final ObjectMapper objectMapper;
    private final JwtComponent jwtProperties;

    @Override
    public CustomerDao customerRegisterAction(CustomerDao customerDao, MultipartFile multipartFile) throws IOException {
        CustomerEntity customerEntity1 = objectMapper.convertValue(customerDao, CustomerEntity.class);

        if (customerDto.findByEmail(customerEntity1.getEmail()).isPresent()) {
            throw new CustomerException("Error: Email already exists.");
        }

        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_DIRECTORY, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, multipartFile.getBytes());
            customerEntity1.setImage(fileName);
        }

        customerDto.save(customerEntity1);
        return objectMapper.convertValue(customerEntity1, CustomerDao.class);
    }

    @Override
    public CustomerDao logincheck(LoginDao loginDao) throws JsonProcessingException {
        Optional<CustomerEntity> byEmail = customerDto.findByEmail(loginDao.getEmail());
        if (byEmail.isEmpty()) {
            throw new CustomerException("Customer not found");
        }

        CustomerEntity customerEntity = byEmail.get();

        if (customerEntity.getPassword().equalsIgnoreCase(loginDao.getMypassword())) {
            String token = generateToken(customerEntity);
            CustomerDao customerDao = objectMapper.convertValue(customerEntity, CustomerDao.class);
            customerDao.setToken(token);
            if(loginDao.getMypassword().equalsIgnoreCase("ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270")){customerDao.setRole("admin");}
            else{ customerDao.setRole("user");}
            return customerDao;
        }

        throw new CustomerException("Password is incorrect");
    }

    @Override
    public List<CustomerDao> getcustomers() {
        Iterable<CustomerEntity> all = customerDto.findAll();
        List<CustomerDao> customerDaoList = new ArrayList<>();
        all.forEach(customerEntity -> {

            if(!customerEntity.getEmail().equalsIgnoreCase("admin1234@gmail.com")){
                customerDaoList.add(objectMapper.convertValue(customerEntity, CustomerDao.class));

            }

        });
        return customerDaoList;
    }


    private String generateToken(CustomerEntity customerEntity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> customerData = objectMapper.convertValue(customerEntity, Map.class);

        return Jwts.builder()
                .setClaims(customerData)
                .setSubject(customerEntity.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }


}

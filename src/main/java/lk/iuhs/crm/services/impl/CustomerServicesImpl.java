package lk.iuhs.crm.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.iuhs.crm.Component.JwtComponent;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.login.LoginDao;
import lk.iuhs.crm.dto.CustomerDto;
import lk.iuhs.crm.entity.CustomerEntity;
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerServices {
    private static final String IMAGE_DIRECTORY = "src/main/resources/images";

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
        return objectMapper.convertValue(customerDao, CustomerDao.class);
    }

    @Override
    public CustomerDao logincheck(LoginDao loginDao) {
        Optional<CustomerEntity> byEmail = customerDto.findByEmail(loginDao.getEmail());
        if (byEmail.isEmpty()) {
            throw new CustomerException("Customer not found");
        }

        CustomerEntity customerEntity = byEmail.get();

        if (customerEntity.getPassword().equalsIgnoreCase(loginDao.getMypassword())) {
            String token = generateToken(customerEntity.getEmail());
            CustomerDao customerDao = objectMapper.convertValue(customerEntity, CustomerDao.class);
            customerDao.setToken(token);
            if(loginDao.getMypassword().equalsIgnoreCase("ac9689e2272427085e35b9d3e3e8bed88cb3434828b43b86fc0596cad4c6e270")){customerDao.setRole("admin");}
            else{ customerDao.setRole("user");}
            return customerDao;
        }

        throw new CustomerException("Password is incorrect");
    }

    private String generateToken(String email) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
}

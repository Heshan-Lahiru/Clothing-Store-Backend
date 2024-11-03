package lk.iuhs.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.CustomerDao;
import lk.iuhs.crm.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/register")
    public CustomerDao customerRegisterAction(@RequestPart("customerDao") String customerDaoString,
                                              @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return customerServices.customerRegisterAction(objectMapper.readValue(customerDaoString, CustomerDao.class), multipartFile);
    }
}
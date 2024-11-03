package lk.iuhs.crm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.CustomerDao;
import lk.iuhs.crm.services.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CustomerController {

  private final  CustomerServices customerServices;
  private final ObjectMapper objectMapper;

    @PostMapping("/register")
    public CustomerDao customerRegisterAction(@RequestPart("customerDao") String customerDaoString,
                                              @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return customerServices.customerRegisterAction(objectMapper.readValue(customerDaoString, CustomerDao.class), multipartFile);
    }
}
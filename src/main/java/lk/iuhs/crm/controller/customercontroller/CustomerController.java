package lk.iuhs.crm.controller.customercontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.login.LoginDao;
import lk.iuhs.crm.services.customerservice.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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

    @PostMapping("/login")
  public CustomerDao customerLoginAction(@RequestBody LoginDao loginDao) throws JsonProcessingException {
           return customerServices.logincheck(loginDao);
    }

    @PostMapping("/checkadmin")
    public boolean data(@RequestBody Map<String, String> emailData) {
        String email = emailData.get("email");
        if(email != null && email.equalsIgnoreCase("admin1234@gmail.com")) {
            return true;
        }
        return false;
    }

}


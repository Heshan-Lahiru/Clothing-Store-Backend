package lk.iuhs.crm.services.customerservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.login.LoginDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerServices {
    CustomerDao customerRegisterAction(CustomerDao customerDao, MultipartFile multipartFile) throws IOException;

    CustomerDao logincheck(LoginDao loginDao) throws JsonProcessingException;
}

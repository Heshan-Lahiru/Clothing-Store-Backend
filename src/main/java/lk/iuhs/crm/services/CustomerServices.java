package lk.iuhs.crm.services;

import lk.iuhs.crm.dao.CustomerDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CustomerServices {
    public CustomerDao customerRegisterAction(CustomerDao customerDao, MultipartFile multipartFile) throws IOException;

}

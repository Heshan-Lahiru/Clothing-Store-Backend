package lk.iuhs.crm.services.menclothservice;

import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MenClothService {
    MenClothDao menclothadd(MenClothDao menClothDao, MultipartFile multipartFile) throws IOException;

     List<MenClothDao> getmencloths(String type);

    List<MenClothDao> getadminmencloths();

    void deletemencloth(Integer menid);


}

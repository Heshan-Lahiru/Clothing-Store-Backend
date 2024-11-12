package lk.iuhs.crm.services.kidclothservice;

import lk.iuhs.crm.dao.kidclothdao.KidClothDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface KidClothService {
    KidClothDao kidclothadd(KidClothDao kidClothDao, MultipartFile multipartFile) throws IOException;

    List<KidClothDao> getkidcloths(String type);

    List<KidClothDao> getadminkidcloths();

    void deletekidcloth(Integer kidid);
}

package lk.iuhs.crm.services.womenclothservice;

import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.dao.womenclothdao.WomenClothDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface WomenClothService {
    WomenClothDao womenclothadd(WomenClothDao womenClothDao, MultipartFile multipartFile) throws IOException;

    List<WomenClothDao> getwomencloths(String type);

    List<WomenClothDao> getadminwomencloths();

    void deletewomencloth(Integer womenid);
}

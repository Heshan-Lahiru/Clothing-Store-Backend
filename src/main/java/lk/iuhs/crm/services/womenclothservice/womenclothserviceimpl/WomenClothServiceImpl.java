package lk.iuhs.crm.services.womenclothservice.womenclothserviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.dao.womenclothdao.WomenClothDao;
import lk.iuhs.crm.dto.womenclothdto.WomenClothDto;
import lk.iuhs.crm.entity.menclothentity.MenClothEntity;
import lk.iuhs.crm.entity.womenclothentity.WomenClothEntity;
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.womenclothservice.WomenClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WomenClothServiceImpl implements WomenClothService {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/women";
    private final WomenClothDto womenClothDto;
    private final ObjectMapper objectMapper;

    @Override
    public WomenClothDao womenclothadd(WomenClothDao womenClothDao, MultipartFile multipartFile) throws IOException {
        WomenClothEntity womenClothEntity = objectMapper.convertValue(womenClothDao, WomenClothEntity.class);
        if(!multipartFile.isEmpty()){
            String originalFilename = multipartFile.getOriginalFilename();
            Path path = Paths.get(IMAGE_DIRECTORY, originalFilename);
            Files.createDirectories(path.getParent());
            Files.write(path, multipartFile.getBytes());
            womenClothEntity.setImage(originalFilename);
        }
        womenClothDto.save(womenClothEntity);
        return objectMapper.convertValue(womenClothEntity,WomenClothDao.class);

    }

    @Override
    public List<WomenClothDao> getwomencloths(String type) {
        Iterable<WomenClothEntity> all = womenClothDto.findByType(type);
        List<WomenClothDao> clothlistwomen = new ArrayList<>();
        all.forEach(womenClothEntity -> {
            clothlistwomen.add(objectMapper.convertValue(womenClothEntity,WomenClothDao.class));
        });
        return clothlistwomen;
    }

    @Override
    public List<WomenClothDao> getadminwomencloths() {
        Iterable<WomenClothEntity> all = womenClothDto.findAll();
        List<WomenClothDao> womenClothDaoList = new ArrayList<>();

        all.forEach(womenClothEntity -> {
            womenClothDaoList.add(objectMapper.convertValue(womenClothEntity,WomenClothDao.class));
        });
        return womenClothDaoList;
    }

    @Override
    public void deletewomencloth(Integer womenid) {
        Optional<WomenClothEntity> byId = womenClothDto.findById(womenid);
        if(byId.isPresent()) {
            womenClothDto.delete(byId.get());
        }
        else {
            throw new CustomerException("error for delete cloths");
        }
    }
}

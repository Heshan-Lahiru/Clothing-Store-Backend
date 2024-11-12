package lk.iuhs.crm.services.kidclothservice.kidclothimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.kidclothdao.KidClothDao;
import lk.iuhs.crm.dao.womenclothdao.WomenClothDao;
import lk.iuhs.crm.dto.kidclothdto.KidClothDto;
import lk.iuhs.crm.entity.kidclothentity.KidClothEntity;
import lk.iuhs.crm.entity.womenclothentity.WomenClothEntity;
import lk.iuhs.crm.exception.CustomerException;
import lk.iuhs.crm.services.kidclothservice.KidClothService;
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
public class KidClothImpl implements KidClothService {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/child";
    private final KidClothDto kidClothDto;
    private final ObjectMapper objectMapper;

    @Override
    public KidClothDao kidclothadd(KidClothDao kidClothDao, MultipartFile multipartFile) throws IOException {
        KidClothEntity kidClothEntity = objectMapper.convertValue(kidClothDao, KidClothEntity.class);
        if(!multipartFile.isEmpty()){
            String originalFilename = multipartFile.getOriginalFilename();
            Path path = Paths.get(IMAGE_DIRECTORY, originalFilename);
            Files.createDirectories(path.getParent());
            Files.write(path, multipartFile.getBytes());
            kidClothEntity.setImage(originalFilename);
        }
        kidClothDto.save(kidClothEntity);
        return objectMapper.convertValue(kidClothEntity, KidClothDao.class);
    }

    @Override
    public List<KidClothDao> getkidcloths(String type) {
        Iterable<KidClothEntity> all = kidClothDto.findByType(type);
        List<KidClothDao> clothlistkid = new ArrayList<>();
        all.forEach(kidClothEntity -> {
            clothlistkid.add(objectMapper.convertValue(kidClothEntity,KidClothDao.class));
        });
        return clothlistkid;
    }

    @Override
    public List<KidClothDao> getadminkidcloths() {
        Iterable<KidClothEntity> all = kidClothDto.findAll();
        List<KidClothDao> kidClothDaoList = new ArrayList<>();
        all.forEach(kidClothEntity -> {
            kidClothDaoList.add(objectMapper.convertValue(kidClothEntity,KidClothDao.class));
        });
        return kidClothDaoList;
    }

    @Override
    public void deletekidcloth(Integer kidid) {
        Optional<KidClothEntity> byId = kidClothDto.findById(kidid);
        if(byId.isPresent()) {
            kidClothDto.delete(byId.get());
        }
        else {
            throw new CustomerException("error for delete cloths");
        }
    }
}

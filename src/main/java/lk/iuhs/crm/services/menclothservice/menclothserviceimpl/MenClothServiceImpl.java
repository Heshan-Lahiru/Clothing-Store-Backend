package lk.iuhs.crm.services.menclothservice.menclothserviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.dto.menclothdto.MenClothDto;
import lk.iuhs.crm.entity.menclothentity.MenClothEntity;
import lk.iuhs.crm.services.menclothservice.MenClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenClothServiceImpl implements MenClothService {

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/men";
    private final MenClothDto menClothDto;
    private final ObjectMapper objectMapper;

    @Override
    public MenClothDao menclothadd(MenClothDao menClothDao, MultipartFile multipartFile) throws IOException {
        MenClothEntity menClothEntity = objectMapper.convertValue(menClothDao, MenClothEntity.class);

        if (!multipartFile.isEmpty()) {
            String fileName = multipartFile.getOriginalFilename();
            Path filePath = Paths.get(IMAGE_DIRECTORY, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, multipartFile.getBytes());
            menClothEntity.setImage(fileName);
        }
        menClothDto.save(menClothEntity);
        return objectMapper.convertValue(menClothEntity,MenClothDao.class);
    }

    @Override
    public List<MenClothDao> getmencloths() {
        Iterable<MenClothEntity> all = menClothDto.findAll();
        List<MenClothDao> clothlistmen = new ArrayList<>();
        all.forEach(menClothEntity -> {
            clothlistmen.add(objectMapper.convertValue(menClothEntity,MenClothDao.class));
        });
        return clothlistmen;
    }
}

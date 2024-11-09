package lk.iuhs.crm.controller.clothcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.customer.CustomerDao;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.services.menclothservice.MenClothService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ClothController {

    private final MenClothService menClothService;
    private final ObjectMapper objectMapper;

    @PostMapping("/menclothadd")
    public MenClothDao menclothadd(@RequestPart("menClothDao") String menClothDaoString,
                                              @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return menClothService.menclothadd(objectMapper.readValue(menClothDaoString, MenClothDao.class), multipartFile);
    }

    @GetMapping("/getmencloths")
    public List<MenClothDao> getmencloths (){
        return menClothService.getmencloths();
    }

}

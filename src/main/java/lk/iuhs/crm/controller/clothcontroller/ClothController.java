package lk.iuhs.crm.controller.clothcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.iuhs.crm.dao.cartdao.CartDao;
import lk.iuhs.crm.dao.kidclothdao.KidClothDao;
import lk.iuhs.crm.dao.menclothdao.MenClothDao;
import lk.iuhs.crm.dao.womenclothdao.WomenClothDao;
import lk.iuhs.crm.services.cartservice.CartService;
import lk.iuhs.crm.services.kidclothservice.KidClothService;
import lk.iuhs.crm.services.menclothservice.MenClothService;
import lk.iuhs.crm.services.womenclothservice.WomenClothService;
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
    private final WomenClothService womenClothService;
    private final KidClothService kidClothService;
    private final ObjectMapper objectMapper;
    private final CartService cartService;

    @PostMapping("/menclothadd")
    public MenClothDao menclothadd(@RequestPart("ClothDao") String menClothDaoString,
                                              @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return menClothService.menclothadd(objectMapper.readValue(menClothDaoString, MenClothDao.class), multipartFile);
    }

    @PostMapping("/womenclothadd")
    public WomenClothDao womenclothadd(@RequestPart("ClothDao") String womenClothDaoString,
                                   @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return womenClothService.womenclothadd(objectMapper.readValue(womenClothDaoString, WomenClothDao.class), multipartFile);
    }

    @PostMapping("/kidclothadd")
    public KidClothDao kidclothadd(@RequestPart("ClothDao") String kidClothDaoString,
                                   @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        return kidClothService.kidclothadd(objectMapper.readValue(kidClothDaoString, KidClothDao.class), multipartFile);
    }


    @GetMapping("/getmencloths/{type}")
    public List<MenClothDao> getmencloths (@PathVariable String type){
        return menClothService.getmencloths(type);
    }
    @GetMapping("/getwomencloths/{type}")
    public List<WomenClothDao> getwomencloths (@PathVariable String type){
        return womenClothService.getwomencloths(type);
    }
    @GetMapping("/getkidcloths/{type}")
    public List<KidClothDao> getkidcloths (@PathVariable String type){
        return kidClothService.getkidcloths(type);
    }

    @PostMapping("/addtocart")
   public CartDao addtocart(@RequestBody CartDao cartDao){

        return cartService.addtocart(cartDao);
   }

   @GetMapping("/getcartdata")
   public List<Object> getcartdata(@RequestParam Integer id){
        return cartService.getcartdata(id);
   }

   @DeleteMapping("/cartdelete/{cartid}")
    public void cartdelete(@PathVariable Integer cartid){
        cartService.cartdelete(cartid);
   }


   @GetMapping("/getadminmencloths")
    public List<MenClothDao> getadminmencloths(){
        return menClothService.getadminmencloths();
   }

    @GetMapping("/getadminwomencloths")
    public List<WomenClothDao> getadminwomencloths(){
        return womenClothService.getadminwomencloths();
    }

    @GetMapping("/getadminkidcloths")
    public List<KidClothDao> getadminkidcloths(){
        return kidClothService.getadminkidcloths();
    }

    @DeleteMapping("/deletemencloth/{menid}")
    public void deletemencloth(@PathVariable Integer menid){
        menClothService.deletemencloth(menid);
    }

    @DeleteMapping("/deletewomencloth/{womenid}")
    public void deletewomencloth(@PathVariable Integer womenid){
        womenClothService.deletewomencloth(womenid);
    }
    @DeleteMapping("/deletekidcloth/{kidid}")
    public void deletekidcloth(@PathVariable Integer kidid){
        kidClothService.deletekidcloth(kidid);
    }


}

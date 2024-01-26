package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.RegionDTO;
import com.example.kun_uz_lesson1.enums.AppLanguage;
import com.example.kun_uz_lesson1.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    //4. Region
    //    1. Create  (ADMIN)
    //        (order_number,name_uz, name_ru, name_en)
    //    2. Update by id (ADMIN)
    //        (key,name_uz, name_ru, name_en)
    //    3. Delete by id (ADMIN)
    //    4. Get List (ADMIN)
    //        (id,key,name_uz, name_ru, name_en,visible,created_date)
    //    5. Get By Lang (Language keladi shu language dagi name larini berib yuboramiz)
    //        (id,key,name) (name ga tegishli name_.. dagi qiymat qo'yiladi.) (visible true)
    @PostMapping("")
    public ResponseEntity<RegionDTO> creat(@RequestBody RegionDTO regionDTO, @RequestHeader("Authorization") String jwt) {
        return ResponseEntity.ok(regionService.create(regionDTO,jwt));
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<RegionDTO> updateById(@PathVariable("id") Integer id,
                                                @RequestBody RegionDTO regionDTO,
                                                @RequestHeader("Authorization") String jwt) {
        return ResponseEntity.ok(regionService.update(id, regionDTO,jwt));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(regionService.delete(id,jwt));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> all(@RequestHeader("Authorization")String jwt) {
        return ResponseEntity.ok(regionService.all(jwt));
    }

    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang(@RequestParam(value = "language", defaultValue = "uz") AppLanguage language) {
        return ResponseEntity.ok(regionService.getByLang(language));
    }
}

package com.example.kun_uz_lesson1.controller;

import com.example.kun_uz_lesson1.dto.RegionDTO;
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
    public ResponseEntity<RegionDTO> creat(@RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.create(regionDTO));
    }
    @PutMapping("/updateById")
    public ResponseEntity<RegionDTO> creat(@RequestParam("id") Integer id,
                                                @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.update(id,regionDTO));
    }
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> delete(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(regionService.delete(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> all() {
        return ResponseEntity.ok(regionService.all());
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<RegionDTO>> getByLang( @RequestParam("language") String language) {
        return ResponseEntity.ok(regionService.getByLang(language));
    }
}

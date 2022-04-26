package com.exadel.hotel.floor.controller;

import com.exadel.hotel.floor.dto.FloorDto;
import com.exadel.hotel.floor.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping("/floor")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @GetMapping("/getAll")
    public ResponseEntity<Iterator<FloorDto>> getAll() {
        return ResponseEntity.ok(floorService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FloorDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.get(id));
    }


    @GetMapping("/getByHotel/{id}")
    public ResponseEntity<Iterator<FloorDto>> getByHotel(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.getAllByHotelId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<FloorDto> add(@RequestBody FloorDto floorDto) {
        return ResponseEntity.ok(floorService.add(floorDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FloorDto> update(@PathVariable("id") Long id, @RequestBody FloorDto floorDto) {
        return ResponseEntity.ok(floorService.update(id, floorDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        floorService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        floorService.deleteAll();
    }


}

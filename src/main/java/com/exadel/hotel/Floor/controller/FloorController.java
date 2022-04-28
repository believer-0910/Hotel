package com.exadel.hotel.Floor.controller;

import com.exadel.hotel.Floor.dto.FloorDto;
import com.exadel.hotel.Floor.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @GetMapping("/getAll")
    public ResponseEntity<List<FloorDto>> getAll() {
        return ResponseEntity.ok(floorService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FloorDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.get(id));
    }


    @GetMapping("/getByHotel/{id}")
    public ResponseEntity<List<FloorDto>> getByHotel(@PathVariable("id") Long id) {
        return ResponseEntity.ok(floorService.getAllByHotelId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<FloorDto> add(@RequestBody FloorDto floorDto) {
        return new ResponseEntity<>(floorService.add(floorDto), HttpStatus.CREATED);
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

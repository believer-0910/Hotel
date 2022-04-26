package com.exadel.hotel.hotel.controller;

import com.exadel.hotel.hotel.dto.HotelDto;
import com.exadel.hotel.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PutMapping("/add")
    public ResponseEntity<HotelDto> add(@RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.add(hotelDto));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<HotelDto> update(@PathVariable("id") Long id, @RequestBody HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.update(id, hotelDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(hotelService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<HotelDto>> getAll() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        hotelService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        hotelService.deleteAll();
    }
}

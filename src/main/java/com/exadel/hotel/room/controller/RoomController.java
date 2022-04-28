package com.exadel.hotel.room.controller;

import com.exadel.hotel.room.dto.RoomDto;
import com.exadel.hotel.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roomService.get(id));
    }

    @GetMapping("/getByFloorId/{floorId}")
    public ResponseEntity<List<RoomDto>> getRoomsByFloorId(@PathVariable("floorId") Long floorId) {
        return ResponseEntity.ok(roomService.getByFloorId(floorId));
    }

    @PostMapping("/add")
    public ResponseEntity<RoomDto> add(@RequestBody RoomDto roomDto) {
        return new ResponseEntity<>(roomService.add(roomDto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RoomDto> update(@PathVariable("id") Long id, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        roomService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        roomService.deleteAll();
    }
}

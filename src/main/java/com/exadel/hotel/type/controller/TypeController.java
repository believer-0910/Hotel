package com.exadel.hotel.type.controller;

import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<TypeDto>> getAll() {
        return ResponseEntity.ok(typeService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TypeDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(typeService.get(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TypeDto> add(@RequestBody TypeDto typeDto) {
        return ResponseEntity.ok(typeService.add(typeDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TypeDto> update(@PathVariable("id") Long id, @RequestBody TypeDto typeDto) {
        return ResponseEntity.ok(typeService.update(id, typeDto));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        typeService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        typeService.deleteAll();
    }
}

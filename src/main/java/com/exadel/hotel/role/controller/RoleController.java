package com.exadel.hotel.role.controller;

import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PutMapping("/add")
    public ResponseEntity<RoleDto> add(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.add(roleDto));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(roleService.get(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<RoleDto>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        roleService.delete(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        roleService.deleteAll();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<RoleDto> update(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.update(id, roleDto));
    }

}
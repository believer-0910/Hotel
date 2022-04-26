package com.exadel.hotel.role.service;

import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.role.entity.Role;
import com.exadel.hotel.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public RoleDto add(RoleDto roleDto) {
        Role role = roleRepository.save(modelMapper.map(roleDto, Role.class));
        return modelMapper.map(role, RoleDto.class);
    }

    public RoleDto get(Long id) {
        return modelMapper.map(roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")), RoleDto.class);
    }

    public RoleDto update(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        modelMapper.map(roleDto, role);
        return modelMapper.map(roleRepository.save(role), RoleDto.class);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    public Iterable<RoleDto> getAll() {
        return modelMapper.map(roleRepository.findAll(), new TypeToken<Iterable<RoleDto>>() {}.getType());
    }

    public void deleteAll() {
        roleRepository.deleteAll();
    }

    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName);
    }

}

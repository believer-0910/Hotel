package com.exadel.hotel.user.service;

import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.role.entity.Role;
import com.exadel.hotel.role.service.RoleService;
import com.exadel.hotel.user.dto.UserDto;
import com.exadel.hotel.user.entity.User;
import com.exadel.hotel.user.ropository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final RoleService roleService;

    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setRole(getRole(userDto.getRoleDto().getName()));
        userRepository.save(user);
        UserDto userDto1 = modelMapper.map(user, UserDto.class);
        userDto1.setRoleDto(modelMapper.map(user.getRole(), RoleDto.class));
        return userDto1;
    }

    public UserDto getUser(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")), UserDto.class);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        modelMapper.map(userDto, user);
        user.setRole(getRole(userDto.getRoleDto().getName()));
        userRepository.save(user);
        UserDto userDto1 = modelMapper.map(user, UserDto.class);
        userDto1.setRoleDto(modelMapper.map(user.getRole(), RoleDto.class));
        return userDto1;
    }

    private Role getRole(String roleName) {
        return roleService.getRole(roleName);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<UserDto> getAllUsers() {
        return modelMapper.map(userRepository.findAll(), new TypeToken<Iterable<UserDto>>() {}.getType());
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public Iterable<UserDto> getAllUsersByName(String name) {
        return modelMapper.map(userRepository.findAllByFirstName(name), new TypeToken<Iterable<UserDto>>() {}.getType());
    }
}

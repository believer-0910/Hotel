package com.exadel.hotel.user.dto;

import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private RoleDto roleDto;
}

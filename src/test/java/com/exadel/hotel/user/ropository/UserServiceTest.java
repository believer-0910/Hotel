package com.exadel.hotel.user.ropository;

import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.user.dto.UserDto;
import com.exadel.hotel.user.entity.User;
import com.exadel.hotel.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, modelMapper);
    }

    @Test
    void addUser() {
        RoleDto roleDto = new RoleDto("ROLE_USER");
        UserDto userDto = new UserDto("A", "F", "D", roleDto);

        userService.addUser(userDto);

        User user = modelMapper.map(userDto, User.class);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void getUser() {

        RoleDto roleDto = new RoleDto("ROLE_USER");
        UserDto userDto = new UserDto("A", "F", "D", roleDto);

        User user = modelMapper.map(userDto, User.class);

        userService.getUser(1L);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).findById(1L);

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }
}

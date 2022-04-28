package com.exadel.hotel.config;

import com.exadel.hotel.Floor.dto.FloorDto;
import com.exadel.hotel.Floor.entity.Floor;
import com.exadel.hotel.hotel.dto.HotelDto;
import com.exadel.hotel.hotel.repository.HotelRepository;
import com.exadel.hotel.role.dto.RoleDto;
import com.exadel.hotel.role.repository.RoleRepository;
import com.exadel.hotel.room.dto.RoomDto;
import com.exadel.hotel.room.entity.Room;
import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.repository.TypeRepository;
import com.exadel.hotel.user.dto.UserDto;
import com.exadel.hotel.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final RoleRepository repository;

    private final TypeRepository typeRepository;

    private final HotelRepository hotelRepository;


    @Bean
    ModelMapper modelMapper() {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<UserDto, User> UserDtoToUserConverter = new Converter<UserDto, User>() {

            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public User convert(MappingContext<UserDto, User> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                User user = modelMapper.map(context.getSource(), User.class);
                if (context.getSource().getRoleDto() != null) {
                    user.setRole(repository.findByName(context.getSource().getRoleDto().getName()));
                }
                return user;
            }
        };

        Converter<FloorDto, Floor> FloorDtoFloorConverter = new Converter<FloorDto, Floor>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public Floor convert(MappingContext<FloorDto, Floor> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Floor floor = modelMapper.map(context.getSource(), Floor.class);
                if (context.getSource().getHotelDto() != null) {
                    floor.setHotel(hotelRepository.findByName(context.getSource().getHotelDto().getName()));
                }
                return floor;
            }
        };

        Converter<RoomDto, Room> RoomDtoToRoomConverter = new Converter<RoomDto, Room>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public Room convert(MappingContext<RoomDto, Room> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                Room room = modelMapper.map(context.getSource(), Room.class);
                Floor floor = room.getFloor();
                if (context.getSource().getFloorDto() != null) {
                    if (context.getSource().getFloorDto().getHotelDto() != null) {
                        floor.setHotel(hotelRepository.findByName(context.getSource().getFloorDto().getHotelDto().getName()));
                    }
                    floor.setNumber(context.getSource().getFloorDto().getNumber());
                }
                if (context.getSource().getTypeDto() != null) {
                    room.setType(typeRepository.findByType(context.getSource().getTypeDto().getType()));
                }
                room.setFloor(floor);
                return room;
            }
        };

        Converter<Room, RoomDto> RoomToRoomDtoConverter = new Converter<Room, RoomDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public RoomDto convert(MappingContext<Room, RoomDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                RoomDto roomDto = modelMapper.map(context.getSource(), RoomDto.class);
                if (context.getSource().getFloor() != null) {
                    FloorDto floorDto = new FloorDto();
                    floorDto.setNumber(context.getSource().getFloor().getNumber());
                    if (context.getSource().getFloor().getHotel() != null) {
                        floorDto.setHotelDto(new HotelDto(context.getSource().getFloor().getHotel().getName()));
                    }
                    roomDto.setFloorDto(floorDto);
                }
                if (context.getSource().getType() != null) {
                    roomDto.setTypeDto(new TypeDto(context.getSource().getType().getType()));
                }
                return roomDto;
            }
        };

        Converter<Floor, FloorDto> FloorToFloorDtoConverter = new Converter<Floor, FloorDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public FloorDto convert(MappingContext<Floor, FloorDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                FloorDto floorDto = modelMapper.map(context.getSource(), FloorDto.class);
                if (context.getSource().getHotel() != null) {
                    floorDto.setHotelDto(new HotelDto(context.getSource().getHotel().getName()));
                }
                return floorDto;
            }
        };

        Converter<User, UserDto> UserToUserDtoConverter = new Converter<User, UserDto>() {
            private final ModelMapper modelMapper = new ModelMapper();

            @Override
            public UserDto convert(MappingContext<User, UserDto> context) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                UserDto userDto = modelMapper.map(context.getSource(), UserDto.class);
                if (context.getSource().getRole() != null) {
                    userDto.setRoleDto(new RoleDto(context.getSource().getRole().getName()));
                }
                return userDto;
            }
        };

        mapper.addConverter(UserDtoToUserConverter);
        mapper.addConverter(FloorDtoFloorConverter);
        mapper.addConverter(RoomDtoToRoomConverter);
        mapper.addConverter(RoomToRoomDtoConverter);
        mapper.addConverter(FloorToFloorDtoConverter);
        mapper.addConverter(UserToUserDtoConverter);

        return mapper;
    }
}

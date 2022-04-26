package com.exadel.hotel.room.service;

import com.exadel.hotel.floor.dto.FloorDto;
import com.exadel.hotel.floor.entity.Floor;
import com.exadel.hotel.hotel.dto.HotelDto;
import com.exadel.hotel.hotel.entity.Hotel;
import com.exadel.hotel.room.dto.RoomDto;
import com.exadel.hotel.room.entity.Room;
import com.exadel.hotel.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    private final ModelMapper modelMapper;

    public RoomDto add(RoomDto roomDto) {
        FloorDto floorDto = roomDto.getFloor();
        HotelDto hotelDto = floorDto.getHotelDto();
        Floor floor  = modelMapper.map(floorDto, Floor.class);
        floor.setHotel(modelMapper.map(hotelDto, Hotel.class));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setFloor(floor);
        Room room1 = roomRepository.save(room);
        RoomDto roomDto1 = modelMapper.map(room1, RoomDto.class);
        roomDto1.setFloor(floorDto);
        return roomDto1;
    }

    public RoomDto update(Long id, RoomDto roomDto) {
        FloorDto floorDto = roomDto.getFloor();
        HotelDto hotelDto = floorDto.getHotelDto();
        Floor floor  = modelMapper.map(floorDto, Floor.class);
        floor.setHotel(modelMapper.map(hotelDto, Hotel.class));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setFloor(floor);
        Room room1 = roomRepository.save(room);
        RoomDto roomDto1 = modelMapper.map(room1, RoomDto.class);
        roomDto1.setFloor(floorDto);
        return roomDto1;
    }

    public RoomDto get(Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);
        Floor floor = room.getFloor();
        Hotel hotel = floor.getHotel();
        HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
        FloorDto floorDto = modelMapper.map(floor, FloorDto.class);
        floorDto.setHotelDto(hotelDto);
        roomDto.setFloor(floorDto);
        return roomDto;
    }

    public List<RoomDto> getAll() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            Floor floor = room.getFloor();
            Hotel hotel = floor.getHotel();
            HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
            FloorDto floorDto = modelMapper.map(floor, FloorDto.class);
            floorDto.setHotelDto(hotelDto);
            RoomDto roomDto = modelMapper.map(room, RoomDto.class);
            roomDto.setFloor(floorDto);
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    public void deleteAll() {
        roomRepository.deleteAll();
    }

    public List<RoomDto> getByFloorId(Long id) {
        List<Room> rooms = roomRepository.findAllByFloorId(id);
        List<RoomDto> roomDtos = new ArrayList<>();
        for (Room room : rooms) {
            Floor floor = room.getFloor();
            Hotel hotel = floor.getHotel();
            HotelDto hotelDto = modelMapper.map(hotel, HotelDto.class);
            FloorDto floorDto = modelMapper.map(floor, FloorDto.class);
            floorDto.setHotelDto(hotelDto);
            RoomDto roomDto = modelMapper.map(room, RoomDto.class);
            roomDto.setFloor(floorDto);
            roomDtos.add(roomDto);
        }
        return roomDtos;
    }
}

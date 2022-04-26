package com.exadel.hotel.floor.service;

import com.exadel.hotel.floor.dto.FloorDto;
import com.exadel.hotel.floor.entity.Floor;
import com.exadel.hotel.floor.repository.FloorRepository;
import com.exadel.hotel.hotel.dto.HotelDto;
import com.exadel.hotel.hotel.entity.Hotel;
import com.exadel.hotel.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorService {

    private final FloorRepository floorRepository;

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    public FloorDto add(FloorDto floorDto) {
        Floor floor = modelMapper.map(floorDto, Floor.class);
        Hotel hotelByName = hotelRepository.findByName(floorDto.getHotelDto().getName());
        floor.setHotel(hotelByName);
        FloorDto floorDto1 = modelMapper.map(floorRepository.save(floor), FloorDto.class);
        floorDto1.setHotelDto(modelMapper.map(hotelByName, HotelDto.class));
        return floorDto1;
    }

    public FloorDto update(Long id, FloorDto floorDto) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Floor with id " + id + " not found"));
        floor.setNumber(floorDto.getNumber());
        Hotel hotel = hotelRepository.findByName(floorDto.getHotelDto().getName());
        floor.setHotel(hotel);
        return modelMapper.map(floorRepository.save(floor), FloorDto.class);
    }

    public FloorDto get(Long id) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Floor with id " + id + " not found"));
        return new FloorDto(floor.getNumber(),new HotelDto(floor.getHotel().getName()));
    }

    public List<FloorDto> getAll() {
        return modelMapper.map(floorRepository.findAll(), new TypeToken<List<FloorDto>>() {}.getType());
    }

    public void delete(Long id) {
        floorRepository.deleteById(id);
    }

    public void deleteAll() {
        floorRepository.deleteAll();
    }

    public List<FloorDto> getAllByHotelId(Long hotelId) {
        return modelMapper.map(floorRepository.findAllByHotelId(hotelId), new TypeToken<List<FloorDto>>() {}.getType());
    }


}

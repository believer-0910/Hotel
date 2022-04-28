package com.exadel.hotel.hotel.service;

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
public class HotelService {

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    public HotelDto add(HotelDto hotelDto) {
        return modelMapper.map(hotelRepository.save(modelMapper.map(hotelDto, Hotel.class)), HotelDto.class);
    }

    public HotelDto get(Long id) {
        return modelMapper.map(hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found")), HotelDto.class);
    }

    public HotelDto update(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
        modelMapper.map(hotelDto, hotel);
        return modelMapper.map(hotelRepository.save(hotel), HotelDto.class);
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    public List<HotelDto> getAll() {
        return modelMapper.map(hotelRepository.findAll(), new TypeToken<List<HotelDto>>() {}.getType());
    }

    public void deleteAll() {
        hotelRepository.deleteAll();
    }
}

package com.exadel.hotel.type.service;

import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.entity.Type;
import com.exadel.hotel.type.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    private final ModelMapper modelMapper;

    public TypeDto add(TypeDto typeDto) {
        return modelMapper.map(typeRepository.save(modelMapper.map(typeDto, Type.class)), TypeDto.class);
    }

    public TypeDto update(Long id, TypeDto typeDto) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type with id " + id + " not found"));
        type.setType(typeDto.getType());
        return modelMapper.map(typeRepository.save(type), TypeDto.class);
    }

    public TypeDto get(Long id) {
        return modelMapper.map(typeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Type with id " + id + " not found")), TypeDto.class);
    }

    public List<TypeDto> getAll() {
        return modelMapper.map(typeRepository.findAll(), new TypeToken<List<TypeDto>>() {}.getType());
    }

    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    public void deleteAll() {
        typeRepository.deleteAll();
    }

}

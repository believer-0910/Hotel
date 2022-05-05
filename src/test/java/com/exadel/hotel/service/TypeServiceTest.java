package com.exadel.hotel.service;

import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.entity.Type;
import com.exadel.hotel.type.repository.TypeRepository;
import com.exadel.hotel.type.service.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TypeServiceTest {
    private TypeService typeService;
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private TypeRepository typeRepository;
    
    @BeforeEach
    void setUp() {
        typeService = new TypeService(typeRepository, modelMapper);
        when(modelMapper.map(new TypeDto("type"), Type.class)).thenReturn(new Type(1L, "type"));
    }
    
    @Test
    void add() {
        TypeDto typeDto = new TypeDto("type");
        typeService.add(typeDto);
        Type type = modelMapper.map(typeDto, Type.class);
        Type save = verify(typeRepository).save(type);
        assertThat(save).isEqualTo(type);
    }

    @Test
    void getAll(){
        typeService.getAll();
        verify(typeRepository).findAll();
    }

    @Test
    void get(){
        when(typeRepository.findById(1L)).thenReturn(Optional.of(new Type(1L, "type")));
        TypeDto typeDto = typeService.get(1L);
        assertNotNull(typeDto);
        assertEquals(typeDto.getType(), "type");
        verify(typeService).get(1L);
        verify(typeRepository).findById(1L);
    }
}
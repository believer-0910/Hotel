package com.exadel.hotel.type.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.entity.Type;
import com.exadel.hotel.type.repository.TypeRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TypeService.class})
@ExtendWith(SpringExtension.class)
class TypeServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TypeRepository typeRepository;

    @Autowired
    private TypeService typeService;

    /**
     * Method under test: {@link TypeService#add(TypeDto)}
     */
    @Test
    void testAdd() {
        Type type = new Type();
        type.setId(123L);
        type.setType("Type");
        when(this.typeRepository.save((Type) any())).thenReturn(type);
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.typeService.add(new TypeDto("Type")));
        verify(this.modelMapper).map((Object) any(), (Class<Type>) any());
    }

    /**
     * Method under test: {@link TypeService#add(TypeDto)}
     */
    @Test
    void testAdd2() {
        Type type = mock(Type.class);
        doNothing().when(type).setId((Long) any());
        doNothing().when(type).setType((String) any());
        type.setId(123L);
        type.setType("Type");
        when(this.typeRepository.save((Type) any())).thenReturn(type);
        when(this.modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(null);
        assertNull(this.typeService.add(new TypeDto("Type")));
        verify(this.typeRepository).save((Type) any());
        verify(type).setId((Long) any());
        verify(type).setType((String) any());
        verify(this.modelMapper, atLeast(1)).map((Object) any(), (Class<Type>) any());
    }

    /**
     * Method under test: {@link TypeService#update(Long, TypeDto)}
     */
    @Test
    void testUpdate() {
        Type type = new Type();
        type.setId(123L);
        type.setType("Type");
        Optional<Type> ofResult = Optional.of(type);

        Type type1 = new Type();
        type1.setId(123L);
        type1.setType("Type");
        when(this.typeRepository.save((Type) any())).thenReturn(type1);
        when(this.typeRepository.findById((Long) any())).thenReturn(ofResult);
        TypeDto typeDto = new TypeDto("Type");
        when(this.modelMapper.map((Object) any(), (Class<TypeDto>) any())).thenReturn(typeDto);
        assertSame(typeDto, this.typeService.update(123L, new TypeDto("Type")));
        verify(this.typeRepository).save((Type) any());
        verify(this.typeRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<TypeDto>) any());
    }

    /**
     * Method under test: {@link TypeService#update(Long, TypeDto)}
     */
    @Test
    void testUpdate2() {
        Type type = new Type();
        type.setId(123L);
        type.setType("Type");
        Optional<Type> ofResult = Optional.of(type);

        Type type1 = new Type();
        type1.setId(123L);
        type1.setType("Type");
        when(this.typeRepository.save((Type) any())).thenReturn(type1);
        when(this.typeRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.modelMapper.map((Object) any(), (Class<TypeDto>) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.typeService.update(123L, new TypeDto("Type")));
        verify(this.typeRepository).save((Type) any());
        verify(this.typeRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<TypeDto>) any());
    }

    /**
     * Method under test: {@link TypeService#get(Long)}
     */
    @Test
    void testGet() {
        Type type = new Type();
        type.setId(123L);
        type.setType("Type");
        Optional<Type> ofResult = Optional.of(type);
        when(this.typeRepository.findById((Long) any())).thenReturn(ofResult);
        TypeDto typeDto = new TypeDto("Type");
        when(this.modelMapper.map((Object) any(), (Class<TypeDto>) any())).thenReturn(typeDto);
        assertSame(typeDto, this.typeService.get(123L));
        verify(this.typeRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<TypeDto>) any());
    }

    /**
     * Method under test: {@link TypeService#get(Long)}
     */
    @Test
    void testGet2() {
        Type type = new Type();
        type.setId(123L);
        type.setType("Type");
        Optional<Type> ofResult = Optional.of(type);
        when(this.typeRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.modelMapper.map((Object) any(), (Class<TypeDto>) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.typeService.get(123L));
        verify(this.typeRepository).findById((Long) any());
        verify(this.modelMapper).map((Object) any(), (Class<TypeDto>) any());
    }


    /**
     * Method under test: {@link TypeService#getAll()}
     */
    @Test
    void testGetAll() {
        when(this.typeRepository.findAll()).thenReturn(new ArrayList<>());
        when(this.modelMapper.map((Object) any(), (java.lang.reflect.Type) any()))
                .thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> this.typeService.getAll());
        verify(this.typeRepository).findAll();
        verify(this.modelMapper).map((Object) any(), (java.lang.reflect.Type) any());
    }

    /**
     * Method under test: {@link TypeService#getAll()}
     */
    @Test
    void testGetAll2() {
        when(this.typeRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<Object> objectList = new ArrayList<>();
        when(this.modelMapper.map((Object) any(), (java.lang.reflect.Type) any())).thenReturn(objectList);
        List<TypeDto> actualAll = this.typeService.getAll();
        assertSame(objectList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.typeRepository).findAll();
        verify(this.modelMapper).map((Object) any(), (java.lang.reflect.Type) any());
    }

    /**
     * Method under test: {@link TypeService#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(this.typeRepository).deleteById((Long) any());
        this.typeService.delete(123L);
        verify(this.typeRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link TypeService#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new IllegalArgumentException("foo")).when(this.typeRepository).deleteById((Long) any());
        assertThrows(IllegalArgumentException.class, () -> this.typeService.delete(123L));
        verify(this.typeRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link TypeService#deleteAll()}
     */
    @Test
    void testDeleteAll() {
        doNothing().when(this.typeRepository).deleteAll();
        this.typeService.deleteAll();
        verify(this.typeRepository).deleteAll();
    }

    /**
     * Method under test: {@link TypeService#deleteAll()}
     */
    @Test
    void testDeleteAll2() {
        doThrow(new IllegalArgumentException("foo")).when(this.typeRepository).deleteAll();
        assertThrows(IllegalArgumentException.class, () -> this.typeService.deleteAll());
        verify(this.typeRepository).deleteAll();
    }
}


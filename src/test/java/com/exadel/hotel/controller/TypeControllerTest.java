package com.exadel.hotel.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.exadel.hotel.type.controller.TypeController;
import com.exadel.hotel.type.dto.TypeDto;
import com.exadel.hotel.type.service.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TypeController.class})
@ExtendWith(SpringExtension.class)
class TypeControllerTest {
    @Autowired
    private TypeController typeController;

    @MockBean
    private TypeService typeService;

    /**
     * Method under test: {@link TypeController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(this.typeService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/type/getAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TypeController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        when(this.typeService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/type/getAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TypeController#get(Long)}
     */
    @Test
    void testGet() throws Exception {
        when(this.typeService.get((Long) any())).thenReturn(new TypeDto("Type"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/type/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    /**
     * Method under test: {@link TypeController#get(Long)}
     */
    @Test
    void testGet2() throws Exception {
        when(this.typeService.get((Long) any())).thenReturn(new TypeDto("Type"));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/type/get/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    /**
     * Method under test: {@link TypeController#add(TypeDto)}
     */
    @Test
    void testAdd() throws Exception {
        when(this.typeService.add((TypeDto) any())).thenReturn(new TypeDto("Type"));

        TypeDto typeDto = new TypeDto();
        typeDto.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(typeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/type/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    /**
     * Method under test: {@link TypeController#update(Long, TypeDto)}
     */
    @Test
    void testUpdate() throws Exception {
        when(this.typeService.update((Long) any(), (TypeDto) any())).thenReturn(new TypeDto("Type"));

        TypeDto typeDto = new TypeDto();
        typeDto.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(typeDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/type/update/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"type\":\"Type\"}"));
    }

    /**
     * Method under test: {@link TypeController#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(this.typeService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/type/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TypeController#delete(Long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(this.typeService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/type/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TypeController#deleteAll()}
     */
    @Test
    void testDeleteAll() throws Exception {
        doNothing().when(this.typeService).deleteAll();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/type/deleteAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TypeController#deleteAll()}
     */
    @Test
    void testDeleteAll2() throws Exception {
        doNothing().when(this.typeService).deleteAll();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/type/deleteAll");
        MockMvcBuilders.standaloneSetup(this.typeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}


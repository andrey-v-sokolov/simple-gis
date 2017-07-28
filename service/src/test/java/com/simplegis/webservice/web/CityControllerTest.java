package com.simplegis.webservice.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegis.common.dto.CityDto;
import com.simplegis.webservice.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class CityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAll() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/city/getAll").accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        List<CityDto> cityDtos = mapper.readValue(json, new TypeReference<List<CityDto>>() {});

        assertTrue(cityDtos.size() == 3);
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/city/getById/{id}", 1).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        CityDto city = mapper.readValue(json, new TypeReference<CityDto>() {});

        assertTrue(city.getName().equals("бердск"));
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void batchInsert() throws Exception {

    }

    @Test
    public void getByName() throws Exception {

    }

    @Test
    public void getByArea() throws Exception {

    }

    @Test
    public void getByPopulation() throws Exception {

    }

}
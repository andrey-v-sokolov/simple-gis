package com.simplegis.webservice.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegis.common.dto.CityDto;
import com.simplegis.webservice.TestConfig;
import org.junit.Before;
import org.junit.Ignore;
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void getByName() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/city/getByName/{name}", URLEncoder.encode("барн", StandardCharsets.UTF_8.name())).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<CityDto> cityDtos = mapper.readValue(json, new TypeReference<List<CityDto>>() {});

        assertTrue(cityDtos.size() == 1);
        assertTrue("барнаул".equals(cityDtos.get(0).getName()));
    }

    @Test
    public void getByArea() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/city/getByArea/{minArea}/{maxArea}/",
                        new BigDecimal("50.1"), new BigDecimal("300.2")).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<CityDto> cityDtos = mapper.readValue(json, new TypeReference<List<CityDto>>() {});

        assertTrue(cityDtos.size() == 1);
        assertTrue("бердск".equals(cityDtos.get(0).getName()));
    }

    @Test
    public void getByPopulation() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/city/getByPopulation/{minPopulation}/{maxPopulation}",
                        new BigInteger("1000000"), new BigInteger("1500000")).accept(MediaType
                        .APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<CityDto> cityDtos = mapper.readValue(json, new TypeReference<List<CityDto>>() {});

        assertTrue(cityDtos.size() == 1);
        assertTrue("новосибирск".equals(cityDtos.get(0).getName()));
    }

    @Test
    public void update() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/city/getById/{id}", 1).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        CityDto city = mapper.readValue(json, new TypeReference<CityDto>() {});

        city.setPopulation(200000);

        result = mockMvc.perform(
                post("/city/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(city))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        json = result.getResponse().getContentAsString();


        Integer updated = mapper.readValue(json, new TypeReference<Integer>() {});

        assertTrue(updated == 1);
    }

    @Test
    public void insert() throws Exception {
        CityDto city = new CityDto();
        city.setName("искитим");
        city.setArea(new BigDecimal("34.3"));
        city.setPopulation(70000);

        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(
                post("/city/insert").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(city))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        city = mapper.readValue(json, new TypeReference<CityDto>() {});

        assertTrue(city.getVersion() == 0);
        assertTrue(city.getId() != null);
    }

    @Test
    @Ignore
    public void batchInsert() throws Exception {
        //ToDo: it seems that custom batch update does not work in H2 db.
    }

}
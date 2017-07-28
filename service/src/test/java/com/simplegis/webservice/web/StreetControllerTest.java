package com.simplegis.webservice.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegis.common.dto.CityDto;
import com.simplegis.common.dto.StreetDto;
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
public class StreetControllerTest {

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
                get("/street/getAll").accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        List<StreetDto> streetDtos = mapper.readValue(json, new TypeReference<List<StreetDto>>() {});

        assertTrue(streetDtos.size() == 6);
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/street/getById/{id}", 1).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        StreetDto street = mapper.readValue(json, new TypeReference<StreetDto>() {});

        assertTrue(street.getName().equals("ленина"));
    }

    @Test
    public void getByName() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/street/getByName/{name}", URLEncoder.encode("ильич", StandardCharsets.UTF_8.name())).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<StreetDto> streetDtos = mapper.readValue(json, new TypeReference<List<StreetDto>>() {
        });
        assertTrue(streetDtos.size() == 1);
    }

    @Test
    public void getByLength() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/street/getByLength/{minLength}/{maxLength}/",
                        new BigDecimal("500"),
                        new BigDecimal("1000")).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<StreetDto> streetDtos = mapper.readValue(json, new TypeReference<List<StreetDto>>() {
        });
        assertTrue(streetDtos.size() == 1);
    }

    @Test
    public void getByCityIdAndName() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/street/getByCityIdAndName/{cityId}/{name}",
                        2L,
                        URLEncoder.encode("ильич", StandardCharsets.UTF_8.name())).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<StreetDto> streetDtos = mapper.readValue(json, new TypeReference<List<StreetDto>>() {
        });
        assertTrue(streetDtos.size() == 1);
    }

    @Test
    public void getByCityIdAndLength() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/street/getByCityIdAndLength/{cityId}/{minLength}/{maxLength}/",
                        2L,
                        new BigDecimal("500"),
                        new BigDecimal("1000")).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<StreetDto> streetDtos = mapper.readValue(json, new TypeReference<List<StreetDto>>() {
        });
        assertTrue(streetDtos.size() == 1);
    }

    @Test
    public void update() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/street/getById/{id}", 3).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        StreetDto streetDto = mapper.readValue(json, new TypeReference<StreetDto>() {});

        streetDto.setName("революции");

        result = mockMvc.perform(
                post("/street/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(streetDto))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        json = result.getResponse().getContentAsString();


        Integer updated = mapper.readValue(json, new TypeReference<Integer>() {});

        assertTrue(updated == 1);
    }

    @Test
    public void insert() throws Exception {
        StreetDto street = new StreetDto();
        street.setName("3-й переулок Крашенинникова");
        street.setCityId(1L);
        street.setLength(new BigDecimal("1523.3353"));

        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(
                post("/street/insert").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(street))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        street = mapper.readValue(json, new TypeReference<StreetDto>() {});

        assertTrue(street.getVersion() == 0);
        assertTrue(street.getId() != null);
    }

    @Test
    @Ignore
    public void batchInsert() throws Exception {
        //ToDo: it seems that custom batch update does not work in H2 db.
    }

}
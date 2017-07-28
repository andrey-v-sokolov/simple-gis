package com.simplegis.webservice.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.ScopeDto;
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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class OrganizationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAll() throws Exception {
//        ToDo: H2 specific test.
//        MvcResult result = mockMvc.perform(
//                get("/organization/getAll").accept(MediaType.APPLICATION_JSON)
//
//        ).andExpect(status().is2xxSuccessful()).andReturn();
//        String json = result.getResponse().getContentAsString();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});
//
//        assertTrue(organizationDtos.size() == 18);
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getById/{id}", 1).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        OrganizationDto organization = mapper.readValue(json, new TypeReference<OrganizationDto>() {});

        assertTrue(organization.getName().equals("Евросалон"));
    }

    @Test
    public void getByName() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getByName/{name}", URLEncoder.encode("сало", StandardCharsets.UTF_8.name())).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 1);
        assertTrue("Евросалон".equals(organizationDtos.get(0).getName()));
    }

    @Test
    public void getByCityId() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getByCityId/{id}", 1L)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 6);
    }

    @Test
    public void getByCityIdAndStreetId() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getByCityIdAndStreetId/{cityId}/{streetId}", 1L, 1L)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 4);
    }

    @Test
    public void getByCityIdAndStreetIdAndBuilding() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getByCityIdAndStreetIdAndBuilding/{cityId}/{streetId}/{building}", 1L, 1L, 2)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 1);
    }

    @Test
    public void getByScopeNameOrOrganizationNameAndGeoToken() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getByScopeNameOrOrganizationNameAndGeoToken/{orgToken}/{geoToken}",
                        URLEncoder.encode("поесть", StandardCharsets.UTF_8.name()),
                        URLEncoder.encode("ленина", StandardCharsets.UTF_8.name()))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 4);
    }

    @Test
    public void getOrganizationAddedOrModifiedSince() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mockMvc.perform(
                post("/organization/getOrganizationAddedOrModifiedSince").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new Timestamp(new Date().getTime())))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        List<OrganizationDto> organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 0);

        result = mockMvc.perform(
                post("/organization/getOrganizationAddedOrModifiedSince").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(new Timestamp(1501029404000L)))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        json = result.getResponse().getContentAsString();

        organizationDtos = mapper.readValue(json, new TypeReference<List<OrganizationDto>>() {});

        assertTrue(organizationDtos.size() == 18);
    }

    @Test
    public void update() throws Exception {

        MvcResult result = mockMvc.perform(
                get("/organization/getById/{id}", 2).accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        OrganizationDto organizationDto = mapper.readValue(json, new TypeReference<OrganizationDto>() {});

        organizationDto.setBuilding(123);

        result = mockMvc.perform(
                post("/organization/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(organizationDto))

        ).andExpect(status().is2xxSuccessful()).andReturn();
        json = result.getResponse().getContentAsString();


        Integer updated = mapper.readValue(json, new TypeReference<Integer>() {});

        assertTrue(updated == 1);
    }

    @Test
    public void insert() throws Exception {
//        ToDo: H2 tests for multiple generated keys return?
//        OrganizationDto organizationDto = new OrganizationDto();
//        organizationDto.setName("Рога и копыта");
//        organizationDto.setCity(1L);
//        organizationDto.setStreet(1L);
//        organizationDto.setBuilding(1);
//        organizationDto.setScope(1);
//        organizationDto.setWww("site.com");
//
//
//        List<PhoneDto> phones = new LinkedList<>();
//        PhoneDto phone0 = new PhoneDto();
//        phone0.setNumber("123123123");
//        PhoneDto phone1 = new PhoneDto();
//        phone1.setNumber("321321321");
//
//        phones.add(phone0);
//        phones.add(phone1);
//
//        organizationDto.setPhones(phones);
//
//        ObjectMapper mapper = new ObjectMapper();
//        MvcResult result = mockMvc.perform(
//                post("/organization/insert").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(organizationDto))
//
//        ).andExpect(status().is2xxSuccessful()).andReturn();
//        String json = result.getResponse().getContentAsString();
//
//        organizationDto = mapper.readValue(json, new TypeReference<OrganizationDto>() {});
//
//        assertTrue(organizationDto.getModified() != null);
//        assertTrue(organizationDto.getId() != null);
//        assertTrue(organizationDto.getPhones().get(0).getId() != null);
    }

    @Test
    @Ignore
    public void batchInsert() throws Exception {
        //ToDo: it seems that custom batch update does not work in H2 db.
    }

    @Test
    public void getAvailableScopes() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/organization/getAvailableScopes").accept(MediaType.APPLICATION_JSON)

        ).andExpect(status().is2xxSuccessful()).andReturn();
        String json = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();

        List<ScopeDto> scopeDtos = mapper.readValue(json, new TypeReference<List<ScopeDto>>() {});

        assertTrue(scopeDtos.size() == 9);
    }

    @Test
    public void addPhone() throws Exception {

    }

    @Test
    public void updatePhone() throws Exception {

    }

    @Test
    public void deletePhone() throws Exception {

    }

}
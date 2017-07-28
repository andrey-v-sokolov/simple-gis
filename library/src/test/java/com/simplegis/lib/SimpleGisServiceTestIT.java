package com.simplegis.lib;

import com.simplegis.common.dto.CityDto;
import com.simplegis.common.dto.OrganizationDto;
import com.simplegis.common.dto.ScopeDto;
import com.simplegis.common.dto.StreetDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SimpleGisServiceTestIT {

    @Autowired
    private SimpleGisService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCities() throws Exception {
        List<CityDto> cityDtos = service.getAllCities();
        assertTrue(cityDtos.size() == 3);

    }

    @Test
    public void findCitiesByArea() throws Exception {
        List<CityDto> cityDtos = service.findCitiesByArea(new BigDecimal("50.1"), new BigDecimal("300.2"));
        assertTrue(cityDtos.size() == 1);
    }

    @Test
    public void findCitiesByPopulation() throws Exception {
        List<CityDto> cityDtos = service.findCitiesByPopulation(new BigInteger("1000000"), new BigInteger("1500000"));
        assertTrue(cityDtos.size() == 1);
    }

    @Test
    public void getAllScopes() throws Exception {
        List<ScopeDto> scopeDtos = service.getAllScopes();
        assertTrue(scopeDtos.size() == 9);
    }

    @Test
    public void findStreetsByCityAndLength() throws Exception {
        List<StreetDto> streetDtos = service.findStreetsByCityAndLength(2L, new BigDecimal("500"), new BigDecimal("1000"));
        assertTrue(streetDtos.size() == 1);
    }

    @Test
    public void findOrganizationsByStringAndGeoTokens() throws Exception {
        List<OrganizationDto> organizationDtos = service.findOrganizationsByStringAndGeoTokens("поесть", "ленина");
        assertTrue(organizationDtos.size() == 7);

    }

    @Test
    public void findOrganizationsByCityAndScopeIds() throws Exception {
        List<OrganizationDto> organizationDtos = service.findOrganizationsByCityAndScopeIds(2L, 1L);
        assertTrue(organizationDtos.size() == 1);

    }

    @Test
    public void findOrganizationsByCityAndStreetIds() throws Exception {
        List<OrganizationDto> organizationDtos = service.findOrganizationsByCityAndStreetIds(2L, 2L);
        assertTrue(organizationDtos.size() == 3);
    }

}
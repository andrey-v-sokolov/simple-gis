package com.simplegis.lib;

import com.simplegis.common.dto.CityDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    }

    @Test
    public void findCitiesByPopulation() throws Exception {
    }

    @Test
    public void getAllScopes() throws Exception {
    }

    @Test
    public void findStreetsByCityAndLength() throws Exception {
    }

    @Test
    public void findOrganizationsByStringAndGeoTokens() throws Exception {
    }

    @Test
    public void findOrganizationsByCityAndScopeIds() throws Exception {
    }

    @Test
    public void findOrganizationsByCityAndStreetIds() throws Exception {
    }

}
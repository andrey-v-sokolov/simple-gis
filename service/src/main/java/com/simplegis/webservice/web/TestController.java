package com.simplegis.webservice.web;

import com.simplegis.webservice.persistence.dao.CityDao;
import com.simplegis.webservice.persistence.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Test controller.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private CityDao cityDao;

    /**
     * Test endpoint.
     * @return string.
     */
    @RequestMapping("/test")
    public String test() {

        cityDao.getAllCities().forEach(city -> LOG.debug(city.toString()));
        City city = new City();
        city.setName("test");
        city.setArea(BigDecimal.ONE);
        city.setPopulation(1);
        cityDao.insertCity(city);

        LOG.debug("it works");
        return "it works";
    }
}

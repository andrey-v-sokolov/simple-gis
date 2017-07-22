package com.simplegis.webservice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

//    @Autowired
//    private CityDao cityDao;

    /**
     * Test endpoint.
     * @return string.
     */
    @RequestMapping("/test")
    public String test() {


//        City city = new City();
//        city.setName("test1");
//        city.setArea(BigDecimal.ONE);
//        city.setPopulation(1);
//
//        City city1 = new City();
//        city1.setName("test2");
//        city1.setArea(BigDecimal.ONE);
//        city1.setPopulation(1);
//
//        City city2 = new City();
//        city2.setName("test3");
//        city2.setArea(BigDecimal.ONE);
//        city2.setPopulation(1);
//
//        List<City> cities = new LinkedList<>();
//        cities.add(city);
//        cities.add(city1);
//        cities.add(city2);


//        cityDao.batchInsertCities(cities);

//        cityDao.getAllCities().forEach(c -> LOG.debug(c.toString()));

//        LOG.debug("City id = " + city.getId());
        LOG.debug("it works");
        return "it works";
    }
}

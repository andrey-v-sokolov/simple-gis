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

    /**
     * Test endpoint.
     * @return string.
     */
    @RequestMapping("/test")
    public String test() {

        LOG.debug("it works");
        return "it works";
    }
}

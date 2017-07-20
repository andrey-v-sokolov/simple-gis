package com.simplegis.webservice.web;

import com.simplegis.webservice.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private Test test;

    /**
     * Test endpoint.
     * @return string.
     */
    @RequestMapping("/test")
    public String test() {

        test.test();
        LOG.debug("it works");
        return "it works";
    }
}

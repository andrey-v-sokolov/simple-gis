package com.simplegis.webservice;

import org.jooq.DSLContext;
import org.jooq.conf.RenderNameStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Test class.
 */
@Component
public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    @Autowired
    private DSLContext dslContext;

    /**
     * Test method.
     */
    public void test() {
        dslContext.settings().setRenderNameStyle(RenderNameStyle.AS_IS);
        dslContext.settings().setRenderSchema(false);
        LOG.debug("DSL Context configured.");

        //ToDo: make jooq using the simplegisdb schema instead of public.
        //        dslContext.insertInto()...;
    }
}

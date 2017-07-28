package com.simplegis.lib.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Api service class.
 */
@Service
public class ApiService {
    private static final Logger LOG = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    private ObjectMapper objectMapper;

    private SimpleGisApiClient client;

    /**
     * Creates client or return exist one.
     *
     * @return client
     */
    public SimpleGisApiClient getClient() {

        if (client == null) {
            LOG.debug("Creating client");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://localhost:8080") //ToDo: configurable url
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .build();

            client = retrofit.create(SimpleGisApiClient.class);
        }
        return client;
    }
}

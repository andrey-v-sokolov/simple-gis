package com.simplegis.lib.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

/**
 * Utility to execute api calls.
 *
 * @param <T> expected response class.
 */
public class Executor<T> {
    private static final Logger LOG = LoggerFactory.getLogger(Executor.class);

    /**
     * Executes requests.
     *
     * @param call to execute
     * @return parsed response
     */
    public List<T> execute(Call<List<T>> call) {

        Response<List<T>> response;
        try {
            response = call.execute();
        } catch (Exception e) {
            LOG.error("Failed to execute service request");
            return null;
        }

        return response.body();
    }
}

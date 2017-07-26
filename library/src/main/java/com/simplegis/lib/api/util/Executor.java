package com.simplegis.lib.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.LinkedList;
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

        List<T> results = new LinkedList<>();

        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                for (T res : response.body()) {
                    results.add(res);
                }
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                LOG.error(t.getMessage());
            }
        });
        return results;
    }
}

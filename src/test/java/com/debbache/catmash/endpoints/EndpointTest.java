package com.debbache.catmash.endpoints;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class EndpointTest {

    protected <T> HttpEntity<T> entity(T t) {
        HttpHeaders headers = headers("UserId", "");
        return new HttpEntity<>(t, headers);
    }


    private HttpHeaders headers(String key, String value) {
        HttpHeaders headers = headers();
        headers.set(key, value);
        return headers;
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

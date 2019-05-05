package com.debbache.catmash.endpoints;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BattleControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void given_missing_user_id_when_getNext_then_return_400_bad_request() throws Exception {
        //When
        ResponseEntity responseEntity = restTemplate.getForEntity(
                "/battles/next", Object.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void given_empty_user_id_when_getNext_then_return_a_page_of_100_battles() throws Exception {
        //When
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("userId", "");
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.exchange(
                "/battles/next",
                HttpMethod.GET,
                entity,
                LinkedHashMap.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        LinkedHashMap body = responseEntity.getBody();
        List content = (List) body.get("content");
        assertThat(content).hasSize(100);

    }

    @Test
    public void shoumd_return_400_bad_request_when_page_is_missing() throws Exception {

        //When
        ResponseEntity responseEntity = restTemplate.getForEntity(
                "/battles/next", Object.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

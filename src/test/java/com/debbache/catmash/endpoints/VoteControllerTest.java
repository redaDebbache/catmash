package com.debbache.catmash.endpoints;

import com.debbache.catmash.dto.VoteDTO;
import com.debbache.catmash.model.Vote;
import com.debbache.catmash.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoteControllerTest{

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void given_voteDto_when_vote_then_should_be_stored_in_database() {
        //Given
        VoteDTO requestVote = new VoteDTO("MTgwODA3MA_tt", "currentUser", "tt");
        //When
        ResponseEntity responseEntity = restTemplate.exchange(
                "/vote",
                HttpMethod.POST,
                headers(requestVote),
                Object.class);
        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Vote> storedVotes = voteRepository.findAll();
        assertThat(storedVotes).isNotEmpty();
        assertThat(storedVotes).hasSize(1);
        Vote storedVote = storedVotes.get(0);
        assertThat(storedVote.getOwner().getId()).isEqualTo(requestVote.getUserId());
        assertThat(storedVote.getBattle().getId()).isEqualTo(requestVote.getBattleId());
        assertThat(storedVote.getWinner().getId()).isEqualTo(requestVote.getWinnerId());

    }

    private <T> HttpEntity<T> headers(T t) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(t, headers);
    }
}

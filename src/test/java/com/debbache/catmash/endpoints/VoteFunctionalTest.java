package com.debbache.catmash.endpoints;

import com.debbache.catmash.dto.VoteDTO;
import com.debbache.catmash.model.Battle;
import com.debbache.catmash.model.Vote;
import com.debbache.catmash.repository.VoteRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoteFunctionalTest extends EndpointTest {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void given_voteDto_when_vote_then_should_be_stored_in_database() {
        //Given
        VoteDTO requestVote = new VoteDTO("MTgwODA3MA_tt", "currentUser", "tt");
        //When
        ResponseEntity responseEntity = vote(requestVote);
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

    private ResponseEntity vote(VoteDTO requestVote) {
        return restTemplate.exchange(
                    "/vote",
                    HttpMethod.POST,
                    entity(requestVote),
                    Object.class);
    }

    @Test
    public void given_a_firs_page_of_battles_when_vote_and_get_ther_battles_no_battle_already_voted_by_user_should_be_returned() throws IOException {
        //Given
        String userId = "User id";
        List<Battle> initialBattles = getBattles(userId);
        initialBattles.stream().map(b -> new VoteDTO(b.getId(), userId, b.getFirst().getId()))
                .forEach(this::vote);

        //When
        List<Battle> nextBattles = getBattles(userId);
        //Then
        assertThat(nextBattles).containsAnyOf(initialBattles.toArray(new Battle[initialBattles.size()]));
    }


    private List<Battle> getBattles(String userId) throws IOException {
        ResponseEntity<String> initialBattles = genNexBattles(userId);
        String body = initialBattles.getBody();
        return extractBattles(body);
    }

    private List<Battle> extractBattles(String body) throws IOException {
        JsonNode battleNode = new ObjectMapper().readTree(body);
        JsonNode content = battleNode.get("content");
        ObjectReader reader = mapper.readerFor(new TypeReference<List<Battle>>() {});
        return reader.readValue(content);
    }


    private ResponseEntity<String> genNexBattles(String userId) {
        return restTemplate.exchange(
                "/battles/next",
                HttpMethod.GET,
                entity(userId),
                String.class);
    }


}

package com.debbache.catmash.manager;

import com.debbache.catmash.configuration.CatMashTest;
import com.debbache.catmash.dto.VoteDTO;
import com.debbache.catmash.model.Vote;
import com.debbache.catmash.repository.UserRepository;
import com.debbache.catmash.repository.VoteRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@CatMashTest
public class VoteManagerTest {

    @InjectMocks
    private VoteManager voteManager;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private UserRepository userRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void should_throw_NPE_When_vote_is_null() {
        //Expect
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("vote must be defined");

        //When
        voteManager.vote(null);
    }

    @Test
    public void should_store_a_valid_vote() {
        //Given
        VoteDTO voteDTO = new VoteDTO("battleId", "userId", "winnerId");
        //When
        voteManager.vote(voteDTO);
        //Then
        ArgumentCaptor<Vote> captor = ArgumentCaptor.forClass(Vote.class);
        verify(voteRepository).save(captor.capture());
        Vote captured = captor.getValue();
        assertThat(captured.getBattle().getId()).isEqualTo(voteDTO.getBattleId());
        assertThat(captured.getOwner().getId()).isEqualTo(voteDTO.getUserId());
        assertThat(captured.getWinner().getId()).isEqualTo(voteDTO.getWinnerId());
    }

    @Test
    public void should_create_a_new_user_if_not_exits() {
        //Given
        VoteDTO voteDTO = new VoteDTO("battleId", "userId", "winnerId");
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        //When
        voteManager.vote(voteDTO);
        //Then
        ArgumentCaptor<Vote> captor = ArgumentCaptor.forClass(Vote.class);
        verify(voteRepository).save(captor.capture());
        Vote captured = captor.getValue();
        assertThat(captured.getOwner()).isNotNull();
        assertThat(captured.getOwner().getId()).isEqualTo("userId");
    }
}
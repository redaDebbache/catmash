package com.debbache.catmash.manager;

import com.debbache.catmash.dto.VoteDTO;
import com.debbache.catmash.model.Battle;
import com.debbache.catmash.model.Cat;
import com.debbache.catmash.model.User;
import com.debbache.catmash.model.Vote;
import com.debbache.catmash.repository.UserRepository;
import com.debbache.catmash.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class VoteManager {

    private static final String VOTE_MUST_BE_DEFINED_ERROR_MESSAGE = "vote must be defined";
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public VoteManager(VoteRepository voteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void vote(VoteDTO voteDTO) {
        Objects.requireNonNull(voteDTO, VOTE_MUST_BE_DEFINED_ERROR_MESSAGE);
        this.voteRepository.save(buildVote(voteDTO));
    }

    private Vote buildVote(VoteDTO voteDTO) {
        User owner = userRepository.findById(voteDTO.getUserId()).orElse(new User(voteDTO.getUserId()));
        return new Vote()
                .owner(owner)
                .battle(new Battle(voteDTO.getBattleId()))
                .winner(new Cat(voteDTO.getWinnerId()));

    }
}

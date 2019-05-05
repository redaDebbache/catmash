package com.debbache.catmash.endPoints;

import com.debbache.catmash.dto.VoteDTO;
import com.debbache.catmash.manager.VoteManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/vote")
@Api( description="Voter et choisir entre deux cahts d'une meême battle")
public class VoteController {

    private static final String VOTE_API_THE_VOTE_IS_MADATORY_ERROR_MESSAGE = "/vote API: The vote is madatory.";
    private final VoteManager voteManager;

    public VoteController(VoteManager voteManager) {
        this.voteManager = voteManager;
    }

    @ApiOperation(value = "End point pour voter pour le plus beau entre deux chats")
    @PostMapping("")
    @CrossOrigin
    public void vote(@ApiParam(value = "Vote a proprement parler", required = true) @RequestBody VoteDTO vote){
        this.voteManager.vote(vote);
    }

}

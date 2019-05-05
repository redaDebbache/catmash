package com.debbache.catmash.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VoteDTO {

    @NotEmpty(message = "The id ofthe battle must be defined")
    private String battleId;

    @NotEmpty(message = "The id of the user must be defined")
    private String userId;

    @NotEmpty(message = "the id of the battle winner must be defined")
    private String winnerId;
}

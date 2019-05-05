package com.debbache.catmash.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BattleDTO {
    private String id;
    private CatDTO first;
    private CatDTO second;

    public BattleDTO(String id, String firstId, String firstImage, String secondId, String secondImage) {
        this.id = id;
        this.first = new CatDTO(firstImage, firstId);
        this.second = new CatDTO(secondImage, secondId);
    }

    @Override
    public String toString() {
        return this.id;
    }
}

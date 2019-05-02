package com.debbache.catmash.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cats {
    private List<CatDTO> images;

    public Cats() {
        this.images = new ArrayList<>();
    }

    public List<CatDTO> getCats() {
        return Optional.ofNullable(images).orElse(new ArrayList<>());
    }

    public void setImages(List<CatDTO> images) {
        this.images = images;
    }
}

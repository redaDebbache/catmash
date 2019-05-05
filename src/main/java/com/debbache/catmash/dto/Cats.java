package com.debbache.catmash.dto;

import java.util.*;

public class Cats {
    private Set<CatDTO> images;

    public Cats() {
        this.images = new HashSet<>();
    }

    public Set<CatDTO> getCats() {
        return Optional.ofNullable(images).orElse(new HashSet<CatDTO>());
    }

    public void setImages(Set<CatDTO> images) {
        this.images = images;
    }
}

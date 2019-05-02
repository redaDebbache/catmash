package com.debbache.catmash.dto;

public class CatDTO {
    private String url;
    private String id;

    public CatDTO(String url, String id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatDTO catDTO = (CatDTO) o;

        return id != null ? id.equals(catDTO.id) : catDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
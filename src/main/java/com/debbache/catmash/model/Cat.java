package com.debbache.catmash.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAT")
public class Cat {
    @Id
    private String id;

    private String imageLocalisation;

    public Cat() {
    }

    public Cat(String id, String imageLocalisation) {
        this.id = id;
        this.imageLocalisation = imageLocalisation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageLocalisation() {
        return imageLocalisation;
    }

    public void setImageLocalisation(String imageLocalisation) {
        this.imageLocalisation = imageLocalisation;
    }
}

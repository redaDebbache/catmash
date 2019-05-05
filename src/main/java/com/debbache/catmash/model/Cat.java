package com.debbache.catmash.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CAT")
@Getter
@Setter
@NoArgsConstructor
public class Cat {
    @Id
    private String id;

    @NotBlank
    private String imageUrl;

    public Cat(String id) {
        this.id = id;
    }

    public Cat(String id, @NotBlank String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

       return this.id.equals(cat.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}

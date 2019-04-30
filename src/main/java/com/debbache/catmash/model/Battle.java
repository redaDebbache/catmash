package com.debbache.catmash.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BATTLE")
public class Battle {

    @Id
    private String id;

    @ManyToOne
    private Cat first;

    @ManyToOne
    private Cat second;

    public Battle() {
    }

    public Battle(Cat first, Cat second) {
        this.first = first;
        this.second = second;
        this.id = String.format("%s_%s", first.getId(), second.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cat getFirst() {
        return first;
    }

    public void setFirst(Cat first) {
        this.first = first;
    }

    public Cat getSecond() {
        return second;
    }

    public void setSecond(Cat second) {
        this.second = second;
    }
}

package com.debbache.catmash.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "BATTLE")
@Getter
@Setter
@NoArgsConstructor
public class Battle {

    @Id
    private String id;

    private UUID functionalId;

    @ManyToOne
    @NotNull
    private Cat first;

    @ManyToOne
    @NotNull
    private Cat second;

    public Battle(Cat first, Cat second) {
        this.first = first;
        this.second = second;
        this.id = Stream.of(first.getId(), second.getId()).sorted().collect(Collectors.joining("_"));
        this.functionalId = UUID.randomUUID();
    }

    public Battle(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Battle battle = (Battle) o;

        return this.id.equals(battle.getId()) || (first.equals(battle.first) || first.equals(battle.second)) && (second.equals(battle.first) || second.equals(battle.second));
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

}

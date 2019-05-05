package com.debbache.catmash.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "VOTE")
@Getter
@Setter
@EqualsAndHashCode
public class Vote {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull
    private User owner;

    @ManyToOne
    @NotNull
    private Battle battle;

    @ManyToOne
    @NotNull
    private Cat winner;

    @CreatedDate
    private LocalDateTime creationDateTime;

    public Vote() {
        this.creationDateTime = LocalDateTime.now();
    }

    public Vote owner(User owner){
        this.owner = owner;
        return this;
    }

   public Vote battle(Battle battle){
        this.battle = battle;
        return this;
    }

   public Vote winner(Cat winner){
        this.winner = winner;
        return this;
    }
}

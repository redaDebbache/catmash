package com.debbache.catmash.model;

import javax.persistence.*;

@Entity
@Table(name = "VOTE")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Battle battle;

    @ManyToOne
    private Cat winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Cat getWinner() {
        return winner;
    }

    public void setWinner(Cat winner) {
        this.winner = winner;
    }
}

package com.debbache.catmash.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
}

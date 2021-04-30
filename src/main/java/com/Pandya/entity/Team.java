package com.Pandya.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;


    private String teamName;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private List<Player> playerList;

    private String timeOfNextGame;

    @ManyToOne
    @JoinColumn(name = "id_league")
    @JsonIgnore
    private League league;

    public Team(String teamName) {
        this.teamName = teamName;
    }
}


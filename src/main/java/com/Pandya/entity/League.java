package com.Pandya.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "League.findAll", query = "SELECT l FROM League l")
public class League  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String leagueName;

    @OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
    private List<Team> teamList;
}

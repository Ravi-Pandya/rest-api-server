package com.Pandya.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
@NamedQuery(name = "Player.getByLastName", query = "SELECT p from Player p where p.lastName = :lastName")
@NamedQuery(name = "Player.clearAll", query = "DELETE FROM Player")
public class Player implements Comparable<Player>, Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String lastName;
    private String firstName;

    private Date signedUpDate;

    private  String dateOfBirth;


    @ManyToOne
    @JoinColumn(name = "id_team")
    @JsonIgnore
    private Team team;

    @PrePersist
    void createdAt() {
        this.signedUpDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.signedUpDate = new Date();
    }

    public Player(String lastName, String firstName, String dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Player{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }

    @Override
    public int compareTo(Player o) {
        return signedUpDate.compareTo(o.signedUpDate);
    }
}

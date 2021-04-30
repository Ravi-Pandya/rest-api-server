package com.Pandya.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamUpdateDto {
    private Long id;
    private String teamName;
    private String timeOfNextGame;
    private List<Player> playerList;
}

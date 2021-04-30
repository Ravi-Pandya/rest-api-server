package com.Pandya.service;


import com.Pandya.entity.Player;
import com.Pandya.entity.Team;
import com.Pandya.entity.TeamUpdateDto;

import java.util.List;

public interface TeamService {
    void addToList(Team team);
    Team updateTeam(TeamUpdateDto dto, Team teamToUpdate);
    Team getByTeamID(Long id);
    List<Player> getTeamList(Team team);
    void addPlayerToTeam(Team team, Player player);
}

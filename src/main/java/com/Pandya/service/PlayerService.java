package com.Pandya.service;

import com.Pandya.entity.Player;
import com.Pandya.entity.PlayerUpdateDto;

import java.util.List;

public interface PlayerService {
    void clearList();

    List<Player> getPlayerList();

   // List<Player> getWaitList();

   // List<Player> getByTeams();
    void addToList(Player player);
    void removeFromList(Player player);
    Player getById(Long id);
    Player updatePlayer(PlayerUpdateDto dto, Player playerToUpdate);
}

package com.Pandya.service;

import com.Pandya.entity.*;

public interface LeagueService{
    void addToList(League league);
    League updateLeague(LeagueUpdateDto dto, League leagueToUpdate);
    League getByLeagueId(Long id);
}

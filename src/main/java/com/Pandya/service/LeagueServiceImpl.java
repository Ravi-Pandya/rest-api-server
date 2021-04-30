package com.Pandya.service;

import com.Pandya.entity.League;
import com.Pandya.entity.LeagueUpdateDto;
import com.Pandya.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LeagueServiceImpl implements LeagueService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addToList(League league) {
        em.persist(league);
    }

    @Override
    public League updateLeague(LeagueUpdateDto dto, League leagueToUpdate){
        if (dto.getLeagueName() != null) {
            leagueToUpdate.setLeagueName(dto.getLeagueName());
        }
        if (dto.getTeamList() != null){
            leagueToUpdate.setTeamList(dto.getTeamList());
        }
        em.merge(leagueToUpdate);
        return leagueToUpdate;
    }

    @Override
    public League getByLeagueId(Long id) {
        return em.find(League.class, id);
    }
}

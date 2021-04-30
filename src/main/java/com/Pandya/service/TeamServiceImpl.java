package com.Pandya.service;

import com.Pandya.entity.Player;
import com.Pandya.entity.Team;
import com.Pandya.entity.TeamUpdateDto;
import com.Pandya.service.TeamServiceImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class TeamServiceImpl implements TeamService{

    private static final int MAX_CAPACITY = 27;
    private static final int INITIAL_CAPACITY = 18;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addToList(Team team) {
        em.persist(team);
    }

    @Override
    public Team updateTeam(TeamUpdateDto dto, Team teamToUpdate) {
        if (dto.getTeamName() != null) {
            teamToUpdate.setTeamName(dto.getTeamName());
        }
        if (dto.getTimeOfNextGame() != null) {
            teamToUpdate.setTimeOfNextGame(dto.getTimeOfNextGame());
        }
        if(dto.getPlayerList() != null){
            teamToUpdate.setPlayerList(dto.getPlayerList());
        }
        em.merge(teamToUpdate);
        return teamToUpdate;
    }

    @Override
    public void addPlayerToTeam(Team team, Player player){
        team.getPlayerList().add(player);
    }

    @Override
    public Team getByTeamID(Long id) {
        return em.find(Team.class, id);
    }



    @Override
    public List<Player> getTeamList(Team team) {
        List<Team> teamList = em.createNamedQuery("Team.findAll",Team.class)
                .getResultList();
        return team.getPlayerList().stream()
                .sorted()
                .collect(Collectors.toList());
    }


}

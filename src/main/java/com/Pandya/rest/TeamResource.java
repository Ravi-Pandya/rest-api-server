package com.Pandya.rest;

import com.Pandya.entity.Team;
import com.Pandya.entity.TeamUpdateDto;
import com.Pandya.service.PlayerService;
import com.Pandya.service.TeamService;
import com.Pandya.service.TeamServiceImpl;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/teams")
public class TeamResource {
    @EJB
    private TeamService teamService;

    @EJB
    private PlayerService playerService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Team Service is working").build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createTeam(Team team) {
        teamService.addToList(team);
        return Response.status(Response.Status.CREATED).entity(team).build();
    }

    @POST
    @Path("/{id}/{id0}")
    @Produces(TEXT_PLAIN)
    public Response addPlayerToTheTeam(@PathParam("id") long id, @PathParam("id0") long id0)  {
        teamService.addPlayerToTeam(teamService.getByTeamID(id), playerService.getById(id0));
        return Response.ok().entity("Player added to Team").build();
    }

    @GET
    @Path("/get/{id1}")
    @Produces({APPLICATION_JSON})
    public Response getAllTeams(@PathParam("id1") long id1) {
        return Response.ok().entity(teamService.getTeamList(teamService.getByTeamID(id1))).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateTeam(TeamUpdateDto updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Team teamToUpdate = teamService.getByTeamID(updateDto.getId());
        if (teamToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(teamService.updateTeam(updateDto, teamToUpdate)).build();
    }

}

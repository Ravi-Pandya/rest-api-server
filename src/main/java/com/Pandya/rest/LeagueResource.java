package com.Pandya.rest;

import com.Pandya.entity.League;
import com.Pandya.entity.LeagueUpdateDto;
import com.Pandya.entity.Team;
import com.Pandya.entity.TeamUpdateDto;
import com.Pandya.service.LeagueService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/leagues")
public class LeagueResource {

    @EJB
    private LeagueService leagueService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("League Service is working").build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createLeague(League league) {
        leagueService.addToList(league);
        return Response.status(Response.Status.CREATED).entity(league).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updateTeam(LeagueUpdateDto updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        League leagueToUpdate = leagueService.getByLeagueId(updateDto.getId());
        if (leagueToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(leagueService.updateLeague(updateDto, leagueToUpdate)).build();
    }

}

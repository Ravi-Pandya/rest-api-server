package com.Pandya.rest;

import com.Pandya.entity.Player;
import com.Pandya.entity.PlayerUpdateDto;
import com.Pandya.service.PlayerService;
import com.Pandya.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @EJB
    private TeamService teamService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Player Service is working").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") long id) {
        playerService.removeFromList(playerService.getById(id));
        return Response.ok().entity("player deleted").build();
    }


    @GET
    @Produces({APPLICATION_JSON})
    public Response getPlayersByTeam() {
        return Response.ok().entity(playerService.getPlayerList()).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(PlayerUpdateDto updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Player playerToUpdate = playerService.getById(updateDto.getId());
        if (playerToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(playerService.updatePlayer(updateDto, playerToUpdate)).build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        playerService.addToList(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }
}

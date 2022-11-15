package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.WardDto;
import com.khoders.ehrs.services.WardServices;
import com.khoders.resource.jaxrs.JaxResponse;
import com.khoders.resource.utilities.Msg;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 
 */
@Path(ApiEndpoint.ROOM_ENDPOINT)
public class WardController {
    private static final Logger log = LoggerFactory.getLogger(WardController.class);
    @Inject private WardServices roomService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,WardDto dto){
        log.debug("Room saving...");
        WardDto wardDto = roomService.save(dto, userAccountId);
        return JaxResponse.created(Msg.CREATED, wardDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,WardDto dto){
        log.debug("Room updating...");
        WardDto roomDto = roomService.save(dto, userAccountId);
        return JaxResponse.created(Msg.UPDATED, roomDto);
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("roomId") String roomId){
        log.debug("Room updating...");
        WardDto depDto = roomService.findById(roomId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("Room listing...");
        List<WardDto> dtoList = roomService.roomList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("roomId") String roomId){
        log.debug("Room deleting...");
        if(roomService.delete(roomId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could not delete room", false);
    }
}

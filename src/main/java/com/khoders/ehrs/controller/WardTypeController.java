package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.WardTypeDto;
import com.khoders.ehrs.services.WardTypeService;
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
@Path(ApiEndpoint.ROOM_TYPE_ENDPOINT)
public class WardTypeController {
    private static final Logger log = LoggerFactory.getLogger(WardTypeController.class);
    @Inject private WardTypeService roomTypeService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,WardTypeDto dto){
        log.debug("RoomType saving...");
        WardTypeDto wardTypeDto = roomTypeService.save(dto, userAccountId);
        return JaxResponse.created(Msg.CREATED, wardTypeDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,WardTypeDto dto){
        log.debug("RoomType updating...");
        WardTypeDto wardTypeDto = roomTypeService.save(dto, userAccountId);
        return JaxResponse.created(Msg.UPDATED, wardTypeDto);
    }
    
    @GET
    @Path("/{roomTypeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("roomTypeId") String roomTypeId){
        log.debug("RoomType updating...");
        WardTypeDto depDto = roomTypeService.findById(roomTypeId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("RoomType listing...");
        List<WardTypeDto> dtoList = roomTypeService.roomTypeList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{roomTypeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("roomTypeId") String roomTypeId){
        log.debug("RoomType deleting...");
        if(roomTypeService.delete(roomTypeId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could not delete roomType", false);
    }
}

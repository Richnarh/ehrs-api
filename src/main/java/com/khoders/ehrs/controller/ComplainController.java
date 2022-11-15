package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.patient.ComplainDto;
import com.khoders.ehrs.services.ComplainsService;
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
@Path(ApiEndpoint.COMPLAIN_ENDPOINT)
public class ComplainController {
    private static final Logger log = LoggerFactory.getLogger(ComplainController.class);
    @Inject private ComplainsService complainsService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,ComplainDto dto){
        log.info("Complain saving...");
        ComplainDto complainDto = complainsService.save(dto, userAccountId);
        return JaxResponse.created(Msg.CREATED, complainDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,ComplainDto dto){
        log.info("Complain updating...");
        ComplainDto complainDto = complainsService.save(dto, userAccountId);
        return JaxResponse.ok(Msg.UPDATED, complainDto);
    }
    
    @GET
    @Path("/{complainId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("complainId") String complainId){
        log.info("Complain updating...");
        ComplainDto depDto = complainsService.findById(complainId);
        return JaxResponse.ok(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.info("Complain listing...");
        List<ComplainDto> dtoList = complainsService.complainList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{complainId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("complainId") String complainId){
        log.info("Complain deleting...");
        if(complainsService.delete(complainId))
            return JaxResponse.ok(Msg.DELETED, true);
        return JaxResponse.error("Could not delete complain", false);
    }
}

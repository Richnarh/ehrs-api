package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.LabResultDto;
import com.khoders.ehrs.services.LabResultService;
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
@Path(ApiEndpoint.LAB_RESULT_ENDPOINT)
public class LabResultController {
    private static final Logger log = LoggerFactory.getLogger(LabResultController.class);
    @Inject private LabResultService labResultService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,@PathParam("patientId") String patientId,LabResultDto dto){
        log.debug("LabResult saving...");
        LabResultDto labResultDto = labResultService.save(dto, patientId,userAccountId);
        return JaxResponse.created(Msg.CREATED, labResultDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,@PathParam("patientId") String patientId, LabResultDto dto){
        log.debug("LabResult updating...");
        LabResultDto labResultDto = labResultService.save(dto,patientId, userAccountId);
        return JaxResponse.created(Msg.UPDATED, labResultDto);
    }
    
    @GET
    @Path("/{labResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("labResultId") String labResultId){
        log.debug("LabResult updating...");
        LabResultDto depDto = labResultService.findById(labResultId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/{labTestId}/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("patientId") String patientId, @PathParam("labTestId") String labTestId){
        log.debug("LabResult listing...");
        LabResultDto dtoList = labResultService.labResultList(patientId,labTestId);
        if(dtoList == null){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, new LabResultDto());
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{labResultId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("labResultId") String labResultId){
        log.debug("LabResult deleting...");
        if(labResultService.delete(labResultId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could not delete labResult", false);
    }
}

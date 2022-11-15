package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.LabTestDto;
import com.khoders.ehrs.services.LabTestService;
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
@Path(ApiEndpoint.LAB_TEST_ENDPOINT)
public class LabTestController {
    private static final Logger log = LoggerFactory.getLogger(LabTestController.class);
    @Inject private LabTestService labTestService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId, @HeaderParam("employeeId") String employeeId,LabTestDto dto){
        log.debug("LabTest saving...");
        LabTestDto labTestDto = labTestService.save(dto, userAccountId,employeeId);
        return JaxResponse.created(Msg.CREATED, labTestDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId, @HeaderParam("employeeId") String employeeId,LabTestDto dto){
        log.debug("LabTest updating...");
        LabTestDto labTestDto = labTestService.save(dto, userAccountId,employeeId);
        return JaxResponse.ok(Msg.UPDATED, labTestDto);
    }
    
    @GET
    @Path("/{labTestId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("labTestId") String labTestId){
        log.debug("LabTest updating...");
        LabTestDto depDto = labTestService.findById(labTestId);
        return JaxResponse.ok(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("LabTest listing...");
        List<LabTestDto> dtoList = labTestService.labTestList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{labTestId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("labTestId") String labTestId){
        log.debug("LabTest deleting...");
        if(labTestService.delete(labTestId))
            return JaxResponse.ok(Msg.DELETED, true);
        return JaxResponse.error("Could not delete labTest", false);
    }
}

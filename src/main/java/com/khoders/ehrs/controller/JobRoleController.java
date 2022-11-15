package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.JobRoleDto;
import com.khoders.ehrs.services.JobRoleService;
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
@Path(ApiEndpoint.JOB_ROLE_ENDPOINT)
public class JobRoleController {
    private static final Logger log = LoggerFactory.getLogger(JobRoleController.class);
    @Inject private JobRoleService jobRoleService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,JobRoleDto dto){
        log.debug("JobRole saving...");
        JobRoleDto jobRoleDto = jobRoleService.save(dto,userAccountId);
        return JaxResponse.created(Msg.CREATED, jobRoleDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,JobRoleDto dto){
        log.debug("JobRole updating...");
        JobRoleDto jobRoleDto = jobRoleService.save(dto,userAccountId);
        return JaxResponse.created(Msg.UPDATED, jobRoleDto);
    }
    
    @GET
    @Path("/{jobRoleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("jobRoleId") String jobRoleId){
        log.debug("JobRole updating...");
        JobRoleDto depDto = jobRoleService.findById(jobRoleId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("JobRole listing...");
        List<JobRoleDto> dtoList = jobRoleService.jobRoleList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{jobRoleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("jobRoleId") String jobRoleId){
        log.debug("JobRole deleting...");
        if(jobRoleService.delete(jobRoleId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could not delete jobRole", false);
    }
}

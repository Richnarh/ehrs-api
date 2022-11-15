package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.EmployeeDto;
import com.khoders.ehrs.services.EmployeeService;
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
@Path(ApiEndpoint.EMPLOYEE_ENDPOINT)
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Inject private EmployeeService employeeService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,EmployeeDto dto){
        log.debug("Employee saving...");
        EmployeeDto employeeDto = employeeService.save(dto,userAccountId);
        return JaxResponse.created(Msg.CREATED, employeeDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,EmployeeDto dto){
        log.debug("Employee updating...");
        EmployeeDto employeeDto = employeeService.save(dto,userAccountId);
        return JaxResponse.created(Msg.UPDATED, employeeDto);
    }
    
    @GET
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("employeeId") String employeeId){
        log.debug("Employee updating...");
        EmployeeDto depDto = employeeService.findById(employeeId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("Employee listing...");
        List<EmployeeDto> dtoList = employeeService.employeeList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("employeeId") String employeeId){
        log.debug("Employee deleting...");
        if(employeeService.delete(employeeId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete employee", false);
    }
}

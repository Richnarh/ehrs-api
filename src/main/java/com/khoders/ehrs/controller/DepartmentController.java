/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;
import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.DepartmentDto;
import com.khoders.ehrs.services.DepartmentService;
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
 * @author richa
 */
@Path(ApiEndpoint.DEPARTMENT_ENDPOINT)
public class DepartmentController
{
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
    @Inject private DepartmentService departmentService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId, DepartmentDto dto){
        log.debug("Deparment saving...");
        DepartmentDto depDto = departmentService.save(dto,userAccountId);
        return JaxResponse.created(Msg.CREATED, depDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId, DepartmentDto dto){
        log.debug("Deparment updating...");
        DepartmentDto depDto = departmentService.save(dto,userAccountId);
        return JaxResponse.created(Msg.UPDATED, depDto);
    }
    
    @GET
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("departmentId") String departmentId){
        log.debug("Deparment updating...");
        DepartmentDto depDto = departmentService.findById(departmentId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.debug("Department listing...");
        List<DepartmentDto> dtoList = departmentService.departmentList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("departmentId") String departmentId){
        log.debug("Deparment deleting...");
        if(departmentService.delete(departmentId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete department", false);
    }
}

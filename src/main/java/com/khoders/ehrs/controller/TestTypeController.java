package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.TestTypeDto;
import com.khoders.ehrs.services.TestTypeService;
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
@Path(ApiEndpoint.TEST_TYPE_ENDPOINT)
public class TestTypeController {
    private static final Logger log = LoggerFactory.getLogger(TestTypeController.class);
    @Inject private TestTypeService testTypeService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,TestTypeDto dto){
        log.info("TestType saving...");
        TestTypeDto wardTypeDto = testTypeService.save(dto, userAccountId);
        return JaxResponse.created(Msg.CREATED, wardTypeDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,TestTypeDto dto){
        log.info("TestType updating...");
        TestTypeDto wardTypeDto = testTypeService.save(dto, userAccountId);
        return JaxResponse.ok(Msg.UPDATED, wardTypeDto);
    }
    
    @GET
    @Path("/{roomTypeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("roomTypeId") String roomTypeId){
        log.info("TestType updating...");
        TestTypeDto depDto = testTypeService.findById(roomTypeId);
        return JaxResponse.ok(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.info("TestType listing...");
        List<TestTypeDto> dtoList = testTypeService.roomTypeList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{roomTypeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("roomTypeId") String roomTypeId){
        log.info("TestType deleting...");
        if(testTypeService.delete(roomTypeId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could not delete testType", false);
    }
}

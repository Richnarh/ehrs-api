package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.patient.PatientAdmissionDto;
import com.khoders.ehrs.services.PatientAdmissionService;
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
@Path(ApiEndpoint.PATIENT_ADMISSION_ENDPOINT)
public class PatientAdmissionController {
    private static final Logger log = LoggerFactory.getLogger(PatientAdmissionController.class);
    @Inject private PatientAdmissionService patientAdmissionService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,PatientAdmissionDto dto, @PathParam("patientId") String patientId){
        log.info("PatientAdmission saving...");
        PatientAdmissionDto patientAdmissionDto = patientAdmissionService.save(dto,userAccountId,patientId);
        return JaxResponse.created(Msg.CREATED, patientAdmissionDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,PatientAdmissionDto dto, @PathParam("patientId") String patientId){
        log.info("PatientAdmission updating...");
        PatientAdmissionDto patientAdmissionDto = patientAdmissionService.save(dto,userAccountId,patientId);
        return JaxResponse.created(Msg.UPDATED, patientAdmissionDto);
    }
    
    @GET
    @Path("/{patientAdmissionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("patientId") String patientId,@PathParam("patientAdmissionId") String patientAdmissionId){
        log.info("PatientAdmission updating...");
        PatientAdmissionDto depDto = patientAdmissionService.findById(patientId,patientAdmissionId);
        return JaxResponse.created(Msg.RECORD_FOUND, depDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("patientId") String patientId){
        log.info("PatientAdmission listing...");
        List<PatientAdmissionDto> dtoList = patientAdmissionService.patientAdmissionList(patientId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{patientAdmissionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("patientId") String patientId,@PathParam("patientAdmissionId") String patientAdmissionId){
        log.debug("PatientAdmission deleting...");
        if(patientAdmissionService.delete(patientId,patientAdmissionId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete patientAdmission", false);
    }
}

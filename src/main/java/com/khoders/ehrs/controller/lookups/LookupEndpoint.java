/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller.lookups;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.commons.AssignPatient;
import com.khoders.ehrs.commons.LeaveStatus;
import com.khoders.ehrs.commons.PatientCategory;
import com.khoders.ehrs.commons.PatientType;
import com.khoders.ehrs.commons.RoomStatus;
import com.khoders.ehrs.commons.Source;
import com.khoders.resource.enums.ClientType;
import com.khoders.resource.enums.Gender;
import com.khoders.resource.enums.IdType;
import com.khoders.resource.enums.Title;
import com.khoders.resource.jaxrs.JaxResponse;
import com.khoders.resource.utilities.Msg;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author richa
 */
@Path(ApiEndpoint.LOOKUP_ENDPOINT)
public class LookupEndpoint
{
    @Inject private LookupService lookupService;
    
// Enums
    @GET
    @Path("/leave-status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leaveStatus(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(LeaveStatus.values()));
    }
    
    @GET
    @Path("/patient-category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response patientCategory(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(PatientCategory.values()));
    }
    
    @GET
    @Path("/patient-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response patientType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(PatientType.values()));
    }
    
    @GET
    @Path("/gender")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gender(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(Gender.values()));
    }
    
    @GET
    @Path("/room-status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response roomStatus(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(RoomStatus.values()));
    }
    
    @GET
    @Path("/title")
    @Produces(MediaType.APPLICATION_JSON)
    public Response title(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(Title.values()));
    }
    
    @GET
    @Path("/id-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response idType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(IdType.values()));
    }
    @GET
    @Path("/client-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clientType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(ClientType.values()));
    }
    @GET
    @Path("/assign-patient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignPatient(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(AssignPatient.values()));
    }
    @GET
    @Path("/source")
    @Produces(MediaType.APPLICATION_JSON)
    public Response source(){
        return JaxResponse.ok(Msg.RECORD_FOUND, LookupSetup.PrepareEnum(Source.values()));
    }
    
// Entities
    @GET
    @Path("/department")
    @Produces(MediaType.APPLICATION_JSON)
    public Response department(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.department());
    }
    
    @GET
    @Path("/leave-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response leaveType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.leaveType());
    }
    
    @GET
    @Path("/room-type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response roomType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.roomType());
    }
    
    @GET
    @Path("/job-role")
    @Produces(MediaType.APPLICATION_JSON)
    public Response jobRole(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.jobRole());
    }
        
    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response employee(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.employee());
    }
    
    @GET
    @Path("/room")
    @Produces(MediaType.APPLICATION_JSON)
    public Response room(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.room());
    }
    
    @GET
    @Path("/lab")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lab(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.lab());
    }
    @GET
    @Path("/testtype")
    @Produces(MediaType.APPLICATION_JSON)
    public Response testType(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.testType());
    }
    
    @GET
    @Path("/labtest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response labTest(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.labTest());
    }
    @GET
    @Path("/patient")
    @Produces(MediaType.APPLICATION_JSON)
    public Response patient(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.patient());
    }
    @GET
    @Path("/frequency")
    @Produces(MediaType.APPLICATION_JSON)
    public Response frequency(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.frequency());
    }
    @GET
    @Path("/dr-report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drReport(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.drReport());
    }
    @GET
    @Path("/stock")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stock(){
        return JaxResponse.ok(Msg.RECORD_FOUND, lookupService.stock());
    }
}

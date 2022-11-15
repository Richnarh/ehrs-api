package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.patient.AssignDrDto;
import com.khoders.ehrs.payload.patient.DrReportDto;
import com.khoders.ehrs.payload.patient.PatientDto;
import com.khoders.ehrs.payload.patient.PatientVitalDto;
import com.khoders.ehrs.payload.patient.PrescriptionDto;
import com.khoders.ehrs.services.PatientService;
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
@Path(ApiEndpoint.PATIENT_ENDPOINT)
public class PatientController {
    private static final Logger log = LoggerFactory.getLogger(PatientController.class);
    @Inject private PatientService patientService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId,PatientDto dto){
        log.info("Patient saving...");
        PatientDto patientDto = patientService.save(dto, userAccountId);
        return JaxResponse.created(Msg.CREATED, patientDto);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId,PatientDto dto){
        log.info("Patient updating...");
        PatientDto patientDto = patientService.save(dto, userAccountId);
        return JaxResponse.created(Msg.UPDATED, patientDto);
    }
    
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("patientId") String patientId){
        log.info("Patient updating...");
        PatientDto patientDto = patientService.findById(patientId);
        return JaxResponse.created(Msg.RECORD_FOUND, patientDto);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.info("Patient listing...");
        List<PatientDto> dtoList = patientService.patientList();
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    
    @DELETE
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("patientId") String patientId){
        log.info("Patient deleting...");
        if(patientService.delete(patientId))
            return JaxResponse.ok(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete patient", false);
    }
    
    @GET
    @Path("/search/{opdSearchField}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPatient(@PathParam("opdSearchField") String opdSearchField){
        log.info("finding patient listing...");
        PatientDto patientDto = patientService.patientDetails(opdSearchField);
        return JaxResponse.ok(Msg.RECORD_FOUND, patientDto);
    } 
    
    // Patient vitals
    @POST
    @Path("/{patientId}/vital")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, PatientVitalDto dto){
        log.info("create patient vital...");
        PatientVitalDto pdto = patientService.save(userAccountId, dto, patientId);
        return JaxResponse.created(Msg.CREATED, pdto);
    } 
    
    @PUT
    @Path("/{patientId}/vital")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, PatientVitalDto dto){
        log.info("update patient vital...");
        PatientVitalDto pdto = patientService.save(userAccountId, dto, patientId);
        return JaxResponse.created(Msg.UPDATED, pdto);
    } 
    
    @GET
    @Path("/{patientId}/vital/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("patientId") String patientId){
        log.info("find all patient vital...");
        List<PatientVitalDto> dtoList = patientService.patientVitalList(patientId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    } 
    
    @GET
    @Path("/{patientId}/vital/{vitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("patientId") String patientId, @PathParam("vitalId") String vitalId){
        log.info("find single patient vital...");
        PatientVitalDto vitalDto = patientService.findById(patientId,vitalId);
        return JaxResponse.ok(Msg.RECORD_FOUND, vitalDto);
    } 
    
    @DELETE
    @Path("/{patientId}/vital/{vitalId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("patientId") String patientId, @PathParam("vitalId") String vitalId){
        log.info("delete single patient vital...");
        if(patientService.delete(patientId,vitalId))
            return JaxResponse.ok(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete patient vital", false);
    } 
    
    // Assign to Dr
    @POST
    @Path("/{patientId}/assign-dr")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, AssignDrDto dto){
        log.info("create AssignDr...");
        System.out.println("userAccountId --- "+userAccountId);
        AssignDrDto drDto = patientService.save(userAccountId, dto, patientId);
        return JaxResponse.created(Msg.CREATED, drDto);
    }
    @PUT
    @Path("/{patientId}/assign-dr")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, AssignDrDto dto){
        log.info("update AssignDr...");
        AssignDrDto drDto = patientService.save(userAccountId, dto, patientId);
        return JaxResponse.created(Msg.UPDATED, drDto);
    }
    @GET
    @Path("/{patientId}/assign-dr/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAssignedDr(@PathParam("patientId") String patientId){
        log.info("find all assigned dr list...");
        List<AssignDrDto> dtoList = patientService.assingDrList(patientId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    @GET
    @Path("/{patientId}/assign-dr/{assignDrId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByAssignDr(@PathParam("patientId") String patientId, @PathParam("assignDrId") String assignDrId){
        log.info("find single patient vital...");
        AssignDrDto drDto = patientService.finByAssignedDrId(patientId,assignDrId);
        return JaxResponse.ok(Msg.RECORD_FOUND, drDto);
    } 
    @GET
    @Path("/assign-dr/{doctorId}/doctor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadPatientAssignedToDr(@PathParam("doctorId") String doctorId){
        log.info("find list of patients assigned to doctor...");
        List<AssignDrDto> dtoList = patientService.loadPatientsForDr(doctorId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    } 
    
    @DELETE
    @Path("/{patientId}/assign-dr/{assignDrId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDr(@PathParam("patientId") String patientId, @PathParam("assignDrId") String assignDrId){
        log.info("delete single assignDr...");
        if(patientService.deleteAsDr(patientId,assignDrId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete assignDr", false);
    } 
    
    // Prescription
    @POST
    @Path("/{patientId}/prescription")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("employeeId") String employeeId, @HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, PrescriptionDto dto){
        log.info("create Prescription...");
        PrescriptionDto prescriptionDto = patientService.save(userAccountId,employeeId, dto, patientId);
        return JaxResponse.created(Msg.CREATED, prescriptionDto);
    }
    @PUT
    @Path("/{patientId}/prescription")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("employeeId") String employeeId, @HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, PrescriptionDto dto){
        log.info("update Prescription...");
        PrescriptionDto prescriptionDto = patientService.save(userAccountId,employeeId, dto, patientId);
        return JaxResponse.created(Msg.UPDATED, prescriptionDto);
    }
    @GET
    @Path("/{patientId}/prescription/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPrescription(@HeaderParam("employeeId") String employeeId, @PathParam("patientId") String patientId){
        log.info("find all prescription by dr ...");
        List<PrescriptionDto> dtoList = patientService.prescriptionList(patientId,employeeId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    @GET
    @Path("/{patientId}/prescription/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPrescription(@PathParam("patientId") String patientId, @PathParam("prescriptionId") String prescriptionId){
        log.info("find single Prescription...");
        PrescriptionDto prescriptionDto = patientService.findByPrescription(patientId,prescriptionId);
        return JaxResponse.ok(Msg.RECORD_FOUND, prescriptionDto);
    } 
    
    @DELETE
    @Path("/{patientId}/prescription/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePrescription(@PathParam("patientId") String patientId, @PathParam("prescriptionId") String prescriptionId){
        log.info("delete single prescription...");
        if(patientService.deletePrescription(patientId,prescriptionId))
            return JaxResponse.created(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete prescription", false);
    } 
    
    // Dr Report
    @POST
    @Path("/{patientId}/drreport")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@HeaderParam("employeeId") String employeeId, @HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, DrReportDto dto){
        log.info("create DrReport...");
        System.out.println("userAccountId --- "+userAccountId);
        DrReportDto reportDto = patientService.save(userAccountId,employeeId, dto, patientId);
        return JaxResponse.created(Msg.CREATED, reportDto);
    }
    @PUT
    @Path("/{patientId}/drreport")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@HeaderParam("employeeId") String employeeId, @HeaderParam("userAccountId") String userAccountId, @PathParam("patientId") String patientId, DrReportDto dto){
        log.info("update DrReport...");
        DrReportDto reportDto = patientService.save(userAccountId,employeeId, dto, patientId);
        return JaxResponse.created(Msg.UPDATED, reportDto);
    }
    @GET
    @Path("/{patientId}/drreport/{complainId}/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findDrReport(@PathParam("patientId") String patientId, @PathParam("complainId") String complainId){
        log.info("find all DrReport list...");
        List<DrReportDto> dtoList = patientService.drReportList(patientId,complainId);
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
    @GET
    @Path("/{patientId}/drreport/{drReportId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDrReportId(@PathParam("patientId") String patientId, @PathParam("drReportId") String drReportId){
        log.info("find single DrReport...");
        DrReportDto reportDto = patientService.findByDrReport(patientId,drReportId);
        return JaxResponse.ok(Msg.RECORD_FOUND, reportDto);
    } 
    
    @DELETE
    @Path("/{patientId}/drreport/{drReportId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDrReport(@PathParam("patientId") String patientId, @PathParam("drReportId") String drReportId){
        log.info("delete single report...");
        if(patientService.deleteDrReport(patientId,drReportId))
            return JaxResponse.ok(Msg.DELETED, true);
        return JaxResponse.error("Could Not Delete Dr report", false);
    } 

}

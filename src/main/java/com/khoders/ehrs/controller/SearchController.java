/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.AppModuleDto;
import com.khoders.ehrs.payload.LabTestDto;
import com.khoders.ehrs.payload.patient.BillingDto;
import com.khoders.ehrs.services.SearchService;
import com.khoders.resource.jaxrs.JaxResponse;
import com.khoders.resource.utilities.Msg;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path(ApiEndpoint.SEARCH_ENDPOINT)
public class SearchController
{
    private static final Logger log = LoggerFactory.getLogger(SearchController.class);
    @Inject private SearchService searchService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{testDate}/{opdSearchField}")
    public Response searchData(@PathParam("testDate") String testDate, @PathParam("opdSearchField") String opdSearchField){
        log.info("Seaching for patient lab details");
         List<LabTestDto> dtoList = searchService.labTestData(testDate, opdSearchField.trim());
        if(dtoList.isEmpty()){
            return JaxResponse.ok(Msg.RECORD_NOT_FOUND, dtoList);
        }
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{opdSearchField}")
    public Response searchBills(@PathParam("opdSearchField") String opdSearchField){
        log.info("Seaching billing info");
        List<BillingDto> dtoList = searchService.findBills(opdSearchField);
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{appModule}/pages")
    public Response getPages(@HeaderParam("userAccountId") String userAccountId, @PathParam("appModule") String appModule){
        log.info("loading pages...");
        List<AppModuleDto> dtoList = this.searchService.loadPages(userAccountId, appModule);
        return JaxResponse.ok(Msg.RECORD_FOUND, dtoList);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;

import com.khoders.ehrs.ApiEndpoint;
import com.khoders.ehrs.payload.AuthRequest;
import com.khoders.ehrs.payload.AuthResponse;
import com.khoders.ehrs.payload.UserAccountDto;
import com.khoders.ehrs.services.AuthService;
import com.khoders.resource.jaxrs.JaxResponse;
import com.khoders.resource.utilities.Msg;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pascal
 */
@Path(ApiEndpoint.AUTH_ENDPOINT)
public class AuthController{
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Inject private AuthService authService;
    
    @POST
    @Path(value="/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserAccountDto dto){
        log.info("Signing up...");
        AuthResponse authResponse = authService.register(dto);
        return JaxResponse.created(Msg.CREATED, authResponse);
    }
    
    @POST
    @Path(value="/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doLogin(AuthRequest authRequest){
        log.info("Signing in...");
        AuthResponse authResponse = authService.doLogin(authRequest);
        return JaxResponse.ok(Msg.RECORD_FOUND, authResponse);
    }
}

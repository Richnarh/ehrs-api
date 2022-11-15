/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs.controller;

import com.khoders.ehrs.services.PatientService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author richa
 */
public class PatientFinderController
{
    private static final Logger log = LoggerFactory.getLogger(PatientFinderController.class);
    @Inject private PatientService patientService;
    
    
}

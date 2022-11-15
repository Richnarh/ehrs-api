/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoders.ehrs;

/**
 *
 * @author pascal
 */
public class ApiEndpoint
{
    private static final String VERSION = "v1/";
    public static final String STAFF = VERSION + "staff";
    public static final String LOOKUP_ENDPOINT = VERSION + "data";
    
    public static final String AUTH_ENDPOINT = VERSION + "auth";
    public static final String DEPARTMENT_ENDPOINT = VERSION + "department";
    public static final String EMPLOYEE_ENDPOINT = VERSION + "employee";
    public static final String PATIENT_ENDPOINT = VERSION + "patient";
    public static final String ROOM_ENDPOINT = VERSION + "room";
    public static final String ROOM_TYPE_ENDPOINT = VERSION + "room-type";
    public static final String TEST_TYPE_ENDPOINT = VERSION + "testtype";
    
    public static final String INVENTORY_ENDPOINT = VERSION + "inventory";
    public static final String SEARCH_ENDPOINT = VERSION + "search";
    
    public static final String CUSTOMER_ENDPOINT = VERSION + "customer";
    public static final String LAB_ENDPOINT = VERSION + "lab";
    public static final String LAB_RESULT_ENDPOINT = PATIENT_ENDPOINT+"/{patientId}/lab-result";
    public static final String LAB_TEST_ENDPOINT = PATIENT_ENDPOINT+"/{patientId}/labtest";
    
    public static final String BILLING_ENDPOINT = PATIENT_ENDPOINT+"/{patientId}/billing";
    public static final String JOB_ROLE_ENDPOINT = VERSION + "job-role";
    
    public static final String FREQUENCY_ENDPOINT = VERSION + "frequency";
    
    public static final String COMPLAIN_ENDPOINT = PATIENT_ENDPOINT+"/{patientId}/complain";
    public static final String PATIENT_ADMISSION_ENDPOINT = PATIENT_ENDPOINT+"/{patientId}/admission";
}

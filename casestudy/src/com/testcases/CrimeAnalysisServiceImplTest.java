package com.testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Date;

import org.junit.jupiter.api.*;

import com.dao.CrimeAnalysisServiceImpl;
import com.entity.Incident;
import com.util.DBConnUtil;

class CrimeAnalysisServiceImplTest {

    private static CrimeAnalysisServiceImpl crimeService;

    @BeforeAll
    public static void setup() {
        crimeService = new CrimeAnalysisServiceImpl();
    }

    @Test
    void testConnection() {
        Connection actualValue = DBConnUtil.getConnection();
        assertNotNull(actualValue, "DB Connection is a Failure");
    }

    @Test
    void testCreateIncident() {
        Incident incident = new Incident(
            420,                                  
            "Robbery",                            
            new Date(),                           
            13.082680,                           
            80.270718,                            
            "Test robbery case at T Nagar",       
            "Open",                               
            201,                                  
            301,                                  
            101                                   
        );

        boolean result = crimeService.createIncident(incident);
        assertTrue(result, "Incident creation failed in the database.");
    }
}

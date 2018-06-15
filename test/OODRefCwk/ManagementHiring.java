/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OODRefCwk;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Provides tests for the basic BITS setup
 * @author comqaam
 */
public class ManagementHiring {
    
Management pr;
    public ManagementHiring() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pr = new ITManager("Olenka",1000);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void hireStaff(){
        int expected = 700;
        pr.hireStaff("Alan");
        assertTrue(expected == pr.getAccount());
    }
    
    @Test
    public void hiredStaffInTeam() {
        int expected = 600;
        pr.hireStaff("Alan");
        pr.hireStaff("Bob");
        boolean budgetOK = (expected == pr.getAccount());
        assertTrue(pr.isInTeam("Alan") && pr.isInTeam("Bob")&& budgetOK);
    }
    
    @Test
    public void hiredStaffNotAvailable() {
        boolean result = true;
        pr.hireStaff("Alan");
        pr.hireStaff("Bob");
        String actual = pr.getAvailableStaff();
        System.out.println("********** THIS ONE HERE");
        System.out.println(actual);
        result = !(actual.contains("Alan") || actual.contains("Bob"));
        assertTrue(result);
    }
    
    @Test 
    public void notEnoughMoney() {
        pr.hireStaff("Alan");
        pr.hireStaff("Gita");
        pr.hireStaff("Hela");
        pr.hireStaff("Fela");
        boolean result = (pr.getAccount()==50);
        result = result && !pr.isInTeam("Fela");
        assertTrue(result);
    }
    
    @Test 
    public void notSuchStaff() {
        pr.hireStaff("John");
        boolean result = (pr.getAccount()==1000);
        result = result && !pr.isInTeam("John");
        assertTrue(result);
    }
}

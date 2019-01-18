package com.att.tlv.training.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

// MUST be a public class!
public class JunitBasics {
    
    // MUST be a public method!
    @Test
    public void test1() {
        System.out.println("Running test1!");
    }
    
    @Test
    public void test2() {
        System.out.println("Running test2!");
    }
    
    
    
    // Set up and tear down methods:
    
    // MUST be a public static method!
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Running set up before class...");
    }
    
    // MUST be a public static method!
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Running tear down after class...");
    }

    // MUST be a public method!
    // Multiple methods are supported but order is not guaranteed - so use only one!
    @Before
    public void setUp() {
        System.out.println("Running set up before test...");
    }
    
    // MUST be a public method!
    @After
    public void tearDown() {
        System.out.println("Running tear down after test...");
    }
    
}

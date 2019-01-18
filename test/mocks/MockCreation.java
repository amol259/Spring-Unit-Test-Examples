package com.att.tlv.training.test.mocks;

import org.junit.Ignore;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.mock;

public class MockCreation {
    
    @SuppressWarnings("unused")
    @Test
    public void create() {
        // We can mock non-generic interfaces
        ExecutorService executorService = mock(ExecutorService.class);
        // We can mock non-generic classes
        Exception exception = mock(Exception.class);
        
        // We can mock generic interfaces
        List<?> list = mock(List.class);   
        // We can mock generic concrete classes as well
        ArrayList<?> arrayList = mock(ArrayList.class);   
    }

    @Ignore
    @SuppressWarnings("unused")
    @Test
    public void cannotCreate() {
        // There are a few types we can't create:
        // Final classes / methods
        String str = mock(String.class);    
        // Enums:
        DayOfWeek dayOfWeek = mock(DayOfWeek.class); 
        
        /*
         * Actually, starting from Mockito v2.1.0 it IS possible, using a different mock maker that the default one. But it is rarely needed.
         * 
         * This mock maker is turned off by default because it is based on completely different mocking mechanism 
         * that requires more feedback from the community. It can be activated explicitly by the mockito extension
         * mechanism, just create in the classpath a file /mockito-extensions/org.mockito.plugins.MockMaker
         * containing the value mock-maker-inline. 
         * As a convenience, the Mockito team provides an artifact where this mock maker is preconfigured.
         * Instead of using the mockito-core artifact, include the mockito-inline artifact in your project.
         * Note that this artifact is likely to be discontinued once mocking of final classes and methods
         * gets integrated into the default mock maker.
         */
    }
}
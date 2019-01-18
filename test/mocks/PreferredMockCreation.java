package com.att.tlv.training.test.mocks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// Note the runner! 'org.mockito.junit' and not 'org.mockito.runners'
@RunWith(MockitoJUnitRunner.class)
public class PreferredMockCreation {

    @Mock
    private List<String> strings;
    @Mock
    private Map<Long, Map<String, List<String>>> complexMap;
    
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
    
    @SuppressWarnings("unused")
    @Test
    public void test() {
        boolean added = strings.add("hello");
        Map<String, List<String>> previousValue = complexMap.put(10L, Collections.emptyMap());
    }
}
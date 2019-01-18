package com.att.tlv.training.test.mocks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class VerifyingBehavior {

    @Mock
    private List<Integer> numbers;
    @Mock
    private List<String> someOtherMock;

    @Test
    public void basicVerification() {
        numbers.add(1);

        // Verify exact invocation
        verify(numbers).add(1);
        // What if any int will do? ArgumentMatchers to the rescue!
        verify(numbers).add(anyInt());

        try {
            // This will fail of course
            verify(numbers).add(2);
            
            failTest("We should never reach this line!");
        }
        catch (AssertionError ignore) {
            // Do nothing
        }
    }

    @Test
    public void verifyMultipleInvocations() {
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);

        // Verify exact invocation
        verify(numbers).add(1);

        try {
            // This will fail of course
            verify(numbers).add(anyInt());
            // It's short for this:
            verify(numbers, times(1)).add(anyInt());
            
            failTest("We should never reach this line!");
        }
        catch (AssertionError ignore) {
            // Do nothing
        }

        // What we want is to verify the method was called 4 times!
        verify(numbers, times(4)).add(anyInt());
        // Or at least once
        verify(numbers, atLeastOnce()).add(1);
        // Or at least more than x times
        verify(numbers, atLeast(2)).add(2);
        // Or at most x times
        verify(numbers, atMost(10)).add(anyInt());
        // Or never!
        verify(numbers, never()).add(3);
    }
    
    @Test
    public void verifyInvocationOrder() {
        numbers.add(1);
        numbers.add(2);
        
        // We already know this works
        verify(numbers).add(1);
        verify(numbers).add(2);
        
        // But so does this. Order doesn't matter!
        verify(numbers).add(2);
        verify(numbers).add(1);
        
        // But what if  it really does? InOrder at your service:
        InOrder inOrder = inOrder(numbers);
        inOrder.verify(numbers).add(1);
        inOrder.verify(numbers).add(2);
        
        try {
            InOrder inOrder2 = inOrder(numbers);
            inOrder2.verify(numbers).add(2);
            // This will fail, add(1) is expected BEFORE add(2)
            inOrder2.verify(numbers).add(1);
            
            failTest("We should never reach this line!");
        }
        catch (AssertionError ignore) {
            // Do nothing
        }
    }
    
    @Test
    public void verifyNoInteractions() {
        numbers.add(1);

        // Standard stuff
        verify(numbers).add(1);

        // We know this one too
        verify(numbers, never()).add(2);

        // Verify that other mocks were not interacted with
        verifyZeroInteractions(someOtherMock);
    }
    
    private static void failTest(String message) {
        throw new UnsupportedOperationException(message);
    }
}

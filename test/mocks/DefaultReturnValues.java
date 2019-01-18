package com.att.tlv.training.test.mocks;

import com.att.tlv.training.test.data.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DefaultReturnValues {
    
    @Mock
    private Demo demo;
    
    @Test
    public void assertThatReturnedCharIsZero() {
        assertThat(demo.getChar()).isEqualTo('\0');
        assertThat(demo.getChar()).isEqualTo('\u0000');
        assertThat(demo.getChar()).isEqualTo(Character.MIN_VALUE);
        assertThat(demo.getBoxedChar()).isEqualTo('\u0000');
    }
    
    @Test
    public void assertThatReturnedByteIsZero() {
        assertThat(demo.getByte()).isZero();
        assertThat(demo.getBoxedByte()).isZero();
    }
    
    @Test
    public void assertThatReturnedShortIsZero() {
        assertThat(demo.getShort()).isZero();
        assertThat(demo.getBoxedShort()).isZero();
    }
    
    @Test
    public void assertThatReturnedIntIsZero() {
        assertThat(demo.getInt()).isZero();
        assertThat(demo.getBoxedInt()).isZero();
    }
    
    @Test
    public void assertThatReturnedLongIsZero() {
        assertThat(demo.getLong()).isZero();
        assertThat(demo.getBoxedLong()).isZero();
    }
    
    @Test
    public void assertThatReturnedFloatIsZero() {
        assertThat(demo.getFloat()).isZero();
        assertThat(demo.getBoxedFloat()).isZero();
    }
    
    @Test
    public void assertThatReturnedDoubleIsZero() {
        assertThat(demo.getDouble()).isZero();
        assertThat(demo.getBoxedDouble()).isZero();
    }
    
    @Test
    public void assertThatReturnedBooleanIsFalse() {
        assertThat(demo.getBoolean()).isFalse();
        assertThat(demo.getBoxedBoolean()).isFalse();
    }
    
    @Test
    public void assertThatObjectReturnsNull() {
        assertThat(demo.getObject()).isNull();
    }
    
    @Test
    public void assertThatStringReturnsNull() {
        assertThat(demo.getString()).isNull();
    }
    
    @Test
    public void assertThatArrayReturnsNull() {
        assertThat(demo.getObjectArray()).isNull();
    }
    
    @Test
    public void assertThatCollectionReturnsEmptyCollection() {
        assertThat(demo.getCollection()).isEmpty();
        assertThat(demo.getList()).isEmpty();
        assertThat(demo.getSet()).isEmpty();
    }
    
    @Test
    public void assertThatMapReturnsEmptyMap() {
        assertThat(demo.getMap()).isEmpty();
    }
    
    @Test
    public void assertThatStreamReturnsEmptyStream() {
        assertThat(demo.getStream()).isEmpty();
    }
    
    @Test
    public void assertThatOptionalReturnsEmptyOptional() {
        assertThat(demo.getOptional()).isEmpty();
        assertThat(demo.getOptionalInt()).isEmpty();
        assertThat(demo.getOptionalLong()).isEmpty();
        assertThat(demo.getOptionalDouble()).isEmpty();
    }
    
    @Test
    public void assertThatMockedTypeReturnsNull() {
        assertThat(demo.getMe()).isNull();
    }
    
    @Mock(answer = Answers.RETURNS_SELF)
    private Demo fluentDemo;
    @Test
    public void assertThatMockedTypeReturnsItself() {
        assertThat(fluentDemo.getMe()).isSameAs(fluentDemo);
    }
    
    @Mock
    private List<Integer> ints;
    @Test
    public void assertThatGenericParameterTypesAreTreatedAsObject() {
        // Although method get returns an Integer, it actually returns T, whose type is erased.
        // So we get the behavior of List<Object> - which returns null by default.
        Integer value = ints.get(0);
        assertThat(value).isNull();
    }
    
}
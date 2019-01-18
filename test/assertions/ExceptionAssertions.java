package com.att.tlv.training.test.assertions;

import org.assertj.core.api.Condition;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ExceptionAssertions {

    @Test
    public void testBasicExceptionAssertions() {

        List<String> list = newArrayList("zero", "one", "two", "three", "four");

        assertThat(list).hasSize(5);

        try {
            list.get(9); // Boom!
        }
        catch (Exception e) {
            // You can check exception type
            assertThat(e).isInstanceOf(IndexOutOfBoundsException.class);

            // You can check if exception has no cause
            assertThat(e).hasNoCause();

            // You can check exception message
            assertThat(e).hasMessage("Index: 9, Size: 5");

            // String#format syntax support
            assertThat(e).hasMessage("Index: %s, Size: %s", 9, 5);
            
            // Sometimes message are not entirely predictable, you can then check for start, end or containing string.
            assertThat(e).hasMessageStartingWith("Index: 9")
                    .hasMessageContaining("9")
                    .hasMessageEndingWith("Size: 5");
            // This is equivalent to:
            assertThat(e.getMessage()).startsWith("Index: 9")
                    .contains("9")
                    .endsWith("Size: 5");
        }
    }

    @Test
    public void testCauseType() throws Exception {

        Throwable throwable = new Throwable(new NullPointerException("boom"));

        assertThat(throwable).hasCause(new NullPointerException("boom"));

        // hasCauseInstanceOf will match inheritance.
        assertThat(throwable).hasCauseInstanceOf(NullPointerException.class);
        assertThat(throwable).hasCauseInstanceOf(RuntimeException.class);

        // hasCauseExactlyInstanceOf will match only exact same type
        assertThat(throwable).hasCauseExactlyInstanceOf(NullPointerException.class);
        try {
            assertThat(throwable).hasCauseExactlyInstanceOf(RuntimeException.class);
        }
        catch (AssertionError e) {
            e.printStackTrace();
        }

        Throwable throwable_root = new Throwable(new IllegalStateException(new NullPointerException()));

        // hasRootCauseInstanceOf will match inheritance
        assertThat(throwable_root).hasRootCauseInstanceOf(NullPointerException.class);
        assertThat(throwable_root).hasRootCauseInstanceOf(RuntimeException.class);

        // hasRootCauseExactlyInstanceOf will match only exact same type
        assertThat(throwable_root).hasRootCauseExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    public void test_assertThatThrownBy() {
        // @formatter:off
        assertThatThrownBy(() -> { throw new IllegalArgumentException("boom!"); })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("boom");
        // @formatter:on
    }

    @Test
    public void test_assertThatExceptionOfType() {
        // @formatter:off
        assertThatExceptionOfType(IOException.class).isThrownBy(() -> { throw new IOException("boom!"); })
                .withMessage("boom!")
                .withMessageContaining("oom")
                .withMessage("%s!", "boom")
                .withStackTraceContaining("IOException")
                .withNoCause();
        
        // Shortcut for IOException, NullPointerException, IllegalArgumentException, IllegalStateException:
        assertThatIOException().isThrownBy(() -> { throw new IOException("boom!"); })
                .withMessage("boom!");
        // @formatter:on
    }
    
    @Test
    public void test_doesNotThrowAnyException() {
        assertThatCode(() -> doSomething("hello")).doesNotThrowAnyException();
    }
    
    private int doSomething(String s) {
        return s.length();
    }

    @Test
    public void assertException() {
        // A condition accepts a predicate and a description (formatted strings accepted)
        Condition<MyException> aTotalGreaterThan10 = new Condition<>(e -> e.getTotal() > 10, "a total that is greater than 10");

        assertThatExceptionOfType(MyException.class).isThrownBy(this::throwMyException)
                .withMessage("An error occurred")
                // We can use the predicate directly
                .matches(e -> e.getTotal() > 10)
                // Or the condition - assertion failures will use our description
                .has(aTotalGreaterThan10);
    }

    private void throwMyException() throws MyException {
        throw new MyException("An error occurred", 20);
    }

}

class MyException extends Exception {

    private static final long serialVersionUID = 1L;
    private final int total;

    public MyException(String message, int total) {
        super(message);
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
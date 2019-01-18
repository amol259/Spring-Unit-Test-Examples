package com.att.tlv.training.test.assertions;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.OptionalInt;

import static org.assertj.core.api.Assertions.assertThat;

public class MoreAssertions {

    @Test
    public void testNumbers() {
        assertThat(50).isGreaterThanOrEqualTo(49);
        assertThat(50).isGreaterThanOrEqualTo(50);

        assertThat(-1).isNegative();
        assertThat(0).isNotNegative();
        assertThat(0).isNotPositive();
        assertThat(0).isZero();

        // both ends inclusive
        assertThat(10).isBetween(10, 30);
        assertThat(20).isBetween(10, 30);
        assertThat(30).isBetween(10, 30);
    }

    @Test
    public void testStrings() {
        assertThat("Hello").startsWith("Hel")
                .endsWith("lo")
                .hasSize(5);
        assertThat("Hello").contains("lo")
                .doesNotContain("hi");
        assertThat("Hello").containsOnlyOnce("Hell");
        assertThat("Hello").isSubstringOf("HelloWorld");

        assertThat("Hello").usingComparator(String.CASE_INSENSITIVE_ORDER)
                .startsWith("HELL");

        assertThat("3210").containsOnlyDigits();
        assertThat("3210").matches("[1-9][0-9]{3}");
    }

    @Test
    public void testJava8DateAndTime() {
        assertThat(LocalDate.now()).isToday();

        LocalDateTime firstOfJanuary2000 = LocalDateTime.parse("2000-01-01T00:00:00");

        assertThat(firstOfJanuary2000).isEqualTo("2000-01-01T00:00:00");

        assertThat(firstOfJanuary2000).isAfter("1999-12-31T23:59:59")
                .isAfterOrEqualTo("1999-12-31T23:59:59")
                .isAfterOrEqualTo(firstOfJanuary2000);

        assertThat(firstOfJanuary2000).isBefore("2000-01-01T00:00:01")
                .isBeforeOrEqualTo("2000-01-01T00:00:01")
                .isBeforeOrEqualTo(firstOfJanuary2000);

        // successful assertions ignoring ...
        // ... nanoseconds
        LocalDateTime localDateTime1 = LocalDateTime.of(2000, 1, 1, 0, 0, 1, 0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2000, 1, 1, 0, 0, 1, 456);
        assertThat(localDateTime1).isEqualToIgnoringNanos(localDateTime2);
        // ... seconds
        localDateTime1 = LocalDateTime.of(2000, 1, 1, 23, 50, 0, 0);
        localDateTime2 = LocalDateTime.of(2000, 1, 1, 23, 50, 10, 456);
        assertThat(localDateTime1).isEqualToIgnoringSeconds(localDateTime2);

        // ... minutes
        localDateTime1 = LocalDateTime.of(2000, 1, 1, 23, 50, 0, 0);
        localDateTime2 = LocalDateTime.of(2000, 1, 1, 23, 00, 2, 7);
        assertThat(localDateTime1).isEqualToIgnoringMinutes(localDateTime2);

        // ... hours
        localDateTime1 = LocalDateTime.of(2000, 1, 1, 23, 59, 59, 999);
        localDateTime2 = LocalDateTime.of(2000, 1, 1, 00, 00, 00, 000);
        assertThat(localDateTime1).isEqualToIgnoringHours(localDateTime2);
    }

    @Test
    public void testOptionals() {
        Optional<String> optional = Optional.of("Test");
        assertThat(optional).isPresent()
                .containsInstanceOf(String.class)
                .hasValue("Test")
                // Or alias:
                .contains("Test");

        Optional<Object> emptyOptional = Optional.empty();
        assertThat(emptyOptional).isEmpty();

        String someString = "something";
        assertThat(Optional.of(someString)).containsSame(someString);

        // We'd like to call more than just contains("something"):
        assertThat(Optional.of(someString)).hasValueSatisfying(s -> {
            assertThat(s).isEqualTo("something");
            assertThat(s).startsWith("some");
            assertThat(s).endsWith("thing");
        });

        // Specialized optionals
        OptionalInt optionalInt = OptionalInt.of(12);
        assertThat(optionalInt).isPresent()
                .hasValue(12);
    }
}

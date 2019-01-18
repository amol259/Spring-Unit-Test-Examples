package com.att.tlv.training.test.assertions;

import com.att.tlv.training.test.data.Person;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class MapAssertions {

    @Test
    public void mapBasicAssertions() {
        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person alice = new Person(555, "Alice", 30, 1.65d);
        Person carl = new Person(666, "Carl", 20, 1.95d);
        // Map id -> Person
        Map<Long, Person> persons = Stream.of(jim, alice, carl)
                .collect(toMap(Person::getId, Function.identity()));

        assertThat(persons).isNotEmpty()
                .hasSize(3);

        // Note the usage of Assertions.entry(key, value) syntactic sugar for better readability (similar to
        // MapEntry.entry(key, value)).
        assertThat(persons).contains(entry(444L, jim), entry(555L, alice));
        // using java util Map.Entry
        assertThat(persons).contains(javaMapEntry(444L, jim), javaMapEntry(555L, alice));
        
        
        // Same assertion but different way of expressing it : no entry call needed but no varargs support.
        assertThat(persons).containsEntry(444L, jim)
                .containsEntry(555L, alice);
        
        // Opposite of contains/containsEntry
        assertThat(persons).doesNotContain(entry(777L, jim), entry(444L, alice));
        assertThat(persons).doesNotContainEntry(777L, jim);

        // Assertion on key
        assertThat(persons).containsKey(444L);
        assertThat(persons).containsKeys(444L, 555L);
        assertThat(persons).containsOnlyKeys(444L, 555L, 666L);
        assertThat(persons).doesNotContainKey(777L);
        assertThat(persons).doesNotContainKeys(777L, 888L);

        // Assertion on value
        assertThat(persons).containsValue(alice);
        assertThat(persons).containsValues(jim, alice);
        assertThat(persons).doesNotContainValue(new Person(777, "Yaniv", 30, 1.65d));

        ImmutableMap<Integer, String> anotherMap = ImmutableMap.of(1, "one",
                2, "two",
                3, "three");
        assertThat(persons).hasSameSizeAs(anotherMap);
        
        persons.clear();
        assertThat(persons).isEmpty();
    }

    @Test
    public void testMap_containsAllEntriesOf() {
        ImmutableMap<Integer, String> bigMap = ImmutableMap.of(1, "one",
                2, "two",
                3, "three");
        
        ImmutableMap<Integer, String> smallMap = ImmutableMap.of(1, "one",
                2, "two");        

        assertThat(bigMap).containsAllEntriesOf(smallMap);
    }

    @Test
    public void testMap_containsOnly_containsExactly() throws Exception {

        Person jim = new Person(444, "Jim", 30, 1.85d);
        Person alice = new Person(555, "Alice", 30, 1.65d);
        Person carl = new Person(666, "Carl", 20, 1.95d);
        Map<Long, Person> persons = Stream.of(jim, alice, carl)
                .collect(toMap(Person::getId, Function.identity(), throwingMerger(), LinkedHashMap::new));

        // Order doesn't matter
        assertThat(persons).containsOnly(entry(555L, alice), entry(666L, carl), entry(444L, jim));

        // Order matters - this MUST be a map that has predictable iteration order! (i.e. NOT HashMap) 
        assertThat(persons).containsExactly(entry(444L, jim), entry(555L, alice), entry(666L, carl));

    }
    
    private static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
    }    

    private static <K, V> Map.Entry<K, V> javaMapEntry(K key, V value) {
        return new SimpleImmutableEntry<>(key, value);
    }
}


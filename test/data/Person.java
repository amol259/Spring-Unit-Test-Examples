package com.att.tlv.training.test.data;

import java.util.Objects;

public class Person {

    private final long id;
    private final String name;
    private final int age;
    private final double height;

    public Person(long id, String name, int age, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Person)) {
            return false;
        }
        Person castOther = (Person) other;
        return id == castOther.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.att.tlv.training.test.data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.stream.Stream;

public interface Demo {
    
    char getChar();
    Character getBoxedChar();
    
    byte getByte();
    Byte getBoxedByte();
    
    short getShort();
    Short getBoxedShort();
    
    int getInt();
    Integer getBoxedInt();
    
    long getLong();
    Long getBoxedLong();
    
    float getFloat();
    Float getBoxedFloat();
    
    double getDouble();
    Double getBoxedDouble();
    
    boolean getBoolean();
    Boolean getBoxedBoolean();
    
    Object getObject();
    
    String getString();
    
    Object[] getObjectArray();
    
    Collection<Object> getCollection();
    List<Object> getList();
    Set<Object> getSet();
    
    Map<Object, Object> getMap();
    
    Stream<Object> getStream();
    
    Optional<Object> getOptional();
    OptionalInt getOptionalInt();
    OptionalLong getOptionalLong();
    OptionalDouble getOptionalDouble();
    
    Demo getMe();
}
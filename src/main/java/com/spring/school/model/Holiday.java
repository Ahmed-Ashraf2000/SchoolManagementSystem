package com.spring.school.model;


public record Holiday(String day, String reason, Type type) {

    public enum Type {
        FESTIVAL, FEDERAL
    }
}

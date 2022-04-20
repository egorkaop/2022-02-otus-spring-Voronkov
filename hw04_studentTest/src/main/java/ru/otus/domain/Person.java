package ru.otus.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Person {

    private String firstName;
    private String surName;

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }
}

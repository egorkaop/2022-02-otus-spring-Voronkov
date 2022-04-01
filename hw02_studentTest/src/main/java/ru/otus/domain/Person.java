package ru.otus.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Person {

    private String firstName;
    private String surName;

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }
}

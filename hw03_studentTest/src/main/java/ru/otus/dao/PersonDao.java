package ru.otus.dao;

import ru.otus.domain.Person;

public interface PersonDao {

    Person getPerson(String name, String surName);
}

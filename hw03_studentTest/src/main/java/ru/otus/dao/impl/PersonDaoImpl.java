package ru.otus.dao.impl;

import org.springframework.stereotype.Repository;
import ru.otus.dao.PersonDao;
import ru.otus.domain.Person;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Override
    public Person getPerson(String name, String surName) {
        return new Person(name, surName);
    }
}

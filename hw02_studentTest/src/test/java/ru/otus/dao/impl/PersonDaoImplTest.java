package ru.otus.dao.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dao.PersonDao;

public class PersonDaoImplTest {

    private static final String EXPECTED_NAME = "egor";
    private static final String EXPECTED_SURNAME = "voronkov";
    private PersonDao personDao;

    @BeforeEach
    void setUp() {
        personDao = new PersonDaoImpl();
    }

    @DisplayName("Должен позвращать правельного Person")
    @Test
    void shouldReturnCorrectPerson() {
        var actualPerson = personDao.getPerson(EXPECTED_NAME, EXPECTED_SURNAME);
        assertThat(EXPECTED_NAME).isEqualTo(actualPerson.getFirstName());
        assertThat(EXPECTED_SURNAME).isEqualTo(actualPerson.getSurName());
    }

}

package ru.otus.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.dao.PersonDao;
import ru.otus.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonDaoImplTest {
    private static final String TEST_NAME = "Egor";
    private static final String TEST_SURNAME = "Voronkov";
    @Autowired
    PersonDao personDao;

    @Configuration
    static class TestConfiguration {
        @Bean
        PersonDao personDao() {
            return new PersonDaoImpl();
        }
    }

    @DisplayName("Должен возвращать правильного Person")
    @Test
    void shouldReturnCorrectPerson() {
        Person expectedPerson = new Person(TEST_NAME, TEST_SURNAME);
        assertThat(personDao.getPerson(TEST_NAME, TEST_SURNAME)).isEqualTo(expectedPerson);
    }

}
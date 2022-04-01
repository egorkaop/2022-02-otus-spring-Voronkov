package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.PersonDao;
import ru.otus.domain.Person;
import ru.otus.exceptions.WrongInputException;
import ru.otus.service.IOService;
import ru.otus.service.LocaleService;
import ru.otus.service.PersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final IOService ioService;
    private final PersonDao personDao;
    private final LocaleService localeService;

    @Override
    public Person getPerson() {
        String name = askName();
        if (name.isEmpty()) {
            throw new WrongInputException("name is empty");
        }
        String surName = askSurName();
        if (surName.isEmpty()) {
            throw new WrongInputException("surname is empty");
        }
        return personDao.getPerson(name, surName);
    }

    private String askName() {
        ioService.outputText(localeService.localizeText("strings.name"));
        return ioService.inputText();
    }

    private String askSurName() {
        ioService.outputText(localeService.localizeText("strings.surname"));
        return ioService.inputText();
    }
}

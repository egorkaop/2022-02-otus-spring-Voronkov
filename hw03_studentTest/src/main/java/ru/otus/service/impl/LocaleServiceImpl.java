package ru.otus.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.CsvFileConfig;
import ru.otus.config.LocaleConfig;
import ru.otus.exceptions.WrongInputException;
import ru.otus.service.LocaleService;

import java.util.Locale;

@Service
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;
    private LocaleConfig localeConfig;
    private final CsvFileConfig csvFileConfig;

    public LocaleServiceImpl(MessageSource messageSource, LocaleConfig localeConfig, CsvFileConfig csvFileConfig) {
        this.messageSource = messageSource;
        this.localeConfig = localeConfig;
        this.csvFileConfig = csvFileConfig;
    }

    @Override
    public String localizeText(String localeString) {
        return messageSource.getMessage(localeString, null, getLocale());
    }

    @Override
    public void checkLanguage(String language) {
        if (language.toLowerCase().equals("ru")) {
            localeConfig.setDefaultLocale("ru-RU");
            csvFileConfig.setFileName("questions_ru.csv");
        } else if (language.toLowerCase().equals("en")) {
            localeConfig.setDefaultLocale("en_EN");
            csvFileConfig.setFileName("questions_en.csv");
        } else throw new WrongInputException("wrong input");
    }

    private Locale getLocale() {
        return Locale.forLanguageTag(localeConfig.getDefaultLocale());
    }
}

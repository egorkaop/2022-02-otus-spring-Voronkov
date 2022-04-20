package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.CsvFileConfig;
import ru.otus.config.LocaleConfig;
import ru.otus.exceptions.WrongInputException;
import ru.otus.service.LanguageService;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LocaleConfig localeConfig;
    private final CsvFileConfig csvFileConfig;

    @Override
    public void checkAndSetLanguage(String language) {
        checkLanguage(language);
        setLanguage(language);
    }

    private void checkLanguage(String language) {
        if (!localeConfig.getLanguages().contains(language.toLowerCase())) {
            throw new WrongInputException("wrong input");
        }
    }

    private void setLanguage(String language) {
        for (String languageString : localeConfig.getLanguages()) {
            if (language.toLowerCase().equals(languageString)) {
                localeConfig.setLocale(languageString);
                csvFileConfig.setFileName("questions_" + languageString + ".csv");
            }
        }
    }
}

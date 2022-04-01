package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.LocaleConfig;
import ru.otus.service.LocaleService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleServiceImpl implements LocaleService {
    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;


    @Override
    public String localizeText(String localeString) {
        return messageSource.getMessage(localeString, null, getLocale());
    }


    private Locale getLocale() {
        return Locale.forLanguageTag(localeConfig.getLocale());
    }
}

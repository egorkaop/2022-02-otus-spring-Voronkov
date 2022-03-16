package ru.otus.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.exceptions.WrongInputException;
import ru.otus.service.IOService;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;
    private final InputStream in;

    public IOServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream out,
            @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.in = in;
    }

    @Override
    public void outputText(String text) {
        out.println(text);
    }

    @Override
    public String inputText() {
        String text;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            text = reader.readLine();
        } catch (Exception e) {
            throw new WrongInputException("Bad input");
        }
        return text;
    }

    @Override
    public void outputFormatText(String format, Object... o) {
        System.out.printf(format,o);
    }
}


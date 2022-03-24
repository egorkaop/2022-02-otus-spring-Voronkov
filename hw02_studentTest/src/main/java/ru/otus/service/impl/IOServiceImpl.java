package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final PrintStream out;
    private final Scanner scanner;

    public IOServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream out,
                         @Value("#{T(java.lang.System).in}") InputStream in) {
        this.out = out;
        scanner = new Scanner(in);
    }

    @Override
    public void outputText(String text) {
        out.println(text);
    }

    @Override
    public String inputText() {
        return scanner.nextLine();
    }

    @Override
    public void outputFormatText(String format, Object... o) {
        out.printf(format, o);
    }
}


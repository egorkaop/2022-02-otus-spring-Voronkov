package ru.otus.service;

public interface IOService {

    void outputText(String text);

    String inputText();

    void outputFormatText(String format, Object... o);


}

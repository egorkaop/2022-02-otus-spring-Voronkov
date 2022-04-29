package ru.otus.utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.dao.BookDao;
import ru.otus.domain.Book;
import ru.otus.utils.BookListMapper;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookListMapperImpl implements BookListMapper {
    private final BookDao bookDao;
    @Override
    public List<Book> getBookList(String stringId) {
        List<Book> bookList = new ArrayList<>();
        for (String id: stringId.split(",")){
            bookList.add(bookDao.getBookById(Long.parseLong(id)));
        }
        return bookList;
    }
}

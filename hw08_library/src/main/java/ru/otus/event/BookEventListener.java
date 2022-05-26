package ru.otus.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.dao.BookRepository;
import ru.otus.dao.CommentRepository;
import ru.otus.domain.Book;

@Component
@RequiredArgsConstructor
public class BookEventListener extends AbstractMongoEventListener<Book> {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        String bookTitle = event.getSource().get("title", String.class);
        Book book = bookRepository.findByTitle(bookTitle);
        commentRepository.deleteByBook(book);
    }
}

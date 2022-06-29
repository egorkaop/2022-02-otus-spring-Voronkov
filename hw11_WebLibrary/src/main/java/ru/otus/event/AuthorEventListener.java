//package ru.otus.event;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
//import org.springframework.stereotype.Component;
//import ru.otus.dao.AuthorRepository;
//import ru.otus.dao.BookRepository;
//import ru.otus.domain.Author;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class AuthorEventListener extends AbstractMongoEventListener<Author> {
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//
//    @Override
//    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
//        String authorName = event.getSource().get("name", String.class);
//        List<Author> authorList = authorRepository.findByName(authorName);
//        bookRepository.deleteByAuthorsContains(authorList);
//    }
//}

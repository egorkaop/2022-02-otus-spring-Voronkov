//package ru.otus.event;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
//import org.springframework.stereotype.Component;
//import ru.otus.dao.BookRepository;
//import ru.otus.dao.GenreRepository;
//import ru.otus.domain.Genre;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class GenreEventListener extends AbstractMongoEventListener<Genre> {
//    private final GenreRepository genreRepository;
//    private final BookRepository bookRepository;
//
//    @Override
//    public void onBeforeDelete(BeforeDeleteEvent<Genre> event) {
//        String genreName = event.getSource().get("name", String.class);
//        List<Genre> genreList = genreRepository.findByName(genreName);
//        bookRepository.deleteByGenresContains(genreList);
//    }
//}

package ru.otus.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.TestConfig;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.service.BookService;
import ru.otus.service.UserService;
import ru.otus.utils.BookDtoMapper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Import({TestConfig.class})
@SpringBootTest(classes = {BookServiceImpl.class})
class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @MockBean
    BookDtoMapper bookDtoMapper;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    GenreRepository genreRepository;
    @MockBean
    private UserService userService;

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"}
    )
    @DisplayName("Должен запрещать доступ на delete")
    @Test
    public void testAuthenticatedOnUserByDeleteRequest() throws Exception {
        assertThrows(AccessDeniedException.class, () -> bookService.deleteBookById(2));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Должен разрешать доступ на delete")
    @Test
    public void testAuthenticatedOnAdminByDeleteInsertRequest() throws Exception {
        bookService.deleteBookById(2);
    }

}
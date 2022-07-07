package ru.otus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.dto.BookInsertDto;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    //get/api/books
    @DisplayName("Должен делать redirect с get/api/books на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByGetApiRequest() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/api/books"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен пускать на get'/api/books' если пользователь авторизован")
    public void testAuthenticatedOnAdminByGetApiRequest() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    //delete/api/books/2
    @DisplayName("Должен делать redirect с delete/api/books/2 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByDeleteRequest() throws Exception {
        mockMvc.perform(delete("/api/books/2"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(delete("/api/books/2"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Должен делать пускать на delete/api/books/2 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAdminByDeleteRequest() throws Exception {
        mockMvc.perform(delete("/api/books/2"))
                .andExpect(status().isOk());
    }

    //patch/api/books/1
    @DisplayName("Должен делать redirect с patch/api/books/1 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByPatchRequest() throws Exception {
        mockMvc.perform(patch("/api/books/1"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(patch("/api/books/1"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Должен делать redirect с patch/api/books/1 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAdminByPatchRequest() throws Exception {
        mockMvc.perform(patch("/api/books/1"))
                .andExpect(status().isOk());
    }

    //get/api/books/1
    @DisplayName("Должен делать redirect с get/api/books/1 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByGetFullRequest() throws Exception {
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/api/books/1"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Должен делать redirect с get/api/books/1 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAdminByGetFullRequest() throws Exception {
        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk());
    }
    //post/api/books
    @DisplayName("Должен делать redirect с post/api/books на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByPostRequest() throws Exception {
        mockMvc.perform(post("/api/books"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(post("/api/books"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @DisplayName("Должен делать redirect с post/api/books на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAdminByPostRequest() throws Exception {
        BookInsertDto bookInsertDto = new BookInsertDto("test", List.of(1L),List.of(1L));
        System.out.println(objectMapper.writeValueAsString(bookInsertDto));
        mockMvc.perform(post("/api/books")
                        .content(objectMapper.writeValueAsString(bookInsertDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
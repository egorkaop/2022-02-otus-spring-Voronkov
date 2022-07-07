package ru.otus.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerRestTest {
    @Autowired
    private MockMvc mockMvc;

    //get/api/authors
    @DisplayName("Должен делать redirect с get/api/authors на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByGetApiRequest() throws Exception {
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/api/authors"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен пускать на get'/api/authors' если пользователь авторизован")
    public void testAuthenticatedOnAdminByGetApiRequest() throws Exception {
        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk());
    }

    //delete/api/authors/1
    @DisplayName("Должен делать redirect с delete/api/authors/1 на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByDeleteApiRequest() throws Exception {
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен пускать на delete'/api/authors/1' если пользователь авторизован")
    public void testAuthenticatedOnAdminByDeleteApiRequest() throws Exception {
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().isOk());
    }
}
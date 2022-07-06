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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Должен делать redirect на /login если пользователь не авторизован")
    @Test
    public void testAuthenticatedOnAnonymousByGetRequest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    @DisplayName("Должен пускать на '/' если пользователь авторизован")
    public void testAuthenticatedOnAdminByGetRequest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
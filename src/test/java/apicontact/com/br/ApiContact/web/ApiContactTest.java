package apicontact.com.br.ApiContact.web;

import apicontact.com.br.ApiContact.controller.ContactController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class ApiContactTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactController contactController;

    @Test
    public void shouldCreateNewContact(){

    }
}

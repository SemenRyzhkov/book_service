package com.github.template.controller;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = "/sql/test_user.sql")
class UserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserController.REST_URL + '/';

    @Test
    @WithMockUser(username = "test3mail", password = "ivan")
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void get() {
    }

    @Test
    void create() {
    }

    @Test
    void updateForAdmin() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
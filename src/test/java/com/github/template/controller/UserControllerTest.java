package com.github.template.controller;

import com.github.template.json.JsonUtil;
import com.github.template.mapper.UserMapper;
import com.github.template.model.db.db.User;
import com.github.template.model.db.to.userDto.AdminDto;
import com.github.template.model.db.to.userDto.UserDto;
import com.github.template.model.db.to.userDto.UserEditDto;
import com.github.template.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;

import static com.github.template.TestUtil.readFromJson;
import static com.github.template.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserController.REST_URL + '/';

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService service;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_DTO_MATCHER.contentJsonPageable(mapper.usersToUsersDto(users)));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_ID))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(USER_DTO_MATCHER.contentJson(mapper.entityToDto(admin)));
    }

    @Test
    void create() throws Exception {
        User newUser = getNew();
        UserEditDto newDtoUser = mapper.entityToEditDto(newUser);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDtoUser)))
                .andExpect(status().isCreated());

        UserDto created = readFromJson(action, UserDto.class);
        long newId = created.getId();
        newUser.setId(newId);
        USER_DTO_MATCHER.assertMatch(created, mapper.entityToDto(newUser));
        USER_DTO_MATCHER.assertMatch(service.get(newId), mapper.entityToDto(newUser));
    }

    @Test
    void updateForAdmin() throws Exception {
        AdminDto updated = new AdminDto();
        updated.setRole("ADMIN");
        updated.setStatus("BANNED");
        perform(MockMvcRequestBuilders.put(REST_URL + "/admin-edit/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        USER_DTO_MATCHER.assertMatch(service.get(USER_ID),mapper.entityToDto(getUpdated()));
    }

    @Test
    void update() throws Exception {
        User updated = getNew();
        UserEditDto newDtoUser = mapper.entityToEditDto(updated);
        perform(MockMvcRequestBuilders.put(REST_URL + "/user-edit/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDtoUser)))
                .andExpect(status().isOk());

        updated.setId(USER_ID);
        USER_DTO_MATCHER.assertMatch(service.get(USER_ID),mapper.entityToDto(updated));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + USER_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(EntityNotFoundException.class, () -> service.get(USER_ID));
    }
}
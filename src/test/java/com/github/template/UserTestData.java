package com.github.template;

import com.github.template.model.db.db.Role;
import com.github.template.model.db.db.Status;
import com.github.template.model.db.db.User;
import com.github.template.model.db.to.userDto.UserDto;

import java.util.List;

public class UserTestData {
    public static final TestMatcher<UserDto> USER_DTO_MATCHER = TestMatcher.usingIgnoringFieldsComparator(UserDto.class,  "password");
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class,  "password");

    public static final long ADMIN_ID = 3;
    public static final long USER_ID = 1;

    public static final User user = new User(USER_ID, "Vasya", "vasya", "test1@mail", Role.USER, Status.ACTIVE);
    public static final User user1 = new User(USER_ID + 1, "Petya", "petya", "test2@mail", Role.USER, Status.BANNED);
    public static final User admin = new User(ADMIN_ID, "Ivan", "ivan", "test3@mail", Role.ADMIN, Status.ACTIVE);

    public static User getNew() {
        return new User(null, "newUser", "newPasword", "new@mail", Role.USER, Status.ACTIVE);
    }

    public static User getUpdated() {
        return new User(user.getId(), user.getName(), user.getPassword(), user.getEmail(), Role.ADMIN, Status.BANNED);
    }

    public static final List<User> users = List.of(user, user1, admin);

}

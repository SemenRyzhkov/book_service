package com.github.template;

import com.github.template.model.db.db.Role;
import com.github.template.model.db.db.Status;
import com.github.template.model.db.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class,  "password");

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static final long ADMIN_ID = 32;
    public static final long USER_ID = 30;

    private static final String password1 = passwordEncoder.encode("vasya");
    private static final String password2 = passwordEncoder.encode("petya");
    private static final String password3 = passwordEncoder.encode("ivan");

    public static final User user = new User(USER_ID, "Vasya", password1, "test1@mail", Role.USER, Status.ACTIVE);
    public static final User user1 = new User(USER_ID + 1, "Petya", password2, "test2@mail", Role.ADMIN, Status.BANNED);
    public static final User admin = new User(ADMIN_ID + 1, "Ivan", password3, "test3@mail", Role.ADMIN, Status.ACTIVE);

    public static final List<User> users = List.of(user, user1, admin);

}

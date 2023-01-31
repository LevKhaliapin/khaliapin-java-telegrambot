package org.telbot.telran.info.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telbot.telran.info.model.User;
import org.telbot.telran.info.repository.UserRepository;
import org.telbot.telran.info.role.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void createUserWithAllNonEmptyFields() {
        List<User> all = userService.list();
        assertEquals(0, all.size());
        User user = new User(1, "User1");
        userService.save(user);
        List<User> all1 = userRepository.findAll();
        assertEquals(1, all1.size());
        User userByUserId = userService.getUser(1);
        assertEquals(user.getId(), userByUserId.getId());
        assertEquals(user.getName(), userByUserId.getName());
    }

    @Test
    void getAllUsers() {
        List<User> all = userRepository.findAll();
        assertEquals(0, all.size());
        userService.save(new User(1, "User1"));
        userService.save(new User(2, "User2"));
        userService.save(new User(3, "User3"));
        List<User> all1 = userRepository.findAll();
        assertEquals(3, all1.size());
    }

    @Test
    void getUserById() {
        List<User> all = userRepository.findAll();
        assertEquals(0, all.size());
        User user = new User(1, "User1");
        userService.save(user);
        List<User> all1 = userRepository.findAll();
        assertEquals(1, all1.size());
        User userByUserId = userService.getUser(1);
        assertEquals(user.getId(), userByUserId.getId());
        assertEquals(user.getName(), userByUserId.getName());
    }

    @Test
    void deleteUserById() {
        List<User> all = userRepository.findAll();
        assertEquals(0, all.size());
        User user = new User(1, "User1");
        userService.save(user);
        List<User> all1 = userRepository.findAll();
        assertEquals(1, all1.size());
        userService.delete(user.getId());
        assertNull(all1.get(0));
        assertEquals(0, all1.size());
    }
}
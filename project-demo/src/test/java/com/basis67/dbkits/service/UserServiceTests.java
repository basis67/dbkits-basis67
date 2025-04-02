package com.basis67.dbkits.service;

import com.basis67.dbkits.mapper.UserMapper;
import com.basis67.dbkits.model.mysql.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password");
        testUser.setPhone("13800138000");
        testUser.setUserStatus(1);
    }

    @Test
    void testGetUserById() {
        when(userMapper.selectByPrimaryKey(1)).thenReturn(testUser);

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        assertEquals(testUser.getUsername(), result.getUsername());
        assertEquals(testUser.getEmail(), result.getEmail());

        verify(userMapper).selectByPrimaryKey(1);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = Arrays.asList(testUser);
        when(userMapper.selectAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testUser.getId(), result.get(0).getId());

        verify(userMapper).selectAll();
    }

    @Test
    void testCreateUser() {
        when(userMapper.insert(any(User.class))).thenReturn(1);

        int result = userService.createUser(testUser);

        assertEquals(1, result);
        verify(userMapper).insert(testUser);
    }

    @Test
    void testUpdateUser() {
        when(userMapper.updateByPrimaryKey(any(User.class))).thenReturn(1);

        int result = userService.updateUser(testUser);

        assertEquals(1, result);
        verify(userMapper).updateByPrimaryKey(testUser);
    }

    @Test
    void testDeleteUser() {
        when(userMapper.deleteByPrimaryKey(1)).thenReturn(1);

        int result = userService.deleteUser(1);

        assertEquals(1, result);
        verify(userMapper).deleteByPrimaryKey(1);
    }
}
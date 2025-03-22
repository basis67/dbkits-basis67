package com.basis67.dbkits.service;

import com.basis67.dbkits.mapper.UserMapper;
import com.basis67.dbkits.model.mysql.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Transactional
    public int createUser(User user) {
        user.setId(null);
        return userMapper.insert(user);
    }

    @Transactional
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Transactional
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}

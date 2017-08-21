package com.ivan.api;

import com.ivan.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByPrimaryKey(String id);
}

package com.quangta.service;

import com.quangta.entity.User;

public interface UserService {

    public User findUserByJwtToken(String token) throws Exception;

    public User findUserByEmail(String email) throws Exception;
}

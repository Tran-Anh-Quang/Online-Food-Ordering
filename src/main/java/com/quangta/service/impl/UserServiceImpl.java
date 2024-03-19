package com.quangta.service.impl;

import com.quangta.config.JwtProvider;
import com.quangta.entity.User;
import com.quangta.repository.UserRepository;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String token) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(token);
        User user = findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}

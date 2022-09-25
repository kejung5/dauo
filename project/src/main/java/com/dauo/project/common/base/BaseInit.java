package com.dauo.project.common.base;

import com.dauo.project.domain.user.User;
import com.dauo.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseInit {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initUser() {
        List<User> users = Arrays.asList(
                new User("ejkim", "ejkim", passwordEncoder.encode("ejkim"), "ejkim@gmail.com"),
                new User("dauo", "dauo", passwordEncoder.encode("dauo"), "dauo@gmail.com"));
        repository.saveAll(users);
    }
}

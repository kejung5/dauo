package com.dauo.project.domain.authenticate;

import lombok.*;

import javax.validation.constraints.NotEmpty;

public class TokenDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Request {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Response {
        private String token;
    }

}

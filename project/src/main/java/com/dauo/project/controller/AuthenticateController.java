package com.dauo.project.controller;

import com.dauo.project.common.code.ErrorCode;
import com.dauo.project.common.exception.ApiException;
import com.dauo.project.common.utils.JwtUtil;
import com.dauo.project.domain.authenticate.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticateController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public TokenDto.Response generateToken(@RequestBody @Valid TokenDto.Request request) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword(),
                    new ArrayList<>());
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            log.error("BadCredentialsException : ", e.getMessage());
            throw new ApiException(ErrorCode.INVALID_AUTHENTICATE);
        } catch (Exception ex) {
            log.error("Exception : ", ex.getMessage());
            throw new ApiException(ErrorCode.INVALID_AUTHENTICATE);
        }

        return TokenDto.Response.builder()
                .token(jwtUtil.generateToken(request.getUsername()))
                .build();
    }

}

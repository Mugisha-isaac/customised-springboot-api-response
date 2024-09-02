package com.rw.studentMs.controller;

import com.rw.studentMs.dto.JwtDto;
import com.rw.studentMs.dto.SignInDto;
import com.rw.studentMs.dto.SignUpDto;
import com.rw.studentMs.model.User;
import com.rw.studentMs.service.AuthService;
import com.rw.studentMs.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AuthService authService;
//    private TokenProvider tokenProvider;

    @PostMapping("/signup")
    ApiResponse<UserDetails> signUp(@RequestBody @Valid SignUpDto data, HttpServletRequest request) {
       UserDetails signUpData = authService.signUp(data);
        return new ApiResponse<>(200, "Success", null, System.currentTimeMillis(), "1.0.0", request.getRequestURI(), signUpData);
    }

    @PostMapping("/signIn")
    ApiResponse<JwtDto> signIn(@RequestBody @Valid SignInDto data, HttpServletRequest request) {
       var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
       var authUser = authenticationManager.authenticate(usernamePassword);
//       var accessToken = tokenProvider.generateAccessToken((User) authUser.getPrincipal());
        return new ApiResponse<>(200, "Success", null, System.currentTimeMillis(), "1.0.0", request.getRequestURI(), null);
    }
}

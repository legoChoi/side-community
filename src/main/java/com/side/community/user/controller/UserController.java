package com.side.community.user.controller;

import com.side.community.common.annotation.UserPrincipal;
import com.side.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/test/v2")
    public String testV2(
            @UserPrincipal User user
    ) {
        return user.getId().toString();
    }
}

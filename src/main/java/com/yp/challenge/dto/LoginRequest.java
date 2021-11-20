package com.yp.challenge.dto;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class LoginRequest {

    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;

}

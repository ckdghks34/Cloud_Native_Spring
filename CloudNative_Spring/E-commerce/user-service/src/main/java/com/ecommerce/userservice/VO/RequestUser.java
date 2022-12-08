package com.ecommerce.userservice.VO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message="Email Cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    private String email;

    @NotNull(message="Name Cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    private String name;

    @NotNull(message="Password Cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    private String password;

}

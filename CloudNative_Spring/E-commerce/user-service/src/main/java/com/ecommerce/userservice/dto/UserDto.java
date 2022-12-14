package com.ecommerce.userservice.dto;

import com.ecommerce.userservice.VO.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private  String name;
    private String password;
    private String userId;
    private Date createdAt;

    private String encryptedPassword;

    private List<ResponseOrder> orders;
}

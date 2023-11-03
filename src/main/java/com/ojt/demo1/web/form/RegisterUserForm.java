package com.ojt.demo1.web.form;

import lombok.Data;

@Data
public class RegisterUserForm {
    private String username;
    private Integer phoneNumber;
    private int gender;
    private boolean married;
    private String city;
}

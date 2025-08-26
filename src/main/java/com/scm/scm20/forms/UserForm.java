package com.scm.scm20.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder    
public class UserForm {
    public String name;
    public String userName;
    public String email;
    public String password;
    public String confirmPassword;
    public String phoneNumber;
    public String about;
}

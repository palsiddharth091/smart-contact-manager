package com.scm.scm20.forms;

import com.custom.annotations.FormValidation;
import com.scm.scm20.constants.Messages;
import com.scm.scm20.constants.Regex;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    
    @FormValidation
    public String name;
    @FormValidation
    public String userName;
    @Email(message = Messages.EMAIL_VALIDATION)
    @FormValidation(max=Integer.MAX_VALUE, sizeMessage = Messages.EMAIL_SIZE_VALIDATION)
    public String email;
    @FormValidation(min = 6, sizeMessage = Messages.PASSWORD_MIN_MAX_LENGTH ) 
    @Pattern(regexp = Regex.PASSWORD_REGEX , message = Messages.PASSWORD_ALPHA_NUMERIC)
    public String password;
    @FormValidation(min = 6, sizeMessage = Messages.PASSWORD_MIN_MAX_LENGTH ) 
    @Pattern(regexp = Regex.PASSWORD_REGEX , message = Messages.PASSWORD_ALPHA_NUMERIC)
    public String confirmPassword;
    @FormValidation(min = 10,max = 10, sizeMessage = Messages.PHONE_NUMBER_VALIDATION)
    public String phoneNumber;
    @FormValidation(min = 3, max= 250 , sizeMessage = Messages.TEXT_AREA_VALIDATION)
    public String about;
}

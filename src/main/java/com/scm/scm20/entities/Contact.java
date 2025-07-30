package com.scm.scm20.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    
    @Id
    private String id;
    private String name;
    private String email;
    @Column(name="phone_number")
    private String phoneNumber;
    private String address;
    private String picture;
    private String description;
    private boolean favorite = false;
    
    // This is to store social media handles
    @Column(name="website_link")
    private String websiteLink;

    // This is to map the contact to the user. 
    private User user;
}

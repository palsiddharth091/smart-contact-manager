package com.scm.scm20.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.scm.scm20.constants.PROVIDER;
import com.scm.scm20.converter.ProviderConverter;

import io.micrometer.common.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name",length = 20)
    private String username;
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    private String about;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "phone_number" , length = 10)
    private String phoneNumber;

    @ColumnDefault("false")
    private String enabled; // Check if the user is enabled or not
    @Column(name = "email_verified", columnDefinition = "default false")
    private Boolean emailVerified; // Check if the email is verfifed or not
    @Column(name = "phone_verified", columnDefinition = "default false")
    private Boolean phoneVerified; // Check if the phone is verfifed or not


    @NonNull
    @Column(columnDefinition = "ENUM('SELF','GOOGLE','FACEBOOK')")
    @ColumnDefault("'SELF'")
    @Enumerated(EnumType.STRING)
    @Convert(converter = ProviderConverter.class) // When reading from the database, if the value is not a valid enum, it will not throw an exception. Instead, it will map to PROVIDER.SELF (or null, or any default you choose).
    private PROVIDER provider; // To check how did the user sign up like Self, Google, Facebook etc. 

    // Mapping one user to multiple contacts
    // The code establishes a one-to-many relationship between the User and `Contact` entities using JPA/Hibernate. Through the `@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)` annotation, a user can be associated with multiple contacts. The `mappedBy = "user"` parameter specifies that the `Contact` entity contains a `user` field that manages this relationship. `cascade = CascadeType.ALL` propagates all operations (like saving or deleting) from a User to their associated contacts. `fetch = FetchType.LAZY` when we fetch a user only user object is loaded and a proxy object of the contact class will be sent. It will fetch the contacts only when we hit a method something like that. `orphanRemoval = true` Automatically deletes contacts when the user is deleted.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Contact> contacts = new ArrayList<>();


}

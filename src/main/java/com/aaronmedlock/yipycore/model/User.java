package com.aaronmedlock.yipycore.model;

import com.aaronmedlock.yipycore.constant.UserConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Authentication
    @Column(unique = true, length = 25, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    // Account Information
    @Column(nullable = false)
    private String email = "";
    @Column
    private Boolean isAccountActive = false;
    @Column(nullable = false)
    private String userRole = UserConstants.STANDARD_USER;

    // Profile Information
    @Column(length = 40, nullable = false)
    private String displayName = "";
    @Column(nullable = false)
    private Instant joinDate = Instant.now();
    @Column
    private String profileCoverUrl;
    @Column
    private String profileDescription;
    @Column
    private String profilePictureUrl;
    @Column
    private String preferredPronouns;

    // User Networking / Connections
    @Column
    private ArrayList<String> followers;
    @Column
    private ArrayList<String> following;

    // Constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }



}
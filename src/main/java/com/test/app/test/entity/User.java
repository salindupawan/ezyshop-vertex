package com.test.app.test.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String userId;
    private Date startDate;
    private Long contractDuration;
    private String password;
    private int maxLoginAttempts;
    private int LockoutDuration;
    private String role;
    private String status;
    private String createdBy;
    private String modifiedBy;
    private Date createdAt;
    private Date modifiedAt;
}

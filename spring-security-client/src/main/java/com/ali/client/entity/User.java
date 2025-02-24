//package com.dailycodebuffer.client.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class User
////    this class represents an entity in the database
//{
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    @Column(length = 60)
////    the above annotation means the password attribute must have a MAXIMUM of 60 characters
//    private String password;
////    we will encode the password in the hashCode values using bcrypt encoder to hide the password so that no one can see the password
//    private String role;
//    private boolean enabled = false;
////    by default, the user is not enabled
//}

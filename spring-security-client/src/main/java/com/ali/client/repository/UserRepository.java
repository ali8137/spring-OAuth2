//package com.dailycodebuffer.client.repository;
//
//import com.dailycodebuffer.client.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
////as I remember, adding @Repository annotation is not necessary here
//public interface UserRepository extends JpaRepository<User, Long>
////    this class corresponds to the repository of the database entity "User", which means it allows us to do SQL queries against the "User" table in the database
//{
////it is a good habit to leave one line empty before writing the data fields of this class
//
//    User findByEmail(String email);
////    the above method works according to the structure of its nomenclature/name
//}

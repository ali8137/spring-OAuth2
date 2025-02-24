//package com.dailycodebuffer.client.service;
//
//import com.dailycodebuffer.client.entity.PasswordResetToken;
//import com.dailycodebuffer.client.entity.User;
//import com.dailycodebuffer.client.entity.VerificationToken;
//import com.dailycodebuffer.client.exceptions.UserNotFoundException;
//import com.dailycodebuffer.client.model.UserModel;
//import com.dailycodebuffer.client.repository.PasswordResetTokenRepository;
//import com.dailycodebuffer.client.repository.UserRepository;
//import com.dailycodebuffer.client.repository.VerificationTokenRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Calendar;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class UserServiceImpl implements UserService
////this class implements the service methods defined in the interface "UserService"
//{
//    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
////it is a good habit to leave one line empty before writing the data fields of this class
//
//    @Autowired
//    private UserRepository userRepository;
////    the above is a dependency injected from interface "UserRepository". it allows us to use methods declared in this interface
////    the above dependency is commonly injected
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
////    the above is a dependency injected from class "WebSecurityConfig". the injected dependency is a bean "PasswordEncoder"
////    "PasswordEncoder" is an interface
////    the above dependency is commonly injected after being configured
//
//    @Autowired
////    can we choose not to write the annotation "@Autowired" above, because "VerificationTokenRepository" is already defined as a bean???
//    private VerificationTokenRepository verificationTokenRepository;
////    the above is a dependency injected from interface "VerificationTokenRepository". it allows us to use methods declared in this interface
////    the above dependency is commonly injected
//
//    @Autowired
//    private PasswordResetTokenRepository passwordResetTokenRepository;
//
//    @Override
//    public User registerService(UserModel userModel)
////            is the "UserModel" injected above using method injection??? \/ no, because we haven't injected the parameter userModel as a dependency injection at the beginning of this class "UserService"
//    {
//        User user = new User();
////        because we're creating a new user, who is not yet in the database, we used "new" above instead of retrieving the user back from the database using the repository.
////        why the above user is not injected as dependency into this class??? \/ there is no need to inject entity class, Typically, domain objects like User are created and managed within the scope of the method or service that needs them rather than being injected as dependencies.  dependency injection is more commonly used for services, components, or utility classes rather than domain entities. injecting the User as dependency here will mean that the data fields of the User must have default value, which is an extra unnecessary step
//
//        user.setEmail(userModel.getEmail());
////        the above line takes the input(email in this case) of the user that will go to the UserModel class we have defined. it then sets it as data(email in this case) of the user in the database using the class "User" that we defined
//        user.setFirstName(userModel.getFirstName());
//        user.setLastName(userModel.getLastName());
//        user.setRole("USER");
////        in actual project/application, we can make the above line more dynamic by creating an enum for the role of the user
//        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
////        in the line above. we will get the password from the user, and then we will encrypt it and store it into the database. "passwordEncoder.encode()" method is used to encode the password
////        the purpose of the method "passwordEncoder.encode()" above is to securely hash and encode passwords. the encode method takes the plain-text password as input and returns the encoded (hashed) version.
//
//        userRepository.save(user);
//
//        return user;
//    }
//
//    @Override
//    public void saveVerificationTokenForUser(String token, User user) {
//        VerificationToken verificationToken
//                = new VerificationToken(user, token);
//
//        verificationTokenRepository.save(verificationToken);
////        the above two lines save a new "VerificationToken" into the database. this "VerificationToken" has User "user" and String token as its attribute
//    }
//
//    @Override
//    public String validateVerificationToken(String token)
////    this method is for the user to verify his VerificationToken sent back to him when he registers using his email and password. all the user needs to do is just to visit the URL sent back to him when he registers
//    {
//        VerificationToken verificationToken
//                = verificationTokenRepository.findByToken(token);
//
//        if(verificationToken == null) {
////            System.out.println("verificationToken is null");
//            return "invalid";
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//
//        if((verificationToken.getExpirationTime().getTime()
////                I think that applying getTime() above is redundant, because we have already applied getTime() twice when we defined calculateExpirationDate() method in the class "VerificationToken"
//                - cal.getTime().getTime()) <= 0)
////        I think applying getTime() on a Calendar object will yield a Date object. and I think applying getTime() on a Date object will yield a value in milliseconds compared to a certain year as I remember, this will help us when we want to compare two dates by converting them into digits
//        {
////            System.out.println(verificationToken + "verificationToken is expired");
//            verificationTokenRepository.delete(verificationToken);
////            the method "delete()" is a JpaRepository method
//            return "expired";
//        }
//
//        user.setEnabled(true);
////        the above is to activate the account of the user.
////        the above line will convert the value of the attribute "enabled" in the table User from 0 into 1
////        if the value of the attribute "enabled" was 0 then the user is not verified yet. otherwise, it means that the user is verified
////        why does the value true and false are interpreted as 1 and 0 in the database, is it to save storage??? \/ yes, the representation of boolean values (such as enabled in this case) can vary based on the database being used. different database systems may use different conventions for storing boolean values. in many relational databases, boolean values are often represented using numeric types, where 0 typically represents false and 1 represents true. this is a common convention, but it's not universal across all database systems.
//        userRepository.save(user);
////        System.out.println(verificationToken + "verificationToken is valid");
//        return "valid";
//    }
//
//
////    tutor's version
////    @Override
////    public VerificationToken generateNewVerificationToken(String oldToken)
//////            this method is just to modify the old value of the token attribute in the table VerificationToken into the new value
////    {
////        VerificationToken verificationToken
////                = verificationTokenRepository.findByToken(oldToken);
//////        findByToken() is a JpaRepository method
////
////
////        verificationToken.setToken(UUID.randomUUID().toString());
//////         the method setToken() above allows us to modify the value of the token attribute using spring data JPA
////
////
////
////        verificationTokenRepository.save(verificationToken);
////        return verificationToken;
////    }
//
//
////    my version
//    @Override
//    public User generateNewVerificationToken(String email)
////            this method is just to modify the old value of the token attribute in the table VerificationToken into the new value
//    {
//        User user
//                = userRepository.findByEmail(email);
//
//        log.info("user in service {}", user);
//        log.info("email in service {}", email);
//
////        User user
////                = userRepository.findById(verificationToken.getId()).orElseThrow(() -> new UserNotFoundException("user with id" + verificationToken.getId() + "not found"));
////or
////        User user
////                = userRepository.findById(verificationToken.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user with id" + verificationToken.getId() + "not found"));
//
////        userRepository.save(user);
//
//        return user;
//    }
//
//    @Override
//    public User findUserByEmail(String email)
////    this method will find the user in the database based on his email
//    {
//        return userRepository.findByEmail(email);
////        "findByEmail" is a method provided by spring data JPA
//    }
//
//    @Override
//    public void createPasswordResetTokenForTheUser(User user, String token)
////    this method will create PasswordResetToken for the user in the database. this token will allow the user to get an email with a URL link containing this token. by clicking on this link, the user will be able to reset his password
//    {
//        PasswordResetToken passwordResetToken
//                = new PasswordResetToken(user, token);
//        passwordResetTokenRepository.save(passwordResetToken);
//    }
//
//    @Override
//    public String validatePasswordResetToken(String token)
////        this method validates the PasswordResetToken to check if there is a token in the table PasswordResetToken with the value "token" where this token should not be expired
//    {
//        PasswordResetToken passwordResetToken
//                = passwordResetTokenRepository.findByToken(token);
//
//        if(passwordResetToken == null) {
//            return "invalid";
//        }
//
//        User user = passwordResetToken.getUser();
//        Calendar cal = Calendar.getInstance();
//
//        if((passwordResetToken.getExpirationTime().getTime()
////                I think that applying getTime() above is redundant, because we have already applied getTime() twice when we defined calculateExpirationDate() method in the class "PasswordRecentToken"
//                - cal.getTime().getTime()) <= 0)
////        I think applying getTime() on a Calendar object will yield a Date object. and I think applying getTime() on a Date object will yield a value in milliseconds compared to a certain year as I remember, this will help us when we want to compare two dates by converting them into digits
//        {
//            passwordResetTokenRepository.delete(passwordResetToken);
////            the method "delete()" is a JpaRepository method
//            return "expired";
//        }
//
//        return "valid";
//    }
//
//    @Override
//    public Optional<User> getUserByPasswordResetToken(String token) {
//        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
////        the method "ofNullable" above will check if the parameter inside it has a value or is empty. if empty, this method will return a null. otherwise, it will return the value of this parameter
////        Optional.ofNullable(...): The result is wrapped in an Optional. if the result is not null (i.e., a user is found), it will be wrapped in an Optional and returned. if no user is associated with the provided token, the method returns an empty Optional.
//    }
//
//    @Override
//    public void changePassword(User user, String newPassword)
////    this method modifies the old value of the attribute "password" of the User "User" in the database into the new value of it
//    {
//        user.setPassword(passwordEncoder.encode(newPassword));
////        this method modifies the password attribute of the User "user" by setting its value to the value "passwordEncoder.encode(newPassword)", which is the encoded version of new password
//        userRepository.save(user);
//    }
//
//    @Override
//    public boolean checkIfValidOldPassword(User user, String oldPassword)
////    this method is to check if the entered "oldPassword" by the user is equal to the one present in the database
//    {
//        return passwordEncoder.matches(oldPassword, user.getPassword());
////        "user.getPassword()" above will get the password of the user from the database (this means the encoded version of the password). "oldPassword" represents the old password of the user (this means the password of the suer before being encoded). matches() methods is a method of the interface PasswordEncoder, this method checks if the encoded version of the first parameter matches the second parameter
//    }
//
//
//}

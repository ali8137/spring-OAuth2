//package com.dailycodebuffer.client.service;
//
//import com.dailycodebuffer.client.entity.User;
//import com.dailycodebuffer.client.entity.VerificationToken;
//import com.dailycodebuffer.client.model.UserModel;
//
//import java.util.Optional;
//
//
////the service annotation is added to the class that implements this interface and not to this interface itself
//public interface UserService
////    this service interface has the service methods that will be implemented in the class "UserServiceImpl" and will be used in the controller class "RegisterController"
//{
//    //it is a good habit to leave one line empty before writing the data fields of this class
//
//    User registerService(UserModel userModel);
//
//    void saveVerificationTokenForUser(String token, User user);
//
//    String validateVerificationToken(String token);
//
//    User generateNewVerificationToken(String oldToken);
//
//    User findUserByEmail(String email);
//
//    void createPasswordResetTokenForTheUser(User user, String token);
//
//    String validatePasswordResetToken(String token);
//
//    Optional<User> getUserByPasswordResetToken(String token);
//
//    void changePassword(User user, String newPassword);
//
//    boolean checkIfValidOldPassword(User user, String oldPassword);
//}

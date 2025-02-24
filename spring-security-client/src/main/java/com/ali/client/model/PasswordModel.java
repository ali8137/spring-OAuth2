//package com.dailycodebuffer.client.model;
//
//import lombok.Data;
//
//@Data
//public class PasswordModel
////this model is to get the user email when he wants to reset his password using the method resetMethod(). then this class will allow us to get the new password of the user
////    this class is also to get the email, old password, and new password of the user when he wants to change his password
//{
////it is a good habit to leave one line empty before writing the data fields of this class
//
//    private String email;
////    the above field was added for the user to send his email in order to request changing his password, to which he will be sent back a URL link containing validation token that will validate that he wants to reset his password. after clicking on this link, the user will then send his old password and his new password, that's why the below fields are added
//    private String oldPassword;
//    private String newPassword;
////    the above two fields are added for the user to send his old password and his new password after validating that he wants to reset his password
//}

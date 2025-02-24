//package com.dailycodebuffer.client.controller;
//
//import com.dailycodebuffer.client.entity.User;
//import com.dailycodebuffer.client.entity.VerificationToken;
//import com.dailycodebuffer.client.event.RegistrationCompleteEvent;
//import com.dailycodebuffer.client.model.PasswordModel;
//import com.dailycodebuffer.client.model.UserModel;
//import com.dailycodebuffer.client.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@Slf4j
//public class RegistrationController {
////it is a good habit to leave one line empty before writing the data fields of this class
//
//    @Autowired
//    private UserService userService;
////    the above is a dependency injected from class "UserService". the injection is to use the methods of this interface.
////    I think the reason why the above dependency is injected and not created using "new" constructor is because we might use the methods of the above dependency in every/many of the methods below. so, it would be better to inject the dependency, then to use new constructor in each method below
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    @PostMapping("/register")
//    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
//        User user = userService.registerService(userModel);
//
//        publisher.publishEvent(new RegistrationCompleteEvent(
//                user,
//                applicationUrl(request)
//        ));
////        the above line will create an event that will create a token for the user and send it to him
//
//        return "Success";
////        the above line means that whenever the user is trying to register, his data will be successfully stored into the database
//    }
//
//    @GetMapping("/verifyRegistration")
//    public String verifyRegistration(@RequestParam("token") String token)
////    "@RequestParam("token") String token" above means that the parameter of the http request sent, which has name "token" will be stored into the parameter variable/object instance "String token" above
////    this method is to verify the registration of the user by verifying his VerificationToken sent back to him when he registers using his email and password. all the user needs to do is just to visit the URL sent back to him when he registers
//    {
//        String result = userService.validateVerificationToken(token);
//        if(result.equalsIgnoreCase("valid")) {
//            return "User verified successfully";
//        }
//        return "Bad User";
//    }
//
//
////    tutor's version
////    @GetMapping("/resendVerifyToken")
////    public String resendVerificationToken(@RequestParam("token") String oldToken,
////                                          HttpServletRequest request)
//////            sometimes, the user didn't receive the email, which contains the token, when he registers. this may happen due to many reasons, for example, due to certain issue with the server. and this might happen commonly as this may have happened to you personally. that's why there is a button or functionality that can resend the verification email containing the token again to the user
//////            to apply this method; when you register as a user, you will get the URL containing the token, but act as if you haven't received it in order to mimic the real life scenario mentioned in the comment above. now, do a get request to the endpoint http://localhost:8080/resendVerifyToken?token=hereIstheValueOfTheTokenRecievedDuringRegister1. if there is in the database a token with the same value, this token will be replaced with new token value and this new token will be sent back to the user in the URL using Gmail for example. so, a new URL with the new token will appear in the application logs in our case
//////            why did we have the parameter "HttpServletRequest request" as "HttpServletRequest"??? \/ In summary, the HttpServletRequest request parameter is used to extract information about the current HTTP request, which is necessary for building a complete and accurate URL for the password reset link. it's a common practice to use the HttpServletRequest object when you need information about the current request in a Spring MVC application.
//////            for the parameter "HttpServletRequest request", we can use the methods "getServerName", "getServerPort", "getContextPath", ... like what we did in this case where we used them in the method applicationUrl()
//////            @RequestParam("token") represents the parameter in this request that has the name "token". so, the parameter "?token" in the request
////    {
////        VerificationToken verificationToken
////                =userService.generateNewVerificationToken(oldToken);
////        User user = verificationToken.getUser();
////        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
////        return "Verification link sent";
////    }
//
////    my version
//    @GetMapping("/resendVerifyToken")
//    public String resendVerificationToken(@RequestBody UserModel userModel,
//                                          HttpServletRequest request)
////            sometimes, the user didn't receive the email, which contains the token, when he registers. this may happen due to many reasons, for example, due to certain issue with the server. and this might happen commonly as this may have happened to you personally. that's why there is a button or functionality that can resend the verification email containing the token again to the user
////            to apply this method; when you register as a user, you will get the URL containing the token, but act as if you haven't received it in order to mimic the real life scenario mentioned in the comment above. now, do a get request to the endpoint http://localhost:8080/resendVerifyToken?token=hereIstheValueOfTheTokenRecievedDuringRegister1. if there is in the database a token with the same value, this token will be replaced with new token value and this new token will be sent back to the user in the URL using Gmail for example. so, a new URL with the new token will appear in the application logs in our case
////            why did we have the parameter "HttpServletRequest request" as "HttpServletRequest"??? \/ In summary, the HttpServletRequest request parameter is used to extract information about the current HTTP request, which is necessary for building a complete and accurate URL for the password reset link. it's a common practice to use the HttpServletRequest object when you need information about the current request in a Spring MVC application.
////            for the parameter "HttpServletRequest request", we can use the methods "getServerName", "getServerPort", "getContextPath", ... like what we did in this case where we used them in the method applicationUrl()
////            @RequestParam("token") represents the parameter in this request that has the name "token". so, the parameter "?token" in the request
//    {
//        User user
//                = userService.generateNewVerificationToken(userModel.getEmail());
//
//        log.info("user in controller {}", user);
//        log.info("email in controller {}", userModel.getEmail());
//
//
//        publisher.publishEvent(new RegistrationCompleteEvent(
//                user,
//                applicationUrl(request)
//        ));
////        the above line will create an event that will create a token for the user and send it to him
//
//        return "Verification link sent";
//    }
//
//
//
////    the first version of changing the password of the user ---------- beginning
////    this version is used when the user forgets his old password and wants to reset his password
//    @PostMapping("/resetPassword")
//    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request)
////    this method will allow the user to reset his password using his email which he registered with before. the user sends his email; he is then sent back a URL link containing the passwordResetToken to verify that he wants to reset his password. then using the below method "savePassword", the user will be able to send his new password
////            when the user wants to reset his password, he will be sent back a URL link in Gmail. this link will allow the user to verify that he wants to reset his password
////            why did we have the parameter "HttpServletRequest request" as "HttpServletRequest"??? \/ In summary, the HttpServletRequest request parameter is used to extract information about the current HTTP request, which is necessary for building a complete and accurate URL for the password reset link. it's a common practice to use the HttpServletRequest object when you need information about the current request in a Spring MVC application.
////            for the parameter "HttpServletRequest request", we can use the methods "getServerName", "getServerPort", "getContextPath", ... like what we did in this case where we used them in the method applicationUrl()
////            @RequestBody represents the body of the request sent in this POST request
//    {
//        User user = userService.findUserByEmail(passwordModel.getEmail());
//        String url = "";
//        if(user!=null)
////        the above condition means if there is a user in the database holding the email sent in the POST request
//        {
//            String token = UUID.randomUUID().toString();
//            userService.createPasswordResetTokenForTheUser(user, token);
////    the above line will create PasswordResetToken for the user in the database. this token will allow the user to get an email with a URL link containing this token. by clicking on this link, the user will be able to reset his password
//            url = passwordResetTokenMail(user, applicationUrl(request), token);
////    the above line will return a URL link containing the PasswordResetToken. by clicking on this link, the user will be able to reset his password
//        }
//        return url;
//    }
//
//    @PostMapping("/savePassword")
//    public String savePassword(@RequestParam("token") String token,
//                               @RequestBody PasswordModel passwordModel)
////    this method will allow the user to send his new password after using the above method "resetPassword" to send his email; in order for him to be sent back the passwordResetToken to verify that he wants to reset his password
////            @RequestBody represents the body of the request sent in this POST request
////            @RequestParam("token") represents the parameter in this request that has the name "token". so, the parameter "?token" in the request
//    {
//        String result = userService.validatePasswordResetToken(token);
////        the method "validatePasswordResetToken" validates the PasswordResetToken to check if there is a token in the table PasswordResetToken with the value "token" where this token should not be expired
//        if(!result.equalsIgnoreCase("valid")) {
//            return "Invalid token";
//        }
//        Optional<User> user = userService.getUserByPasswordResetToken(token);
//        if(user.isPresent())
////        isPresent() is a method of the class "Optional"
//        {
//            userService.changePassword(user.get(), passwordModel.getNewPassword());
//            return "Password reset successfully";
//        } else {
//            return "Invalid Token";
//        }
//    }
////    the first version of changing the password of the user ---------- end
//
////            the above two methods represent the first version of changing the password of the user. these two methods will allow the user to change his password by first sending his email, to which he will be sent back a link containing a token; which acts as validation to the request of the user for changing his password. the user then will do a POST request using this link by sending his new password
//
//
////    the second version of changing the password of the user ---------- beginning
//    @PostMapping("/changePassword")
//    public String changePassword(@RequestBody PasswordModel passwordModel)
////            @RequestBody represents the body of the request sent in this POST request
////    this method will allow the user to send his email, old password and his new password all at once. this will allow the user to change his password
//    {
//        User user = userService.findUserByEmail(passwordModel.getEmail());
//        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
//            return "Invalid Old Password";
//        }
//
//        //save new password
//        userService.changePassword(user, passwordModel.getNewPassword());
//        return "Password Changed Successfully";
//    }
////    the second version of changing the password of the user ---------- end
//
////            the above method represents the second version of changing the password of the user. this method will allow the user to change the password by DIRECTLY entering the old password and the new password. if the old password the user entered is wrong, the password won't change. otherwise, the password will change into the new value he wrote
//
//    private String passwordResetTokenMail(User user, String applicationUrl, String token)
////    this method will return a URL link containing the PasswordResetToken. by clicking on this link, the user will be able to reset his password
////            when the user wants to reset his password, he will be sent back a URL link in Gmail. this link will allow the user to verify that he wants to reset his password
////            it is a better habit to put ALL the private methods down below the public methods
//    {
//        //send email to user
//        String url =
//                applicationUrl
//                        + "savePassword?token="
//                        + token;
////        why is the above URL like that???
////        \/ "verifyRegistration?token=": This is a relative path appended to the base URL. it suggests that there is an endpoint or resource named savePassword that the application can handle. the ?token= part indicates that some data (in this case, the verification token) will be passed as a query parameter to the savePassword endpoint.
////        \/ token: The token variable is appended as the value of the token query parameter. this is a common approach for verification processes, where the token is included in the URL to link the user's action (e.g., clicking a link) with a specific piece of data or operation (e.g., verifying a registration).
//
////        in this video we will act as if we're sending the email through gmail
//
//
//        //sendVerificationEmail()
////        I think the below code is just for the sake of showing that we will send back the info to the user through gmail. we used "log" just to show the URL sent back to the user. so, the actual implementation here will be sending the URL back to the user in order for the user to click on it, which will execute/invoke the method "verifyRegistration" in the class "UserServiceImplementation".
//        //so, mocking is done here
//        log.info("click the link to reset your password: {}",
//                url);
////        briefly, what does the above line mean/do??? \/ it logs an informational message containing the verification URL. this message will be visible in the application logs (that is in the console).
//
//        return url;
//    }
//
//    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken)
////            this method is just to resend the verification token email to the user, sending it to the application logs in our case
////            it is a better habit to put ALL the private methods down below the public methods
//    {
//        //send email to user
//        String url =
//                applicationUrl
//                        + "verifyRegistration?token="
//                        + verificationToken.getToken();
////        the tutor of this project/video haven't added "verificationToken.getToken()", he instead added "verificationToken". I added getToken() because verificationToken itself is an entity that has many attributes within, and hence returning verificationToken alone will return all the attributes of the verificationToken, which I don't want, I just want to have the value of the token attribute
////        an edit to the above comment: I looked it up, he did actually write it as it is written above
////        why is the above URL like that???
////        \/ "verifyRegistration?token=": This is a relative path appended to the base URL. it suggests that there is an endpoint or resource named verifyRegistration that the application can handle. the ?token= part indicates that some data (in this case, the verification token) will be passed as a query parameter to the verifyRegistration endpoint.
////        \/ verificationToken: The verificationToken variable is appended as the value of the token query parameter. this is a common approach for verification processes, where the token is included in the URL to link the user's action (e.g., clicking a link) with a specific piece of data or operation (e.g., verifying a registration).
//
////        in this video we will send the email through gmail
//
//
//        //sendVerificationEmail()
////        I think the below code is just for the sake of showing that we will send back the info to the user through gmail. we used "log" just to show the URL sent back to the user. so, the actual implementation here will be sending the URL back to the user in order for the user to click on it, which will execute/invoke the method "verifyRegistration" in the class "UserServiceImplementation".
//        //so, mocking is done here
//        log.info("click the link to verify your account: {}",
//                url);
////        briefly, what does the above line mean/do??? \/ it logs an informational message containing the verification URL. this message will be visible in the application logs (that is in the console).
//    }
//
//    private String applicationUrl(HttpServletRequest request)
////            it is a better habit to put ALL the private methods down below the public methods
////            this applicationUrl() method takes a HttpServletRequest object as a parameter and generates a base URL for the application using information from that request
//    {
//        return "http://" +
//                request.getServerName() +
////                getServerName() above Retrieves the name of the server to which the request was sent. It represents the hostname of the server.
//                ":" +
//                request.getServerPort() +
////                getServerPort() above Retrieves the port number to which the request was sent. It represents the port on which the server is listening for incoming requests.
//                "/" +
//                request.getContextPath();
////        getContextPath() above Retrieves the context path of the web application. The context path is the prefix of the URL that represents the context under which the current application is running.
////        For example, if the application is running on a server with the hostname "localhost," listening on port 8080, and the context path is "/myapp," the generated URL would be "http://localhost:8080/myapp."
//    }
//}

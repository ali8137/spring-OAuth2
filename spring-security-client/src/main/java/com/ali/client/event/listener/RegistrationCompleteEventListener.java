//package com.dailycodebuffer.client.event.listener;
//
//import com.dailycodebuffer.client.entity.User;
//import com.dailycodebuffer.client.event.RegistrationCompleteEvent;
//import com.dailycodebuffer.client.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//
//@Component
////we added the above annotation for the spring container to take into account the below class as a bean. without annotating this class with the annotation @Component, spring won't be able to detect
//@Slf4j
////the above annotation is added because to do logging in this class
////@Slf4j: This annotation is used for Lombok to generate a logger for the class. the logger is named log, and you can use it to log messages in your code.
//public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>
////this class is an event listener to the event class "RegistrationCompleteEvent", because this class implements the interface "ApplicationListener"
////    how does this class work as an event listener??? \/ this class implements the ApplicationListener interface, specifying that it listens for events of type RegistrationCompleteEvent.
//{
//
//    @Autowired
//    private UserService userService;
////    the above is a dependency injected from interface "UserService". it allows us to use methods declared in this interface
//
//    @Override
//    public void onApplicationEvent(RegistrationCompleteEvent event)
////            this is a method declared in the interface "ApplicationListener".
////            the above method will be executed when the event "event" happens, where a token record is created/inserted into the table "VerificationToken". and also a URL will be sent back to the user in order for the user to click on it, where that will redirect him into verifying his registration by activating/invoking the method "verifyRegistration" in the class "UserServiceImplementation"
//    {
//        //create the verification token for the user with the link
//        User user = event.getUser();
////        the above line will get the user from the event done
//
//        System.out.println("user" + user);
//
//        String token = UUID.randomUUID().toString();
////        I think the above line creates a random token String
////        what does the above line mean???
////        \/ UUID: UUID is a 128-bit identifier that is guaranteed to be unique across both space and time. The UUID class in Java is part of the java.util package, and it provides a way to generate these unique identifiers.
////        \/ UUID.randomUUID(): This static method creates a new random UUID. The UUID is generated using a cryptographically strong pseudo-random number generator. This means that the generated UUIDs are highly likely to be unique.
////        \/ toString(): Converts the UUID into its string representation. The resulting string will look like "550e8400-e29b-41d4-a716-446655440000". This string format is commonly used for representing UUIDs.
//
//        userService.saveVerificationTokenForUser(token, user);
////        the above method will store the token of the user into the database
//
//        //send email to user
//        String url =
//                event.getApplicationUrl()
//                        + "verifyRegistration?token="
//                        + token;
////        why is the above URL like that???
////        \/ "verifyRegistration?token=": This is a relative path appended to the base URL. it suggests that there is an endpoint or resource named verifyRegistration that the application can handle. the ?token= part indicates that some data (in this case, the verification token) will be passed as a query parameter to the verifyRegistration endpoint.
////        \/ token: The token variable, which was generated using UUID.randomUUID().toString(), contains a unique identifier. this identifier is appended as the value of the token query parameter. this is a common approach for verification processes, where the token is included in the URL to link the user's action (e.g., clicking a link) with a specific piece of data or operation (e.g., verifying a registration).
//
////        in this video we will send the email through gmail
//
//
//        //sendVerificationEmail()
////        I think the below code is just for the sake of showing that we will send back the info to the user through gmail. we used "log" just to show the URL sent back to the user. so, the actual implementation here will be sending the URL back to the user in order for the user to cick on it, which will execute/invoke the method "verifyRegistration" in the class "UserServiceImplementation".
//        //so, mocking is done here
//        log.info("click the link to verify your account: {}",
//                url);
////        briefly, what does the above line mean/do??? \/ it logs an informational message containing the verification URL. this message will be visible in the application logs (that is in the console).
//    }
//}

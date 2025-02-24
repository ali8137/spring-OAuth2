//package com.dailycodebuffer.client.event;
//
//import com.dailycodebuffer.client.entity.User;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.context.ApplicationEvent;
//
//@Getter
//@Setter
//public class RegistrationCompleteEvent extends ApplicationEvent
////this class represents an application event by extending the class "ApplicationEvent"
////    how does this class work as an event??? \/ the above line means that RegistrationCompleteEvent is a type of ApplicationEvent, inheriting its properties and behaviors.
//{
//
//    private User user;
//    private String applicationUrl;
////    "applicationUrl" is our url that we will send to the user when we send the email to click on it
////    the above two data fields are obviously not dependency injections
//
//    public RegistrationCompleteEvent(User user, String applicationUrl) {
//        super(user);
//        this.user = user;
//        this.applicationUrl = applicationUrl;
//    }
//}

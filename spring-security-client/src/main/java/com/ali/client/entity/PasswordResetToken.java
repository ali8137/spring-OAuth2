//package com.dailycodebuffer.client.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Calendar;
//import java.util.Date;
//
//
//@Entity
//@Data
//@NoArgsConstructor
//public class PasswordResetToken
////the above method is to create a table "PasswordResetToken" in the database. this table will contain the PasswordResetToken tokens of the user each time he wants to reset his password using his email
//{
////it is a good habit to leave one line empty before writing the data fields of this class
//
//    //expiration time is 10 minutes
//    private static final int EXPIRATION_TIME = 10;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    what does the above strategy mean??? \/ the above two annotations specify that the id field is the primary key for the entity and that its values should be automatically generated using an identity strategy, which is typically associated with auto-incrementing columns in a database.
//    private Long id;
//
//    private String token;
//
//    private Date expirationTime;
//
//    @OneToOne(fetch = FetchType.EAGER)
////    The EAGER fetch type indicates that the associated User entity should be fetched eagerly (loaded immediately when the VerificationToken is retrieved).
//    @JoinColumn(name = "user_id",
//            nullable = false,
//            foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
////    the above annotation specifies that the name of the foreign key column in the VerificationToken table should be "user_id," it cannot be nullable, and it defines a foreign key constraint with the name "FK_USER_VERIFY_TOKEN."
//    private User user;
//
//    public PasswordResetToken(User user, String token) {
//        super();
//        this.token = token;
//        this.user = user;
//        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
//    }
//
//    public PasswordResetToken(String token) {
//        super();
//        this.token = token;
//        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
//    }
//
//    private Date calculateExpirationDate(int expirationTime)
////            we can use the recommended version of the above parameter instead
//    {
//
//        Calendar calendar = Calendar.getInstance();
////        This line creates a new instance of the Calendar class, representing the current date and time. The getInstance() method returns a Calendar object initialized with the current date and time based on the system's default time zone and locale.
//        calendar.setTimeInMillis(new Date().getTime());
////        This line sets the time of the Calendar object to the current time. It obtains the current time using new Date().getTime() and sets it using setTimeInMillis().
////        the above line might be redundant, I think
//        calendar.add(Calendar.MINUTE, expirationTime);
////        the above method adds an amount of time into the Calendar object calendar. so, "Calendar.MINUTE": This constant represents the field identifier for minutes in the Calendar class. when you use Calendar.MINUTE as the field, you are specifying that you want to manipulate the minutes part of the date and time. expirationTime: this is the amount of time, in minutes, that you want to add to the current date and time.
//        return new Date(calendar.getTime().getTime());
////        calendar.getTime(): This part returns a Date object representing the date and time stored in the Calendar object. The getTime() method here is called on a Calendar instance, and it returns a Date object. calendar.getTime().getTime(): After obtaining the Date object from the Calendar, another getTime() method is called on this Date object. The getTime() method on a Date object returns the time represented by the Date as the number of milliseconds since the epoch (January 1, 1970, 00:00:00 UTC)
//    }
//}

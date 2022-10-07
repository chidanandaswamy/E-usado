package com.stackroute.slotservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(value="createslot")
@NoArgsConstructor
@AllArgsConstructor
@ToString



public class CreateSlot {


    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";

    @Id
    private long slotId;
    private String sellerName;
    private String sellerEmailId;
    private String buyerName;
    private String buyerEmailId;
    private String slotStatus;
    private String date;
    private String startTime;
    private String endTime;
    private String description;

//    {
//        "slotId":"05",
//            "{
//        "slotId":"05",
//            "sellerName":"dinesh",
//            "sellerEmailId":"bk@gmail.com",
//            "buyerName":"bittu",
//            "buyerEmailId":"bittu@gmail.com",
//    "slotStatus":"progress",
//            "date":"2022-10-10",
//            "startTime":"2022-10-10T18:42:33.049Z",
//            "endTime":"2022-11-10T18:42:33.049Z",
//            "description":"details"
//
//    }


}

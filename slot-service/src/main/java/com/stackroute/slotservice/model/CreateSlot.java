package com.stackroute.slotservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;





@NoArgsConstructor
@AllArgsConstructor
//@ToString

@Data
@Document(value="newSlot")
public class CreateSlot {


    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";

    @Id
    private long slotId;
    private String sellerName;
    private String sellerEmailId;
    private String buyerName;
    private String buyerEmailId;
    private SlotStatus status;
    private String date;
    private String startTime;
    private String endTime;
    private String description;
}

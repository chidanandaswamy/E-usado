package com.stackroute.slotservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;


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

    @NotNull(message = "Enter sellerName")
    private String sellerName;

    @NotNull(message = "Enter seller EmailID")
    private String sellerEmailId;

    @NotNull(message = "Enter buyer Name")
    private String buyerName;

    @NotNull(message = "Enter buyer EmailID")
    private String buyerEmailId;


    private SlotStatus status;

    private List<DateTimeSlots> dateTimeSlots;

    @NotNull(message = "Enter date")
    private String date;

    @NotNull(message = "write description")
    private String description;
}

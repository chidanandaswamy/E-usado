package com.stackroute.slotservice.dto;

import com.stackroute.slotservice.model.DateTimeSlots;
import com.stackroute.slotservice.model.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SlotDto {


    private long slotId;
    private String sellerName;
    private String sellerEmailId;
    private String buyerName;
    private String buyerEmailId;
    private SlotStatus status;
    private List<DateTimeSlots> dateTimeSlots;
    private String date;
    private String description;


}

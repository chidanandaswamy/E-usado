package com.stackroute.slotservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateTimeSlots {
    private String slotDate;
    private List<SellerSlots> sellerSlots;
}

package com.stackroute.slotservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerSlots {
    @Id
    private String slotId;
    private String startTime;
    private String endTime;

}


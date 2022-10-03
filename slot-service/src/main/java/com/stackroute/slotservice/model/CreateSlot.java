package com.stackroute.slotservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Document(value="createslot")
@NoArgsConstructor
@AllArgsConstructor
public class CreateSlot {


    private String slotId;
    private String sellerName;
    private String sellerEmailId;
    private String buyerName;
    private String buyerEmailId;
    private String slotStatus;
    private Date date;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;


}

package com.stackroute.emailservice.model;

import com.stackroute.slotservice.model.DateTimeSlots;

import java.util.List;

public class SlotBooking {

    private long slotId;
    private String sellerName;
    private String sellerEmailId;
    private String buyerName;
    private String buyerEmailId;
//    private SlotStatus status;
    private List<DateTimeSlots> dateTimeSlots;
    private String date;
    private String description;

    public SlotBooking() {
        super();
    }

    public SlotBooking(long slotId, String sellerName, String sellerEmailId, String buyerName, String buyerEmailId, List<DateTimeSlots> dateTimeSlots, String date, String description) {
        super();
        this.slotId = slotId;
        this.sellerName = sellerName;
        this.sellerEmailId = sellerEmailId;
        this.buyerName = buyerName;
        this.buyerEmailId = buyerEmailId;
        this.dateTimeSlots = dateTimeSlots;
        this.date = date;
        this.description = description;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerEmailId() {
        return sellerEmailId;
    }

    public void setSellerEmailId(String sellerEmailId) {
        this.sellerEmailId = sellerEmailId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerEmailId() {
        return buyerEmailId;
    }

    public void setBuyerEmailId(String buyerEmailId) {
        this.buyerEmailId = buyerEmailId;
    }

    public List<DateTimeSlots> getDateTimeSlots() {
        return dateTimeSlots;
    }

    public void setDateTimeSlots(List<DateTimeSlots> dateTimeSlots) {
        this.dateTimeSlots = dateTimeSlots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SlotBooking{" +
                "slotId=" + slotId +
                ", sellerName='" + sellerName + '\'' +
                ", sellerEmailId='" + sellerEmailId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerEmailId='" + buyerEmailId + '\'' +
                ", dateTimeSlots=" + dateTimeSlots +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

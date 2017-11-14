package com.nhn.base.coupon.bean;

public class CouponLogExtends extends CouponLog{
    String eventName = "";
    String itemName = "";
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}

package com.nhn.game.api;

public class AdminSendItemRequest {
    private int ResultCode;
    private long UserID;
    private int RewardType;
    private int RewardId;
    private int RewardCount;
    private int ExpireTime;
    private String Sender;
    public int getResultCode() {
        return ResultCode;
    }
    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }
    public long getUserID() {
        return UserID;
    }
    public void setUserID(long userID) {
        UserID = userID;
    }
    public int getRewardType() {
        return RewardType;
    }
    public void setRewardType(int rewardType) {
        RewardType = rewardType;
    }
    public int getRewardId() {
        return RewardId;
    }
    public void setRewardId(int rewardId) {
        RewardId = rewardId;
    }
    public int getRewardCount() {
        return RewardCount;
    }
    public void setRewardCount(int rewardCount) {
        RewardCount = rewardCount;
    }
    public int getExpireTime() {
        return ExpireTime;
    }
    public void setExpireTime(int expireTime) {
        ExpireTime = expireTime;
    }
    public String getSender() {
        return Sender;
    }
    public void setSender(String sender) {
        Sender = sender;
    }



}

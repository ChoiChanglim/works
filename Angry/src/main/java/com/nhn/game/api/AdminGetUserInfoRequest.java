package com.nhn.game.api;


public class AdminGetUserInfoRequest {
    //api 응답 파라미터명과 완전 똑같이 하자.
    private int ResultCode;
    private long UserID;
    private String Nickname;
    private int Level;
    private int Exp;
    private long FreeGold;
    private long RealGold;
    private int FreeGem;
    private int RealGem;
    private int FriendPoint;    //우정 포인트
    private int Kick;           //0:정상, 1:킥유저
    private int MasterScore;    //일간 랭킹 점수
    private double WeeklyScore; //주간 랭킹 점수
    private long PurchaseTime;      //정액제 상품 구매시간
    private long LastConnectTime; //마지막 접속시간
    private String Nation;    //게임 내 언어

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
    public String getNickname() {
        return Nickname;
    }
    public void setNickname(String nickname) {
        Nickname = nickname;
    }
    public int getLevel() {
        return Level;
    }
    public void setLevel(int level) {
        Level = level;
    }
    public int getExp() {
        return Exp;
    }
    public void setExp(int exp) {
        Exp = exp;
    }
    public long getFreeGold() {
        return FreeGold;
    }
    public void setFreeGold(long freeGold) {
        FreeGold = freeGold;
    }
    public long getRealGold() {
        return RealGold;
    }
    public void setRealGold(long realGold) {
        RealGold = realGold;
    }
    public int getFreeGem() {
        return FreeGem;
    }
    public void setFreeGem(int freeGem) {
        FreeGem = freeGem;
    }
    public int getRealGem() {
        return RealGem;
    }
    public void setRealGem(int realGem) {
        RealGem = realGem;
    }
    public int getFriendPoint() {
        return FriendPoint;
    }
    public void setFriendPoint(int friendPoint) {
        FriendPoint = friendPoint;
    }
    public int getKick() {
        return Kick;
    }
    public void setKick(int kick) {
        Kick = kick;
    }
    public int getMasterScore() {
        return MasterScore;
    }
    public void setMasterScore(int masterScore) {
        MasterScore = masterScore;
    }
    public double getWeeklyScore() {
        return WeeklyScore;
    }
    public void setWeeklyScore(double weeklyScore) {
        WeeklyScore = weeklyScore;
    }
    public long getPurchaseTime() {
        return PurchaseTime;
    }
    public void setPurchaseTime(long purchaseTime) {
        PurchaseTime = purchaseTime;
    }
    public long getLastConnectTime() {
        return LastConnectTime;
    }
    public void setLastConnectTime(long lastConnectTime) {
        LastConnectTime = lastConnectTime;
    }
    public String getNation() {
        return Nation;
    }
    public void setNation(String nation) {
        Nation = nation;
    }






}

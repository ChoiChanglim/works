package com.nhn.game.constant;

import java.util.ArrayList;
import java.util.List;

public enum RewardType {
    Gem(1, "보석지급"),
    Gold(2, "골드지급"),
    Blank_3(3, ""),
    RandomBox(4, "랜덤박스 지급"),
    PiggyRandomDrop(5, "피기 랜덤 지급"),
    Item(6, "아이템 지급"),
    Buff(7, "버프 지급"),
    BottleExp(8, "별조각 지급"),
    Blank_9(9, ""),
    Bird(10, "버드 지급"),
    Exp(11, "유저경험치"),
    Blank_12(11, ""),
    FriendPoint(13, "우정포인트 지급"),
    Piggy(14, "피기 지급"),
    VIPPoint(15, "VIP Point 지급"),
    Blank_16(16, ""),
    BushItembox(17, "부시 아이템 박스 지급"),
    PlayerIcon(18, "왕관아이콘 지급"),
    RandomGold(19, "골드(랜덤하게 골드를 획득)"),
    Deco(20, "데코 건물 지급"),
    Cart(21, "카트 지급");

    private int no;
    private String descript;

    private RewardType(int no, String descript){
        this.no = no;
        this.descript = descript;
    }

    public int getNo(){
        return no;
    }
    public String getDescript(){
        return descript;
    }
    public static List<RewardType> GetRewardTypeListForHTML(){
        List<RewardType> list = new ArrayList<RewardType>();
        for(int i=0;i<RewardType.values().length;i++){
            RewardType o = RewardType.values()[i];
            if(o.getDescript().equals("") == false){
                list.add(RewardType.values()[i]);
            }
        }
        return list;
    }
}

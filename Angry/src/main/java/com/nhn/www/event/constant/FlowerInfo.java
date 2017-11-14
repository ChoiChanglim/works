package com.nhn.www.event.constant;

import java.util.ArrayList;
import java.util.List;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;

public class FlowerInfo {
    //보상
    public enum RewardItem{
        GLOBAL_ENTRY1(500, true, "보석 1,000개"),
        GLOBAL_ENTRY2(250, true, "골드 50,000개"),
        GLOBAL_ENTRY3(150, false, "밤 경험치 물약 3개"),
        GLOBAL_ENTRY4(150, false,  "레드 경험치 물약 3개"),

        KOREAN_ENTRY1(500, true, "갤럭시 S8"),
        KOREAN_ENTRY2(300, true, "백화점 상품권 10만원"),
        KOREAN_ENTRY3(150, true, "코미코 웹툰 자유 이용권"),
        KOREAN_ENTRY4(150, false, "레드 경험치 물약 3개"),

        RECEIPT1(100, false, "하부도면 2개"),
        RECEIPT2(60, false, "보석 10개"),
        RECEIPT3(50, false, "골드 1,000개"),
        RECEIPT4(40, false, "광석 10개"),
        RECEIPT5(30, false, "나무 10개");

        private int flower;
        private boolean isLimited;
        private String product;

        private RewardItem(int flower, boolean isLimited, String product){
            this.flower = flower;
            this.isLimited = isLimited;
            this.product = product;
        }
        public int getFlower(){
            return flower;
        }
        public String getProduct(){
            return product;
        }
        public boolean getIsLimited(){
            return isLimited;
        }
        public synchronized static int GetUsedRewardPoint(String itemCode){
            for(int i=0;i<RewardItem.values().length;i++){
                RewardItem o = RewardItem.values()[i];
                if(o.name().equals(itemCode.toUpperCase())){
                    return o.flower;
                }
            }
            return 0;
        }

        public static RewardItem GetRewardItem(String itemCode){
            for(int i=0;i<RewardItem.values().length;i++){
                RewardItem o = RewardItem.values()[i];
                if(o.name().equals(itemCode.toUpperCase())){
                    return o;
                }
            }
            return null;
        }

        public static List<RewardItem> GetEntryItemList(LanguageEnum languageEnum){
            List<RewardItem> list = new ArrayList<FlowerInfo.RewardItem>();
            if(languageEnum == LanguageEnum.KR){
                list.add(RewardItem.KOREAN_ENTRY1);
                list.add(RewardItem.KOREAN_ENTRY2);
                list.add(RewardItem.KOREAN_ENTRY3);
                list.add(RewardItem.KOREAN_ENTRY4);
            }else{
                list.add(RewardItem.GLOBAL_ENTRY1);
                list.add(RewardItem.GLOBAL_ENTRY2);
                list.add(RewardItem.GLOBAL_ENTRY3);
                list.add(RewardItem.GLOBAL_ENTRY4);
            }
            return list;
        }
        public static List<RewardItem> GetReceiptItemList(){
            List<RewardItem> list = new ArrayList<FlowerInfo.RewardItem>();
            list.add(RewardItem.RECEIPT1);
            list.add(RewardItem.RECEIPT2);
            list.add(RewardItem.RECEIPT3);
            list.add(RewardItem.RECEIPT4);
            list.add(RewardItem.RECEIPT5);
            return list;
        }

    }


    //획득한 꽃잎
    public enum MissionLog{
        EVENT_PIGGY_CAPTURE(1, "게으른 피기 잡기 1회"),
        EVENT_FRIEND_GEM_SEND(1, "친구에게 보석 선물 1회"),
        EVENT_COMPLETE_QUEST(2, "게시판 퀘스트 1회"),
        EVENT_BATTLE_END(2, "월드 전투 승리"),
        EVENT_PIGGY_LEVELUP(5, "피기 레벨업"),
        EVENT_BIRD_LEVELUP(5, "버드 레벨업"),
        EVENT_CART_LEVELUP(5, "카트 레벨업");

        private int flower;
        private String descript;

        private MissionLog(int flower, String descript){
            this.flower = flower;
            this.descript = descript;
        }
        public int getFlower(){
            return flower;
        }
        public String getDescript(){
            return descript;
        }
        public static List<String> GetMissionLogList(){
            List<String> list = new ArrayList<String>();
            for(int i=0;i<MissionLog.values().length;i++){
                MissionLog o = MissionLog.values()[i];
                list.add(o.name());
            }
            return list;
        }

        public synchronized static int GetMissionPoint(String log){
            for(int i=0;i<MissionLog.values().length;i++){
                MissionLog o = MissionLog.values()[i];
                if(o.name().equals(log.toUpperCase())){
                    return o.flower;
                }
            }
            return 0;
        }
    }

    //결과 메시지
    public enum ResultMessage{
        INVALID_ITEM_CODE("잘못된 아이템 코드"),
        NOT_ENOUGH_FLOWER("꽃잎 갯수 부족"),
        LEAVING_OUT("탈락"),
        SYSTEM_ERROR("시스템 오류"),
        SOLD_OUT("지급 가능한 갯수 모두 소진"),
        TODAY_SOLD_OUT("오늘 지급 가능한 갯수 모두 소진"),
        ITEM_SEND_SUCCESS("지급 성공"),
        ITEM_ENTRY_SUCCESS("응모 성공");

        private String message;
        private ResultMessage(String message){
            this.message = message;
        }
        public String getMessage(){
            return message;
        }
    }
}

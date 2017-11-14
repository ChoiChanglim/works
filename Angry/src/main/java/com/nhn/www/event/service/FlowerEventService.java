package com.nhn.www.event.service;

import java.util.HashMap;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.www.event.constant.FlowerInfo.ResultMessage;
import com.nhn.www.event.constant.FlowerInfo.RewardItem;

public interface FlowerEventService {
    public int getTotalFlowerCount(long usn);
    public int getUsedFlowerCount(long usn);
    public ResultMessage entryItem(long usn, RewardItem rewardItem, LanguageEnum languageEnum);
    public HashMap<String, Integer> myWinStatus(long usn, LanguageEnum languageEnum);
}

package com.nhn.www.event.service;

import java.util.HashMap;
import java.util.List;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.www.event.bean.BannerEventItem;
import com.nhn.www.event.bean.BannerEventItemAndImg;
import com.nhn.www.event.bean.BannerEventItemImg;
import com.nhn.www.event.bean.BannerEventItemImgKey;
import com.nhn.www.event.bean.BannerEventResult;
import com.nhn.www.event.bean.BannerEventResultKey;

public interface BannerEventService {
    public boolean isPossibleApply(long sno, LanguageEnum languageEnum);
    public List<BannerEventItemAndImg> getBannerEventItemAndImg(LanguageEnum languageEnum);
    public boolean isBannerEventTodayComplete(long sno);
    public BannerEventItemAndImg bannerEventApply(long sno, LanguageEnum languageEnum);
    public HashMap<String, Object> createWebviewData(String imgUrl);
    public void bannerEventItemSend(long sno, BannerEventItemAndImg item);

    /**
     * 어드민
     */
    public List<BannerEventItem> adminBannerList();
    public List<BannerEventResult> adminBannerResultList();
    public List<BannerEventItemImg> adminBannerItemImgList();
    public void insertBannerEventItem(BannerEventItem item);
    public void updateBannerEventItem(BannerEventItem item);
    public void insertBannerEventItemImg(BannerEventItemImg img);
    public void updateBannerEventItemImg(BannerEventItemImg img);
    public void deleteBannerEventResult(BannerEventResultKey resultKey);
    public List<BannerEventItemAndImg> getLiveItemsAndLocalUrl();
    public List<BannerEventItemAndImg> getLiveItems();
    public void deleteBannerImg(BannerEventItemImgKey imgKey);


}

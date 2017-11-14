package com.nhn.base.notice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.base.constant.LangeageIndex.LanguageEnum;
import com.nhn.base.notice.bean.Notice;
import com.nhn.base.notice.bean.PopupNotice;
import com.nhn.base.notice.mapper.NoticeMapper;
import com.nhn.base.notice.mapper.NoticeSubMapper;

@Transactional(value="baseTransactionManager", readOnly=false)
@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    NoticeSubMapper noticeSubMapper;


    /**
     * 뷰페이지 연결.
     */
    @Override
    public Notice getNotice(int idx, LanguageEnum languageEnum) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("idx", idx);
        param.put("lang_idx", languageEnum.getLanguageIndex());

        Notice notice = noticeMapper.selectNoticeEventByIdx(param);

        Notice updateNotice = new Notice();
        updateNotice.setIdx(idx);
        updateNotice.setVisit(notice.getVisit() + 1);
        noticeMapper.updateByPrimaryKeySelective(updateNotice);

        return notice;
    }


    /**
     * 공지팝업정보 클라에 내려주기
     */
    @Override
    public List<HashMap<String, Object>> getPopupNoticeList(LanguageEnum languageEnum, int level, long regist_time) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("languageIndex", languageEnum.getLanguageIndex());
        param.put("idx_type", "W");

        List<PopupNotice> popupNoticeList = noticeMapper.selectPopupNotice(param);

        List<HashMap<String, Object>> jsonList = new ArrayList<HashMap<String,Object>>();

        Iterator<PopupNotice> iter = popupNoticeList.iterator();
        while(iter.hasNext()){
            PopupNotice notice = iter.next();
            StringBuffer sb = new StringBuffer(notice.getViewNation());
            char viewnationByLanguageIndexChar = sb.charAt(languageEnum.getLanguageIndex()-1);
            if(String.valueOf(viewnationByLanguageIndexChar).equals("0")){
                iter.remove();
                continue;
            }

            //레벨
            if(notice.getViewLevel() > 0){
                if(level < notice.getViewSLevel()){
                    iter.remove();
                    continue;
                }
                if(level > notice.getViewELevel()){
                    iter.remove();
                    continue;
                }
            }

            //가입일
            if(notice.getViewJoin() > 0){
                if(null != notice.getViewSJoin() && null != notice.getViewEJoin()){
                    if(regist_time < (notice.getViewSJoin().getTime() / 1000) ){
                        iter.remove();
                        continue;
                    }
                    if(regist_time > (notice.getViewEJoin().getTime() / 1000) ){
                        iter.remove();
                        continue;
                    }
                }
            }

            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("key", notice.getKey());
            json.put("img_addr", notice.getImgAddr());
            json.put("link_addr", notice.getLinkAddr());
            json.put("event_view", notice.getEventDel());
            boolean newpopup = false;
            if("1".equals(notice.getNewPopup())){
                newpopup = true;
            }
            json.put("new_popup", newpopup);
            json.put("event_start", (notice.getEventStartDate().getTime() / 1000) );
            json.put("event_end", (notice.getEventEndDate().getTime() / 1000) );
            boolean is_param = false;
            if(notice.getLinkAddr().contains("angry.hangame.com") && notice.getLinkAddr().contains("?") == false){ //hangame.com 도메인 그리고, 링크에 직접 ?파라미터를 안붙였으면
                is_param = true;
            }
            json.put("param", is_param);
            jsonList.add(json);

        }



        return jsonList;
    }

    /**
     * 로딩 이미지 정보 내려주기
     */
    @Override
    public List<HashMap<String, Object>> getLoadingImgList(LanguageEnum languageEnum) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("languageIndex", languageEnum.getLanguageIndex());
        param.put("idx_type", "L");

        List<PopupNotice> list = noticeMapper.selectLoadingImgList(param);

        List<HashMap<String, Object>> jsonList = new ArrayList<HashMap<String,Object>>();

        Iterator<PopupNotice> iter = list.iterator();
        while(iter.hasNext()){
            PopupNotice notice = iter.next();
            StringBuffer sb = new StringBuffer(notice.getViewNation());
            char viewnationByLanguageIndexChar = sb.charAt(languageEnum.getLanguageIndex()-1);
            if(String.valueOf(viewnationByLanguageIndexChar).equals("0")){
                iter.remove();
                continue;
            }

            HashMap<String, Object> json = new HashMap<String, Object>();
            json.put("key", notice.getKey());
            json.put("img_addr", notice.getImgAddr());
            //json.put("event_start", (notice.getEventStartDate().getTime() / 1000) );
            //json.put("event_end", (notice.getEventEndDate().getTime() / 1000) );
            jsonList.add(json);

        }



        return jsonList;
    }

}

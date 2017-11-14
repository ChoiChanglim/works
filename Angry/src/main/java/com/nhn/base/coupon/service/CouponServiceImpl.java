package com.nhn.base.coupon.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhn.base.constant.HspService;
import com.nhn.base.coupon.bean.Coupon;
import com.nhn.base.coupon.bean.CouponExample;
import com.nhn.base.coupon.bean.CouponItem;
import com.nhn.base.coupon.bean.CouponItemExample;
import com.nhn.base.coupon.bean.CouponLog;
import com.nhn.base.coupon.bean.CouponLogExample;
import com.nhn.base.coupon.bean.CouponLogExtends;
import com.nhn.base.coupon.mapper.CouponItemMapper;
import com.nhn.base.coupon.mapper.CouponLogMapper;
import com.nhn.base.coupon.mapper.CouponMapper;
import com.nhn.common.exception.CustomException;
import com.nhn.common.util.DateUtil;
import com.nhn.common.util.HttpWebURLConnection;
import com.nhn.common.util.HttpWebURLResponse;
import com.nhn.common.util.StringUtil;
import com.nhn.game.service.AdminApiService;
import com.nhn.hsp.Games;
import com.nhncorp.hsp.connector.response.APlayedGameLog;

@Service
@Transactional(value="baseTransactionManager", readOnly=false)
public class CouponServiceImpl implements CouponService{
    private static Logger LOG = LoggerFactory.getLogger(CouponServiceImpl.class);
    public static String HgbCouponMatchingPrefix = "ANGRY";


    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CouponItemMapper couponItemMapper;

    @Autowired
    CouponLogMapper couponLogMapper;

    @Autowired
    AdminApiService adminApiService;

    @Autowired
    HspService hspService;

    @Value("#{propinfo['Zone']}")
    private String Zone;

    public enum CouponRequestApi{
        ALPHA("http://alpha-coupons.hangame.com/couponapi/register.nhn"),
        REAL("http://coupons.hangame.com/couponapi/register.nhn");

        private String url;
        private CouponRequestApi(String url){
            this.url = url;
        }
        public String getUrl(){
            return url;
        }
        public static CouponRequestApi GetCouponRegustApi(String zone){
            if("REAL".equals(zone.toUpperCase())){
                return CouponRequestApi.REAL;
            }
            return CouponRequestApi.ALPHA;
        }
    }

    public enum CouponRequestMethod{
        RegisterAndUse("registerAndUse"),
        CancelRegisterAndUse("cancelRegisterAndUse");

        private String name;
        private CouponRequestMethod(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }

    }


    /**
     * HGB 쿠폰시스템 유효성 검사
     * @param cnum
     * @return
     */
    public JSONObject HgbCouponApi(String cnum, String sno, CouponRequestMethod couponRequestMethod) {
        String hgbCouponValidationCheckUrl = CouponRequestApi.GetCouponRegustApi(Zone).getUrl();
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("m", couponRequestMethod.getName());
        param.put("memberid", sno);
        param.put("couponCode", cnum);
        HttpWebURLConnection conn = new HttpWebURLConnection(hgbCouponValidationCheckUrl, param);
        try {
            HttpWebURLResponse response =  conn.sendGet();
            if(200 == response.getResponseCode()){
                return response.getResultJson();
            }
        } catch (Exception e) {
            LOG.error(e.toString());
            throw CustomException.HTTP_WEB_CALL_EXCEPTION;
        }
        return null;
    }

    /**
     * HGB ITEMCODE 검사
     * @param itemcode
     * @return
     */
    public int hgbItemCodeValid(String itemcode, String cnum, String sno){
        String eventIdxStr = itemcode.trim().replace(HgbCouponMatchingPrefix, "");
        if(StringUtil.isDigit(eventIdxStr) == false){
            LOG.error("itemcode:"+itemcode+", eventIdx:"+eventIdxStr);
            HgbCouponApi(cnum, sno, CouponRequestMethod.CancelRegisterAndUse);  //실패시 쿠폰 사용 취소
            throw CustomException.COUPON_INVALID_HGB_ITEMCODE;
        }
        return Integer.valueOf(eventIdxStr);
    }

    /**
     * 쿠폰 상품을 HGB 쿠폰 시스템에서 유효성 검사 후 게임메일로 지급
     */
    @Override
    public synchronized String HgbCouponCheckAndItemSend(String cnum, String sno) {
        JSONObject hgbCouponResult = HgbCouponApi(cnum, sno, CouponRequestMethod.RegisterAndUse);
        String resultCode = (String)hgbCouponResult.get("code");
        if(null != hgbCouponResult && resultCode.equals("0")){
            String itemcode = (String)hgbCouponResult.get("itemcode");
            int eventIdx = hgbItemCodeValid(itemcode, cnum, sno);
            //Coupon coupon = getCouponInfo(eventIdx);
            List<CouponItem> giveCouponItemList = getItemListByEventIdx(eventIdx);
            if(giveCouponItemList.size() == 0){
                HgbCouponApi(cnum, sno, CouponRequestMethod.CancelRegisterAndUse);  //상품이 없으므로 쿠폰 사용 취소
                couponLogging(sno, cnum, eventIdx, "0", "0", "0", "0", 2, null, "EMPTY_ITEM", null);
                return "99";
            }
            Iterator<CouponItem> iter = giveCouponItemList.iterator();
            while(iter.hasNext()){
                CouponItem couponItem = iter.next();
                int sussType = 1;
                boolean isSendItemMail = adminApiService.isSendItemMail(Long.valueOf(sno), couponItem.getItemtype(), couponItem.getItemid(), couponItem.getItemcount());
                if(isSendItemMail == false){
                    HgbCouponApi(cnum, sno, CouponRequestMethod.CancelRegisterAndUse);  //실패시 쿠폰 사용 취소
                    couponLogging(sno, cnum, eventIdx, couponItem.getItempart().toString(), couponItem.getItemtype().toString(), couponItem.getItemid().toString(), couponItem.getItemcount().toString(), 2, null, "SendItemMailFail", null);
                    return "99";
                }
                couponLogging(sno, cnum, eventIdx, couponItem.getItempart().toString(), couponItem.getItemtype().toString(), couponItem.getItemid().toString(), couponItem.getItemcount().toString(), sussType, null, null, null);
            }
        }
        return resultCode;
    }

    /**
     * 쿠폰으로 지급할 상품 조회
     * @param eventIdx
     * @return
     */
    private List<CouponItem> getItemListByEventIdx(int eventIdx) {
        CouponItemExample couponItemExample = new CouponItemExample();
        couponItemExample.createCriteria().andEventIdxEqualTo(eventIdx);
        couponItemExample.setOrderByClause("num asc");
        List<CouponItem> list = couponItemMapper.selectByExample(couponItemExample);
        return list;
    }

    /**
     * 쿠폰 조회
     * @param eventIdx
     * @return
     */
    private Coupon getCouponInfo(int eventIdx) {
        Coupon coupon = couponMapper.selectByPrimaryKey(eventIdx);
        return coupon;
    }


    /**
     * 쿠폰 사용 로그
     */
    @Override
    public void couponLogging(String sno, String cnum, int couponIdx, String itemPart, String itemType, String itemId, String itemCount, int sussType, Integer temp1, String temp2, Integer temp3) {
        CouponLog couponLog = new CouponLog();
        couponLog.setSusstype(1);
        couponLog.setSno(Long.valueOf(sno));
        couponLog.setEventIdx(couponIdx);
        couponLog.setCnum(cnum);
        couponLog.setItempart(itemPart);
        couponLog.setItemtype(itemType);
        couponLog.setItemid(itemId);
        couponLog.setItemcount(itemCount);
        couponLog.setRegdate(new Date());
        if(temp1 != null){
            couponLog.setTemp1(temp1);
        }
        if(temp2 != null){
            couponLog.setTemp2(temp2);
        }
        if(temp3 != null){
            couponLog.setTemp3(temp3);
        }
        couponLogMapper.insertSelective(couponLog);

    }


    @Override
    public String CodeCouponCheckAndItemSend(String cnum, String sno) {
        String result = "0";

        boolean isCodeCouponValid = isCodeCouponValid(cnum);
        boolean isUsedCodeCoupon = isUsedCodeCoupon(Long.valueOf(sno), cnum);
        boolean isDateVaild = couponDateValid(cnum);
        if(isCodeCouponValid == false){
            return "84";
        }
        if(HottracksCouponInfo.IsValidInCode(cnum) == true){
            LOG.error("핫트렉스 쿠폰을 코드형으로 사용이 불가.");
            return "84";
        }
        if(isUsedCodeCoupon == true){
            return "81";
        }
        if(isDateVaild == false){
            return  "82";
        }


        //보상지급
        List<Coupon> codeTypeCouponEventList = getEventInfoByCouponCode(cnum);


        Iterator<Coupon> eventListIter = codeTypeCouponEventList.iterator();
        while(eventListIter.hasNext()){
            Coupon coupon = eventListIter.next();
            //tempcode1 null or 빈값이 아니라면
            //tempcode1 이후 가입자만 사용가능. yyyyMMddHHmmss
            String tempcode1 = StringUtil.nullToBlank(coupon.getTempcode1()).trim();
            if("".equals(tempcode1) == false && StringUtil.isDigit(tempcode1) == true ){
              //코드형 쿠폰은 어느 시점 이후 가입자만 받을수있는 조건이 있다.
                APlayedGameLog playLog = hspService.getPlayedGameLog(Long.valueOf(sno)).getGameLogMap().get(Games.Angry);
                long firstloginTime = Long.valueOf(playLog.getFirstLoginTime());  //yyyyMMddHHmmss

                long CouponValidJoinDate = Long.valueOf(tempcode1);
                if(CouponValidJoinDate >  firstloginTime){
                    LOG.info("쿠폰 사용은 특정기간 이후 가입자만 받을수있다."+sno+":"+firstloginTime);
                    return "90";
                }
            }
            List<CouponItem> giveCouponItemList = getItemListByEventIdx(coupon.getIdx());
            Iterator<CouponItem> iter = giveCouponItemList.iterator();
            while(iter.hasNext()){
                CouponItem couponItem = iter.next();
                int sussType = 1;
                boolean isSendItemMail = adminApiService.isSendItemMail(Long.valueOf(sno), couponItem.getItemtype(), couponItem.getItemid(), couponItem.getItemcount());
                if(isSendItemMail == false){
                    result = "99";
                    sussType = 2;
                }
                couponLogging(sno, cnum, couponItem.getEventIdx(), couponItem.getItempart().toString(), couponItem.getItemtype().toString(), couponItem.getItemid().toString(), couponItem.getItemcount().toString(), sussType, null, null, null);
            }
        }

        return result;
    }

    /**
     * 코드형 쿠폰번호로 쿠폰정보찾기
     * @param cnum
     * @return
     */
    private List<Coupon> getEventInfoByCouponCode(String cnum) {
        CouponExample couponExample = new CouponExample();
        String dateValid = DateUtil.getCurrentDateString("yyyy-MM-dd");
        couponExample.createCriteria().andCtypeEqualTo(1).andCodenameEqualTo(cnum).andStartdateLessThanOrEqualTo(dateValid).andEnddateGreaterThanOrEqualTo(dateValid);
        List<Coupon> list = couponMapper.selectByExample(couponExample);

        return list;
    }

    /**
     * 쿠폰 사용기한 체크
     * @param cnum
     * @return
     */
    private boolean couponDateValid(String cnum) {
        CouponExample couponExample = new CouponExample();
        String dateValid = DateUtil.getCurrentDateString("yyyy-MM-dd");
        couponExample.createCriteria().andCtypeEqualTo(1).andCodenameEqualTo(cnum).andStartdateLessThanOrEqualTo(dateValid).andEnddateGreaterThanOrEqualTo(dateValid);
        int count = couponMapper.countByExample(couponExample);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * 코드형 쿠폰 사용유무 확인
     * @param sno
     * @param cnum
     * @return
     */
    private boolean isUsedCodeCoupon(long sno, String cnum) {
        CouponLogExample couponLogExample = new CouponLogExample();
        couponLogExample.createCriteria().andSnoEqualTo(sno).andCnumEqualTo(cnum).andSusstypeEqualTo(1);
        int count = couponLogMapper.countByExample(couponLogExample);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * 코드형 쿠폰 유효성 검사
     * @param cnum
     * @return
     */
    private boolean isCodeCouponValid(String cnum) {
        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDelEqualTo(0).andCtypeEqualTo(1).andCodenameEqualTo(cnum);
        int count = couponMapper.countByExample(couponExample);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * 내 쿠폰 사용내역 카운트
     */
    @Override
    public int getMyCouponUsedListCount(long sno) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("sno", sno);
        int count = couponLogMapper.myUsedCouponListCount(param);
        return count;
    }

    /**
     * 내쿠폰 사용내역
     */
    @Override
    public List<CouponLogExtends> getMyCouponUsedList(long sno, int skip, int limit) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("sno", sno);
        param.put("skip", skip);
        param.put("limit", limit);
        List<CouponLogExtends> list = couponLogMapper.myUsedCouponList(param);
        return list;
    }

    /**
     * 핫트렉스쿠폰 사용
     */
    @Override
    public String HottracksCouponCheckAndItemSend(String cnum, String sno) {
        String result = "0";
        boolean isUsedCodeCoupon = isUsedCodeHottracksCoupon(Long.valueOf(sno));
        if(HottracksCouponInfo.IsValid(cnum) == false){
            return "84";
        }
        if(isUsedCodeCoupon == true){
            return "81";
        }
        HottracksCouponInfo item = HottracksCouponInfo.GetItem(cnum);

        boolean isDateVaild = couponDateValid(item.getCode());
        if(isDateVaild == false){
            return  "82";
        }

        //보상지급
        List<Coupon> codeTypeCouponEventList = getEventInfoByCouponCode(item.getCode());

        Iterator<Coupon> eventListIter = codeTypeCouponEventList.iterator();
        while(eventListIter.hasNext()){
            Coupon coupon = eventListIter.next();
            List<CouponItem> giveCouponItemList = getItemListByEventIdx(coupon.getIdx());
            Iterator<CouponItem> iter = giveCouponItemList.iterator();
            while(iter.hasNext()){
                CouponItem couponItem = iter.next();
                int sussType = 1;
                boolean isSendItemMail = adminApiService.isSendItemMail(Long.valueOf(sno), couponItem.getItemtype(), couponItem.getItemid(), couponItem.getItemcount());
                if(isSendItemMail == false){
                    result = "99";
                    sussType = 2;
                }
                couponLogging(sno, item.getCode(), couponItem.getEventIdx(), couponItem.getItempart().toString(), couponItem.getItemtype().toString(), couponItem.getItemid().toString(), couponItem.getItemcount().toString(), sussType, null, cnum, null);
            }
        }

        return result;
    }
    private boolean isUsedCodeHottracksCoupon(Long sno) {
        CouponLogExample couponLogExample = new CouponLogExample();
        List<String> hottracksCouponList = new ArrayList<String>();
        hottracksCouponList.add(HottracksCouponInfo.FIRST.getCode());
        hottracksCouponList.add(HottracksCouponInfo.SECOND.getCode());
        hottracksCouponList.add(HottracksCouponInfo.THIRD.getCode());

        couponLogExample.createCriteria().andSnoEqualTo(sno).andCnumIn(hottracksCouponList).andSusstypeEqualTo(1);
        int count = couponLogMapper.countByExample(couponLogExample);
        if(count > 0){
            return true;
        }
        return false;
    }
    public enum HottracksCouponInfo{
        FIRST("1673", 0, 1, 20),
        SECOND("4911", 0, 1, 40),
        THIRD("2850", 0, 1, 60),
        BONUS("**", 105, 21, 1);

        private String code;
        private int item_id;
        private int item_type;
        private int item_count;

        private HottracksCouponInfo(String code, int item_id, int item_type, int item_count){
            this.code = code;
            this.item_id = item_id;
            this.item_type = item_type;
            this.item_count = item_count;
        }

        public String getCode(){
            return code;
        }
        public int getItemId(){
            return item_id;
        }
        public int getItemType(){
            return item_type;
        }
        public int getItemCount(){
            return item_count;
        }
        public static boolean IsValid(String cnum){
            String secretCode = cnum.trim().substring(6, 10);
            if(StringUtil.isDigit(secretCode) == false){
                return false;
            }
            for(int i=0;i<HottracksCouponInfo.values().length;i++){
                HottracksCouponInfo o = HottracksCouponInfo.values()[i];
                if(o.getCode().equals(secretCode)){
                    return true;
                }
            }

            return false;
        }
        public static HottracksCouponInfo GetItem(String cnum){
            String secretCode = cnum.trim().substring(6, 10);
            for(int i=0;i<HottracksCouponInfo.values().length;i++){
                HottracksCouponInfo o = HottracksCouponInfo.values()[i];
                if(o.getCode().equals(secretCode)){
                    return o;
                }
            }

            return null;
        }

        //핫트렉스 시크릿번호가 코드형으로 사용되어지는걸 막아야된다.
        public static boolean IsValidInCode(String cnum){
            //String secretCode = cnum.trim().substring(6, 10);
            for(int i=0;i<HottracksCouponInfo.values().length;i++){
                HottracksCouponInfo o = HottracksCouponInfo.values()[i];
                if(o.getCode().equals(cnum)){
                    return true;
                }
            }

            return false;
        }
    }

}

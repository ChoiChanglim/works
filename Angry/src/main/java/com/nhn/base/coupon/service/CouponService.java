package com.nhn.base.coupon.service;

import java.util.List;

import com.nhn.base.coupon.bean.CouponLogExtends;

public interface CouponService {
    public String HgbCouponCheckAndItemSend(String cnum, String sno) ;
    public void couponLogging(String sno, String cnum, int evntIdx, String itemPart, String itemType, String itemId, String itemCount, int sussType, Integer temp1, String temp2, Integer temp3);
    public String CodeCouponCheckAndItemSend(String cnum, String sno);
    public int getMyCouponUsedListCount(long sno);
    public List<CouponLogExtends> getMyCouponUsedList(long sno, int begin, int rowCount);
    public String HottracksCouponCheckAndItemSend(String upperCase, String valueOf);
}

package com.nhn.game.service;

import java.util.Calendar;
import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nhn.common.exception.CustomException;
import com.nhn.common.util.HttpWebURLConnection;
import com.nhn.common.util.HttpWebURLResponse;
import com.nhn.game.api.AdminGetUserInfoRequest;
import com.nhn.game.constant.APIObject;
import com.nhn.game.constant.ApiConstant.AdminType;
import com.nhn.game.constant.ApiConstant.ApiEnum;
import com.nhn.game.constant.RewardType;

@Service
public class AdminApiServiceImpl implements AdminApiService{
    private static Logger LOG = LoggerFactory.getLogger(AdminApiServiceImpl.class);

    @Value("#{propinfo['Zone']}")
    private String Zone;

    @Value("#{propinfo['AdminApiUrl']}")
    private String AdminApiUrl;

    /**
     * admin api 호출 모듈.
     * @param param
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public HttpWebURLResponse AdminHttpCall(HashMap<String, Object> param) {
        if(null == AdminApiUrl){
            throw CustomException.ANGRY_ADMIN_API_REQUEST_URL_EXCEPTION;
        }

        ApiEnum apiEnum = (ApiEnum)param.get("Command");
        String[] params = apiEnum.getParams().replace("{", "").replace("}", "").split("[,]");

        for(int i=0;i<params.length;i++){
            if(param.containsKey(params[i].trim()) == false){
                LOG.error(params[i].trim() +" is empty!");
                throw CustomException.EMPTY_PARAMETER;
            }
        }
        param.put("Command", apiEnum.name());

        long usn = (Long)param.get("UserID");
        boolean hasUser = hasAdminGetUserInfoRequest(usn);
        if(hasUser == false){
            LOG.error("UserID:"+usn);
            throw CustomException.INVALID_USER;
        }

        HttpWebURLConnection conn = new HttpWebURLConnection(AdminApiUrl, param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("AdminID", AdminType.WEB_EVENT.getId());
        headerMap.put("Content-Type", "application/json");
        conn.setHeader(headerMap);
        HttpWebURLResponse response = new HttpWebURLResponse();
        try {
            response =  conn.sendPostByParamTypeJson();
            LOG.info("Admin api call - "+new JSONObject(param)+" | "+AdminApiUrl+" | "+response.getResponseCode()+", "+response.getResult());
            if(200 == response.getResponseCode()){
                return response;
            }
        } catch (Exception e) {
            LOG.error("Admin api call Exception:"+e.toString());
            throw CustomException.ANGRY_ADMIN_API_RESPONSE_EXCEPTION;
        }
        return response;
    }

    /**
     * 유저 기본 정보 조회
     * @param usn
     * @return
     */
    @Override
    public AdminGetUserInfoRequest getAdminGetUserInfoRequest(long usn){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Command", ApiEnum.AdminGetUserInfoRequest.name());
        param.put("UserID", usn);

        HttpWebURLResponse result = new HttpWebURLResponse();

        HttpWebURLConnection conn = new HttpWebURLConnection(AdminApiUrl, param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("AdminID", AdminType.WEB_EVENT.getId());
        headerMap.put("Content-Type", "application/json");
        conn.setHeader(headerMap);
        try {
            result =  conn.sendPostByParamTypeJson();
            LOG.debug("AdminGetUserInfoRequest api call - "+new JSONObject(param)+" | "+AdminApiUrl+" | "+result.getResponseCode()+", "+result.getResult());
            if(200 == result.getResponseCode()){
                if(result.getResultJson().has("ResultCode")){
                    if(0 == result.getResultJson().getInt("ResultCode")){
                        APIObject<AdminGetUserInfoRequest> mapper = new APIObject<AdminGetUserInfoRequest>(AdminGetUserInfoRequest.class);
                        AdminGetUserInfoRequest request = mapper.getObject(result.getResultJson());
                        return request;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("AdminGetUserInfoRequest api call Exception:"+e.toString());
            throw CustomException.ANGRY_ADMIN_API_RESPONSE_EXCEPTION;
        }


        return null;
    }
    /**
     * 유저의 유효성 검증
     * @param usn
     * @return
     */
    public boolean hasAdminGetUserInfoRequest(long usn){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Command", ApiEnum.AdminGetUserInfoRequest.name());
        param.put("UserID", usn);

        HttpWebURLResponse result = new HttpWebURLResponse();

        HttpWebURLConnection conn = new HttpWebURLConnection(AdminApiUrl, param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("AdminID", AdminType.WEB_EVENT.getId());
        headerMap.put("Content-Type", "application/json");
        conn.setHeader(headerMap);
        try {
            result =  conn.sendPostByParamTypeJson();
            LOG.debug("AdminGetUserInfoRequest api call - "+new JSONObject(param)+" | "+AdminApiUrl+" | "+result.getResponseCode()+", "+result.getResult());
            if(200 == result.getResponseCode()){
                if(result.getResultJson().has("ResultCode")){
                    if(0 == result.getResultJson().getInt("ResultCode")){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("AdminGetUserInfoRequest api call Exception:"+e.toString());
            throw CustomException.ANGRY_ADMIN_API_RESPONSE_EXCEPTION;
        }
        return false;
    }

    /**
     * 우편함에 아이템 지급
     * true: 지급 성공
     * false: 지급 실패
     */
    @Override
    public boolean isSendItemMail(long usn, int itemType, int itemId, long itemCount) {
        try{
            //게임서버에 지급
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("Command", ApiEnum.AdminSendItemRequest);
            param.put("UserID", usn);
            param.put("RewardType", itemType);
            param.put("RewardId", itemId);
            param.put("RewardCount", itemCount);
            Calendar c = Calendar.getInstance();
            if(itemType == RewardType.Cart.getNo()){
                c.add(Calendar.DATE, 7);
            }else{
                c.add(Calendar.DATE, 3);
            }

            param.put("ExpireTime", (c.getTimeInMillis() / 1000));
            param.put("Sender", "WEB");

            HttpWebURLResponse result = AdminHttpCall(param);

            if(200 == result.getResponseCode()){
                if(result.getResultJson().has("ResultCode")){
                    if(0 == result.getResultJson().getInt("ResultCode")){
                        return true;
                    }
                }

            }else{
                LOG.error("GAME MAIL API FAIL - Http Response code:"+result.getResponseCode());
            }
        }catch(Exception e){
            LOG.error("GAME MAIL API EXCEPTION - "+ e.toString());
        }
        return false;
    }


}

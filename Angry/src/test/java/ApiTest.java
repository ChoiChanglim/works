import java.util.HashMap;

import com.nhn.common.exception.CustomException;
import com.nhn.common.util.HttpWebURLConnection;
import com.nhn.common.util.HttpWebURLResponse;
import com.nhn.game.constant.ApiConstant.AdminType;
import com.nhn.game.constant.ApiConstant.ApiEnum;


public class ApiTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        long usn=88899626759840284L;
        boolean flag = hasAdminGetUserInfoRequest(usn);
        System.err.println(flag);
        //APIObject<AdminGetUserInfoRequest> mapper = new APIObject<AdminGetUserInfoRequest>(AdminGetUserInfoRequest.class);
        //AdminGetUserInfoRequest request = mapper.getObject(json);
        //System.err.println(new JSONObject(request));
    }
    public static boolean hasAdminGetUserInfoRequest(long usn){
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("Command", ApiEnum.AdminGetUserInfoRequest.name());
        param.put("UserID", usn);

        HttpWebURLResponse result = new HttpWebURLResponse();

        HttpWebURLConnection conn = new HttpWebURLConnection("http://qa-angry.nhn629.com:8888/v1.0/adminAPI", param);
        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("AdminID", AdminType.WEB_EVENT.getId());
        headerMap.put("Content-Type", "application/json");
        conn.setHeader(headerMap);
        try {
            result =  conn.sendPostByParamTypeJson();
            //LOG.debug("AdminGetUserInfoRequest api call - "+new JSONObject(param)+" | "+AdminApiUrl+" | "+result.getResponseCode()+", "+result.getResult());
            if(200 == result.getResponseCode()){
                if(result.getResultJson().has("ResultCode")){
                    if(0 == result.getResultJson().getInt("ResultCode")){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //LOG.error("AdminGetUserInfoRequest api call Exception:"+e.toString());
            throw CustomException.ANGRY_ADMIN_API_RESPONSE_EXCEPTION;
        }
        return false;
    }



}

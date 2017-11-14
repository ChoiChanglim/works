package com.nhn.game.constant;

import java.lang.reflect.Method;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author WOW
 * 앵아 admin api json을 모델로 매핑
 * @param <RESPONSEMODEL>
 */
public class APIObject<RESPONSEMODEL> {
	private static final Logger LOG = LoggerFactory.getLogger(APIObject.class);
	Class<RESPONSEMODEL> clazz;

	public APIObject(Class<RESPONSEMODEL> clazz) {
		this.clazz = clazz;
	}

	public RESPONSEMODEL getObject(JSONObject json) {
	    RESPONSEMODEL valueItem = null;
        try {
            valueItem = clazz.newInstance();

            for(int i=0;i<this.clazz.getDeclaredMethods().length;i++){
                Method method = this.clazz.getDeclaredMethods()[i];

                if(method.getName().startsWith("set")){
                    String json_key = method.getName().replace("set", "");
                    Object[] arglist = new Object[]{json.get(json_key)};
                    method.invoke(valueItem, arglist);
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }

		return valueItem;
	}

}

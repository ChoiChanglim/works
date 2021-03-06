package com.nhn.base.constant;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class AngryMappingJacksonJsonView extends MappingJackson2JsonView{


    @Override
    protected Object filterModel(Map<String, Object> model){
        Object result = super.filterModel(model);
        if(!(result instanceof Map)){
            return result;
        }

        Map map = (Map) result;
        if(map.containsKey("info")){
            SessionScopeBean obj = (SessionScopeBean) map.get("info");
        }
        if(map.size() == 1){
            return map.values().toArray()[0];
        }
        return map;
    }
}

package com.of.brm.mail.utils;

import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	public static <T> List<T> getJsonData(Object obj,Class clazz){  
        //TODO 怎么从 T 中获取 class ??   
        //TODO 要不然就可以不要 clazz 参数 了  
        //TODO 现在不伦不类的  
        //TODO 这个泛型用的没多大的作用       
        JSONObject jsonObject = JSONObject.fromObject(obj);  
          
        List<T> list = new ArrayList<T>();  
        for (Iterator iter = jsonObject.keys(); iter.hasNext();) {   
              
            String key = (String) iter.next();  
            JSONArray array = jsonObject.getJSONArray(key);  
  
            for (int i = 0; i < array.size(); i++) {   
                  
                JSONObject object = (JSONObject) array.get(i);  
                  
                T t = (T)JSONObject.toBean(object,clazz);  
                  
                if(t != null) list.add(t);  
                  
            }  
        }  
          
        return list;  
    }

    public static <T> T getJsonDataInfo(Object obj,Class clazz){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        String key = (String) jsonObject.keys().next();
        JSONArray array = jsonObject.getJSONArray(key);

        JSONObject object = (JSONObject) array.get(0);

        T t = (T)JSONObject.toBean(object,clazz);

        return t;
    }

    public static <T> T getJsonDataListInfo(Object obj,Class clazz,Class clazz2){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        String key = (String) jsonObject.keys().next();
        JSONArray array = jsonObject.getJSONArray(key);

        JSONObject object = (JSONObject) array.get(0);
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("list", clazz2);
        T t = (T)JSONObject.toBean(object,clazz,classMap);

        return t;
    }
    
    public static Map getMapFromJsonObjStr(String jsonObjStr) {   
        JSONObject jsonObject = JSONObject.fromObject(jsonObjStr);   
  
       Map map = new HashMap();   
        for (Iterator iter = jsonObject.keys(); iter.hasNext();) {   
            String key = (String) iter.next();   
            map.put(key, jsonObject.get(key));   
        }   
        return map;   
    }   
}

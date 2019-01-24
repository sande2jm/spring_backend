package web.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataService {

    public Map<String,String> getData(){
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }
}

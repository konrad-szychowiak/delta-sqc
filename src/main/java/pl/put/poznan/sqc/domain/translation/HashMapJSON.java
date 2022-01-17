package pl.put.poznan.sqc.domain.translation;

import org.json.simple.JSONObject;

import java.util.HashMap;

public class HashMapJSON <K,V>{
    private final HashMap<K,V> hash;

    public HashMapJSON(HashMap<K,V> hashMap)
    {
        hash = hashMap;
    }


    public String
    stringify ()
    {
        JSONObject jo = new JSONObject(hash);
        return jo.toJSONString();
    }
}

package edu.escuelaing.AREP;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Memorycache {
    private ConcurrentHashMap<String, String> alfaCache = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> ixCache = new ConcurrentHashMap<>();


    public void addalfaCache(String name ,String stock){
        alfaCache.put(name,stock);
    }


    public String getalfaCache(String name) {

        if(alfaCache.containsKey(name)) {

            return alfaCache.get(name);
        }
        return null;
    }
    public void addixCache(String name ,String stock){
        ixCache.put(name,stock);
    }


    public String getixCache(String name) {
        if(ixCache.containsKey(name)) {
            return ixCache.get(name);
        }
        return null;
    }
}
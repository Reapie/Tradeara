package at.ahif18.tradeara.data;

import java.util.HashMap;

public class StockFavManager {
    private static StockFavManager instance;

    private HashMap<String, Integer> map;

    private StockFavManager(){
        map = new HashMap<>();
        map.put("Simon", 1);
        map.put("David", 5);
        map.put("Juritsch", 10);
    }

    public static StockFavManager getInstance(){
        if (instance == null){
            instance = new StockFavManager();
        }
        return instance;
    }

    public HashMap<String, Integer> getMap(){
        return map;
    }
}

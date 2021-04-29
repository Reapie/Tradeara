package at.ahif18.tradeara.data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StockManager {
    private static StockManager instance;

    private Map<String, Integer> map;
    private List<Stock> stocks;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("StockList");

    private StockManager(){
        map = new HashMap<>();
        stocks = new ArrayList<>();

        loadList();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                GenericTypeIndicator<Map<String, Integer>> t = new GenericTypeIndicator<Map<String, Integer>>() {}; //set snapchat getValue to the right generics

                map = snapshot.getValue(t);
                System.out.println(map);
                if (map == null || map.isEmpty()){
                    loadMap(); //init Map
                    myRef.setValue(map); //upload map
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void loadList (){
        stocks = Arrays.asList(
                new Stock("Simon", "SMN", 25.26, 24.24),
                new Stock("David", "DVD", 24.24, -25.24),
                new Stock("Manu", "MXN", 26.24, -20.24),
                new Stock("Martin", "MAN", 30.24, -10.24),
                new Stock("Bene", "BNN", 150.88, 151.2),
                new Stock("Schmidl", "SMD", 3.88, -5.23)
        );
    }

    private void loadMap (){
        map = new HashMap<>();
        for (Stock stock: stocks) {
            map.put(stock.getName(), 1);
        }
    }

    private void increase (String name){
        map.put(name, map.get(name).intValue() + 1);
        myRef.setValue(map);
    }

    public synchronized static StockManager getInstance(){
        if (instance == null){
            instance = new StockManager();
        }
        return instance;
    }

    public Map<String, Integer> getMap(){
        return map;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}

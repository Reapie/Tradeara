package at.ahif18.tradeara.data;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class StockManager {
    private static StockManager instance;

    private Map<String, Integer> map;

    private StockManager(){
        map = new HashMap<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("StockList");

        Gson gson = new Gson();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                Type empMapType = new TypeToken<Map<String, Integer>>() {}.getType();
                map = gson.fromJson(text, empMapType);
                System.out.println(map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static StockManager getInstance(){
        if (instance == null){
            instance = new StockManager();
        }
        return instance;
    }

    public Map<String, Integer> getMap(){
        return map;
    }
}

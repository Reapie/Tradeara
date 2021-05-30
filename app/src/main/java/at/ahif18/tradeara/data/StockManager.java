package at.ahif18.tradeara.data;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import com.google.android.gms.common.api.Response;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.bl.StockGetter;
import at.ahif18.tradeara.stock.StockLauncher;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

public class StockManager {
    private static StockManager instance;

    private Map<String, Integer> map;
    private List<Stock> stocks;

    private StockAdapter homeStockAdapter;
    private boolean homeStockAdapterSet;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("StockList");

    private GenericTypeIndicator<Map<String, Integer>> t = new GenericTypeIndicator<Map<String, Integer>>() {
    }; //set snapchat getValue to the right generics

    private StockManager() {
        map = new HashMap<>();
        stocks = new ArrayList<>();
        homeStockAdapterSet = false;
        //loadMap();
    }


    public void loadList(Context context) {
        /*stocks = Arrays.asList(
                new Stock("Simon", "SMN", 25.26, 24.24),
                new Stock("David", "DVD", 24.24, -25.24),
                new Stock("Manu", "MXN", 26.24, -20.24),
                new Stock("Martin", "MAN", 30.24, -10.24),
                new Stock("Bene", "BNN", 150.88, 151.2),
                new Stock("Schmidl", "SMD", 3.88, -5.23)
        );*/

        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open("stocks.csv");
            String[] symbols = new BufferedReader(new InputStreamReader(is)).lines().skip(1).collect(Collectors.toList()).toArray(new String[0]);
            //ArrayList<yahoofinance.Stock> stockList = StockGetter.getStocks(symbols);
            ArrayList<yahoofinance.Stock> stockList = StockLauncher.getStocks(symbols);

            stockList.forEach(stock -> stocks.add(new Stock(stock.getName(), stock.getSymbol(), stock.getQuote().getPrice().doubleValue(), 23.5)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                map = snapshot.getValue(t);

                if (map == null || map.isEmpty()) {
                    loadMap(); //init Map
                    myRef.setValue(map); //upload map
                }

                List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
                list.sort(Map.Entry.comparingByValue());
                Collections.reverse(list);


                if (!list.isEmpty()) {
                    List<Stock> homeStocks = new ArrayList<>();

                    for (int i = 0; i < 4; i++) {
                        homeStocks.add(getStock(base64Decode(list.get(i).getKey())));
                    }
                    homeStockAdapter.setStocks(homeStocks);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //System.out.println(StockGetter.getStocks("TSLA", "BABA", "XCAP.L"));
    }

    private void loadMap() {
        map = new HashMap<>();
        for (Stock stock : stocks) {
            map.put(base64Encode(stock.getName()), 1);
        }
        myRef.setValue(map);
    }

    public void increase(String name) {
        name = base64Encode(name);
        System.out.println(map);
        map.put(name, map.get(name).intValue() + 1);
        myRef.setValue(map);
    }

    public synchronized static StockManager getInstance() {
        if (instance == null) {
            instance = new StockManager();
        }
        return instance;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public List<Stock> getStocks() {
        return stocks;
    }


    private Stock getStock(String name) {
        for (Stock stock : stocks
        ) {
            if (stock.getName().equals(name)) {
                return stock;
            }
        }
        return null;
    }

    public void setHomeStockAdapter(StockAdapter homeStockAdapter) {
        this.homeStockAdapter = homeStockAdapter;
        homeStockAdapterSet = true;
    }

    public StockAdapter getHomeStockAdapter() {
        return homeStockAdapter;
    }
    private static String base64Encode(String value) {
        try {
            return Base64.getEncoder()
                    .encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
        } catch(UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String base64Decode(String value) {
            String decode = new String(Base64.getDecoder()
                    .decode(value));
            System.out.println(decode);
            return decode;
    }

    public boolean isHomeStockAdapterSet() {
        return homeStockAdapterSet;
    }
}

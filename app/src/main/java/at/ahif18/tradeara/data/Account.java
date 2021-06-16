package at.ahif18.tradeara.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.json.StockDeserializer;
import at.ahif18.tradeara.json.StockSerializer;

public class Account {
    @JsonIgnore
    private MainActivity mainActivity;
    @JsonIgnore
    private static double START_BALANCE = 40000;
    private double balance;
    private String name;
    @JsonDeserialize(keyUsing = StockDeserializer.class)
    @JsonSerialize(keyUsing = StockSerializer.class)
    @JsonProperty("stocks")
    private HashMap<Stock, Integer> stocks;


    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public HashMap<Stock, Integer> getStocks() {
        return stocks;
    }

    public void setStocks(HashMap<Stock, Integer> stocks) {
        this.stocks = stocks;
    }

    public Account(String name, MainActivity mainActivity) {
        this.balance = START_BALANCE;
        this.stocks = new HashMap();
        this.name = name;
        this.mainActivity = mainActivity;
        //addStock(new Stock("Test", "TST", 42.0, 69.0), 1);
    }

    @JsonCreator
    public Account(@JsonProperty("balance")double balance,
                   @JsonProperty("name")String name,
                   @JsonProperty("stocks")HashMap<Stock, Integer> map) {
        this.balance = balance;
        this.name = name;
        this.stocks = map;
    }

    public double getBalance(){
        return balance;
    }

    public void addStock(Stock stock, int amount){
        stocks.put(stock, stocks.get(stock) == null ? amount : stocks.get(stock) + amount);
    }

    public void removeStock(Stock stock, int amount){
        if(stocks.containsKey(stock) && stocks.get(stock) - amount > 0) {
            stocks.replace(stock, stocks.get(stock) - amount);
        }else if(stocks.get(stock) - amount == 0){
            stocks.remove(stock);
        }else{
            System.out.println("Error");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public StockAdapter getStockAdapter() {
        StockAdapter sa = new StockAdapter(mainActivity, false);
        sa.setStocks(new ArrayList<>(stocks.keySet()));
        return sa;
    }
}

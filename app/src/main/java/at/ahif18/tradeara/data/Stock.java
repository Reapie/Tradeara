package at.ahif18.tradeara.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

import at.ahif18.tradeara.bl.StockGetter;

public class Stock implements Parcelable {
    private String name;
    private String symbol;
    private double price;
    private double diff;

    //private double minPrice;
    //private double maxPrice;

    public Stock(String name, String symbol, double price, double diff){
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.diff = diff;
    }

    @JsonCreator
    public Stock(String dataRaw) {
        System.out.println(dataRaw);
        String[] data = dataRaw.split(";");
        System.out.println(Arrays.toString(data));
        this.name = data[0];
        this.symbol = data[1];
        this.price = Float.parseFloat(data[2]);
        this.diff = Float.parseFloat(data[3]);
    }

    @JsonValue
    public String toJson() {
        return String.format("%s;%s;%.2f;%.2f", name, symbol, price, diff);
    }

    protected Stock(Parcel in) {
        name = in.readString();
        symbol = in.readString();
        price = in.readDouble();
        diff = in.readDouble();
    }

    public static final Creator<Stock> CREATOR = new Creator<Stock>() {
        @Override
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };

    public String getFormattedPrice(){
        return String.format("%,.2f", price) + " EUR";
    }

    public String getFormattedPercent () { return String.format("%.2f", diff) + " %";}

    public String getName() {
        return name;
    }

    public String getSymbol(){
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getDiff(){
        return diff;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(symbol);
        dest.writeDouble(price);
        dest.writeDouble(diff);
    }
}

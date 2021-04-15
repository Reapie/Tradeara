package at.ahif18.tradeara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import at.ahif18.tradeara.R;

import static at.ahif18.tradeara.StockGetter.getStocks;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStocks("INTC", "TSLA");

    }
}
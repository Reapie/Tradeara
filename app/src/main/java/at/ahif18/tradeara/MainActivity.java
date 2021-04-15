package at.ahif18.tradeara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.StockAdapter;

import static at.ahif18.tradeara.StockGetter.getStocks;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStock = findViewById(R.id.rvStock);
        rvStock.setHasFixedSize(true);

        rvStock.setLayoutManager(new LinearLayoutManager(this));
        rvStock.setAdapter(new StockAdapter());

        getStocks("INTC", "TSLA");
    }
}
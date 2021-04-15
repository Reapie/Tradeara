package at.ahif18.tradeara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.Account;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.fragments.AccountFragment;
import at.ahif18.tradeara.fragments.BookFragment;
import at.ahif18.tradeara.fragments.DepotFragment;
import at.ahif18.tradeara.fragments.HomeFragment;
import at.ahif18.tradeara.fragments.SearchFragment;
import yahoofinance.Utils;

public class MainActivity extends AppCompatActivity {

    private Fragment searchFragment = new SearchFragment();
    private Fragment depotFragment = new DepotFragment();
    private Fragment homeFragment = new HomeFragment(this);
    private Fragment accountFragment = new AccountFragment();
    private Fragment bookFragment = new BookFragment();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fabT = findViewById(R.id.fabT);
        FloatingActionButton fabCash = findViewById(R.id.fabCash);

        makeCurrentFragment(homeFragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ic_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.ic_search:
                    makeCurrentFragment(searchFragment);
                    return true;
                case R.id.ic_depot:
                    makeCurrentFragment(depotFragment);
                    return true;
                case R.id.ic_home:
                    makeCurrentFragment(homeFragment);
                    return true;
                case R.id.ic_account:
                    makeCurrentFragment(accountFragment);
                    return true;
                case R.id.ic_book:
                    makeCurrentFragment(bookFragment);
                    return true;
            }
            return false;
        });
        // OnClickListener Floating Action Button

        fabT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Simon der Jude soll mir mal Geld geben /paypalme.MxnuR", Toast.LENGTH_SHORT).show();
            }
        });
        fabCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Simon der Jude soll mir noch immer das Geld überweisen der Hund", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void makeCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_wrapper, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        
        makeCurrentFragment(homeFragment);
    }
}
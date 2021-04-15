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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.fragments.AccountFragment;
import at.ahif18.tradeara.fragments.BookFragment;
import at.ahif18.tradeara.fragments.DepotFragment;
import at.ahif18.tradeara.fragments.HomeFragment;
import at.ahif18.tradeara.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment searchFragment = new SearchFragment();
        Fragment depotFragment = new DepotFragment();
        Fragment homeFragment = new HomeFragment();
        Fragment accountFragment = new AccountFragment();
        Fragment bookFragment = new BookFragment();

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
                case R.id.ic_account:
                    makeCurrentFragment(accountFragment);
                    return true;
                case R.id.ic_book:
                    makeCurrentFragment(bookFragment);
                    return true;
            }
            return false;
        });
    }

    private void makeCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_wrapper, fragment)
                .commit();
    }

}
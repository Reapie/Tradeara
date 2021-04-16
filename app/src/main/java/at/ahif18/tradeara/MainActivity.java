package at.ahif18.tradeara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<Fragment> mainFragments;

    private TextView tvCash;
    private FloatingActionButton fabLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragments = Arrays.asList(searchFragment, depotFragment, homeFragment, accountFragment, bookFragment);
        fabLogo = findViewById(R.id.fabLogo);
        tvCash = findViewById(R.id.tvCash);

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

        fabLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Onclick für Logo", Toast.LENGTH_SHORT).show();
            }
        });

        tvCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCurrentFragment(depotFragment);
            }
        });

    }

    public void makeCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_wrapper, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(!mainFragments.contains(currentActiveFragment())){
            FragmentManager fragments = getSupportFragmentManager();
            fragments.popBackStack();
        }
    }

    public Fragment currentActiveFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();

        for (Fragment fragment: fragments) {
            if(fragment != null && fragment.isVisible()){
                return fragment;
            }
        }

        return null;
    }
}
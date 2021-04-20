package at.ahif18.tradeara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import at.ahif18.tradeara.fragments.AccountFragment;
import at.ahif18.tradeara.fragments.BookFragment;
import at.ahif18.tradeara.fragments.BuySellFragment;
import at.ahif18.tradeara.fragments.DepotFragment;
import at.ahif18.tradeara.fragments.HomeFragment;
import at.ahif18.tradeara.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment searchFragment = new SearchFragment(this);
    private Fragment depotFragment = new DepotFragment();
    private Fragment homeFragment = new HomeFragment(this);
    private Fragment accountFragment = new AccountFragment();
    private Fragment bookFragment = new BookFragment();

    private List<Fragment> mainFragments;

    private TextView tvCash;
    private ImageView ivLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragments = Arrays.asList(searchFragment, depotFragment, homeFragment, accountFragment, bookFragment);
        ivLogo = findViewById(R.id.ivLogo);
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

        ivLogo.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Onclick für Logo", Toast.LENGTH_SHORT).show());

        tvCash.setOnClickListener(v -> {
            makeCurrentFragment(depotFragment);
            bottomNavigationView.setSelectedItemId(R.id.ic_depot);
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

    private int stockClickCount = 0;
    public void buySellBottomDialog(String stockName, String stockSymbol){
        if(stockClickCount == 0){
            BuySellFragment buySellFragment = BuySellFragment.newInstance(stockName, stockSymbol);
            buySellFragment.show(getSupportFragmentManager(), "buy_sell_fragment");
            buySellFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);

            stockClickCount++;
        }else{
            stockClickCount = 0;
        }
    }

}
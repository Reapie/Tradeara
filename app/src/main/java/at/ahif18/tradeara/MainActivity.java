package at.ahif18.tradeara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import at.ahif18.tradeara.bl.PrefManager;
import at.ahif18.tradeara.data.Account;
import at.ahif18.tradeara.data.Stock;
import at.ahif18.tradeara.data.StockManager;
import at.ahif18.tradeara.fragments.AccountFragment;
import at.ahif18.tradeara.fragments.BookFragment;
import at.ahif18.tradeara.fragments.BuySellFragment;
import at.ahif18.tradeara.fragments.DepotFragment;
import at.ahif18.tradeara.fragments.HomeFragment;
import at.ahif18.tradeara.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Fragment searchFragment = new SearchFragment(this);
    private Fragment depotFragment = new DepotFragment();
    private Fragment homeFragment = new HomeFragment(this);
    private Fragment accountFragment = new AccountFragment(this);
    private Fragment bookFragment = new BookFragment();

    private List<Fragment> mainFragments;

    private TextView tvCash;
    private ImageView ivLogo;

    private PrefManager prefManager;
    private Account account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StockManager.getInstance().loadList(this);

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

        ivLogo.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Onclick für Logo", Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Reapie/Tradeara"));
            startActivity(browserIntent);
        });

        tvCash.setOnClickListener(v -> {
            makeCurrentFragment(depotFragment);
            bottomNavigationView.setSelectedItemId(R.id.ic_depot);
        });

        prefManager = new PrefManager(this, this);
        prefManager.getOrCreate();
    }

    public void setAccount(Account acc) {
        this.account = acc;
        refreshBalance();   // ALWAYS CALL WHEN BALANCE CHANGES
    }

    public Account getAccount() {
        return account;
    }

    public void refreshBalance() {
        // set balance in header textview
        tvCash.setText(String.format(getString(R.string.moneyFormat), account.getBalance()));
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
        if(currentActiveFragment() != homeFragment){
            FragmentManager fragments = getSupportFragmentManager();
            fragments.popBackStack();
            makeCurrentFragment(homeFragment);
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setSelectedItemId(R.id.ic_home);
        } else {
            finish();
            System.exit(0);
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

    public void buySellBottomDialog (Stock stock){
        BuySellFragment buySellFragment = BuySellFragment.newInstance(stock);
        buySellFragment.show(getSupportFragmentManager(), "buy_sell_fragment");
        buySellFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme);
    }

}
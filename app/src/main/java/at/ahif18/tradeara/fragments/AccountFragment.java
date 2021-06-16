package at.ahif18.tradeara.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.SQLOutput;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.LoadStocksTask;
import at.ahif18.tradeara.bl.StockAdapter;
import at.ahif18.tradeara.data.Account;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private static final String TAG = "AccountFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView rvUserStock;
    private RelativeLayout User;
    private static MainActivity mainActivity;
    private LoadStocksTask loadStocksTask;
    private String name = "";

    public AccountFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment(mainActivity);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        loadStocksTask = new LoadStocksTask(mainActivity, false);
        loadStocksTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        rvUserStock = view.findViewById(R.id.rvUserStock);

        rvUserStock.setHasFixedSize(true);
        TextView tvAccountName = view.findViewById(R.id.tvName);
        tvAccountName.setText(mainActivity.getAccount().getName());

        rvUserStock.setLayoutManager(new LinearLayoutManager(getContext()));
        Account account = mainActivity.getAccount();
        rvUserStock.setAdapter(account.getStockAdapter());

        User = view.findViewById(R.id.RelativeID);

        User.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              Account acc = new Account((String) tvAccountName.getText(), mainActivity);

                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity.getBaseContext());
                builder.setTitle("Title");

// Set up the input
                final EditText input = new EditText(mainActivity.getBaseContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = input.getText().toString();
                        acc.setName(name);
                        tvAccountName.setText(name);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
        return view;

    }

    @Override
    public String toString() {
        return TAG;
    }
}
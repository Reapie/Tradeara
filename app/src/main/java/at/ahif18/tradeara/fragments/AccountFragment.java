package at.ahif18.tradeara.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.LoadStocksTask;
import at.ahif18.tradeara.bl.StockAdapter;

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
    private static MainActivity mainActivity;
    private LoadStocksTask loadStocksTask;
    private TextView tvtraining;
    private RelativeLayout layout;

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
        tvtraining = view.findViewById(R.id.tvTraining);

        rvUserStock = view.findViewById(R.id.rvUserStock);
        layout = view.findViewById(R.id.RelativeTraining);

        rvUserStock.setHasFixedSize(true);
        TextView tvAccountName = view.findViewById(R.id.tvName);
        tvAccountName.setText(mainActivity.getAccount().getName());

        rvUserStock.setLayoutManager(new LinearLayoutManager(getContext()));
        rvUserStock.setAdapter(loadStocksTask.getStockAdapter());

        layout.setOnClickListener(v -> mainActivity.makeCurrentFragment(new TrainingsModeFragement()));

        return view;

    }

    @Override
    public String toString() {
        return TAG;
    }
}
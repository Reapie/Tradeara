package at.ahif18.tradeara.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import at.ahif18.tradeara.MainActivity;
import at.ahif18.tradeara.R;
import at.ahif18.tradeara.bl.HelpAdapter;

public class TrainingsModeFragement extends Fragment {

        private static final String TAG = "TrainingsModeFragement";
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";


        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;
        private static MainActivity mainActivity;
        private RecyclerView rvHelp;


        public TrainingsModeFragement() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TrainingsModeFragement.
         */
        // TODO: Rename and change types and number of parameters
        public static at.ahif18.tradeara.fragments.TrainingsModeFragement newInstance(String param1, String param2) {
            at.ahif18.tradeara.fragments.TrainingsModeFragement fragment = new at.ahif18.tradeara.fragments.TrainingsModeFragement();
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
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragement_trainingmode, container, false);
            return view;
        }

        @Override
        public String toString() {
            return TAG;
        }
    }


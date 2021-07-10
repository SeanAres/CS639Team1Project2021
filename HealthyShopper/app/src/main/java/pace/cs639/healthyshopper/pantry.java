package pace.cs639.healthyshopper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pantry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pantry extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextInputEditText nutrientsEditText;
    private TextView mNutrientsText;
    private MaterialButton calculateButton;
    private String s1[], s2[];
    private ArrayList<Integer> images= new ArrayList<Integer>();
    private RecyclerView recyclerView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pantry() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favorites.
     */
    // TODO: Rename and change types and number of parameters
    public static pantry newInstance(String param1, String param2) {
        pantry fragment = new pantry();
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
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        mNutrientsText = (TextView)view.findViewById(R.id.nutrientText);
        nutrientsEditText = (TextInputEditText)view.findViewById(R.id.nutrients);
        final String TAG = "Pantry";
        calculateButton = view.findViewById(R.id.calculate_btn);
        images.add(R.drawable.ic_baseline_fastfood_24);
        images.add(R.drawable.ic_baseline_emoji_food_beverage_24);
        s1 =getResources().getStringArray(R.array.food_array);
        s2 = getResources().getStringArray(R.array.food_description);
        List<String> s1List = Arrays.asList(s1);
        List<String> s2List = Arrays.asList(s2);
        recyclerView = view.findViewById(R.id.recyclerView);
        // Set an error if the password is less than 8 characters.
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Button Clicked");
                String queryString = nutrientsEditText.getText().toString();
                try{
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    if (imm != null ) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connMgr != null) {
                    networkInfo = connMgr.getActiveNetworkInfo();
                }


                if (networkInfo != null && networkInfo.isConnected()
                        && queryString.length() != 0) {
                    new FetchNutrients(mNutrientsText).execute(queryString);
                    mNutrientsText.setText(R.string.loading);
                    Log.i(TAG, queryString);
                }
                else {
                    if (queryString.length() == 0) {

                        mNutrientsText.setText(R.string.no_search_term);
                    } else {

                        mNutrientsText.setText(R.string.no_network);
                    }
                }




            }
        });


        return view;
    }
}
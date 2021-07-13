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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
    private MaterialButton addButton;
    private TextInputEditText totalEditText;
    private TextInputEditText maxEditText;
    private RecyclerView pantryRecyclerView;
    private static ArrayList<Pantry_Item> pantryList;
    private TextInputEditText maxInput;
    private TextInputEditText totalInput;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private String email;



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
        pantryList = new ArrayList<>();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private void setAdapter(PantryAdapter adapter) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        pantryRecyclerView.setLayoutManager(layoutManager);
        pantryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        pantryRecyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pantry, container, false);
        mNutrientsText = (TextView)view.findViewById(R.id.nutrientText);
        nutrientsEditText = (TextInputEditText)view.findViewById(R.id.nutrients);

       

        // Set an error if the password is less than 8 characters.

        totalEditText = (TextInputEditText)view.findViewById(R.id.totalEditText);
        maxEditText = (TextInputEditText)view.findViewById(R.id.maxEditText);
        final String TAG = "Pantry";
        calculateButton = view.findViewById(R.id.calculate_btn);
        addButton = view.findViewById(R.id.add_button);

        pantryRecyclerView =  (RecyclerView)view.findViewById(R.id.pantryRecyclerView);
        PantryAdapter adapter = new PantryAdapter(pantryList);
        setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        email = email.replace(".","");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(email);

        Log.i("MAINACTIVITY", myRef.toString());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pantry_Item item;
                int counter = 0;

                pantryList.clear();
                adapter.notifyDataSetChanged();
                //pantryList = new ArrayList<>();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    item= (Pantry_Item) ds.getValue(Pantry_Item.class);
                    Log.i("Pantry", counter + " - Name: " + item.getName()+ "Nutrition: " + item.getNutrition()+ "Max: " +
                            item.getMax() + "Total: " + item.getTotal());
                    String food_name = item.getName();
                    String max_item = item.getMax();
                    String total_item =item.getTotal();
                    String food_ntr = item.getNutrition();
                    pantryList.add(new Pantry_Item(food_name, food_ntr,max_item, total_item));
                    adapter.notifyItemInserted(pantryList.size() - 1);

                    counter++;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MAINACTIVITY", "Failed to read value.", error.toException());
            }
        });
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
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String food_details = mNutrientsText.getText().toString();
                String [] detail_list = food_details.split("\n",2);
                String foodname = String.valueOf(detail_list[0]);

                try {
                    String nutrition = String.valueOf(detail_list[1]);
                    String max = maxEditText.getText().toString().trim();
                    String total =totalEditText.getText().toString().trim();
                    Log.i("Food name", foodname);
                    Log.i("Nutrition",nutrition);
                    myRef = database.getReference(email);
                    Pantry_Item pant = new Pantry_Item(foodname, nutrition,max, total);
                    int found = 0;
                    for (int i = 0; i < pantryList.size(); i++) {
                        if (pantryList.get(i).getName().equals(foodname)) {
                            found = 1;
                            //break;
                        }
                    }

                    if(found==0) {
                        myRef.child(foodname).setValue(pant);
                    }
                    else {
                        Log.i("pantry", "found==1");
                        Toast.makeText(view.getContext(), "Food already in list!", Toast.LENGTH_SHORT).show();
                        mNutrientsText.setText("Food already in list!");

                    }
                    }


                    //pantryList.add(new Pantry_Item(foodname, nutrition,max, total));
                    //adapter.notifyItemInserted(pantryList.size() - 1);

                catch(Exception e){
                    Log.i(TAG, "failed to load card");
                    mNutrientsText.setText("Must calculate first!");
                }
            }
        });


        return view;
    }
}
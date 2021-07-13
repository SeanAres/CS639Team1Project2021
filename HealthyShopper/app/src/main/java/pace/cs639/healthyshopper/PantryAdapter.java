package pace.cs639.healthyshopper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.MyViewHolder> {
    private ArrayList<Pantry_Item> pantryList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private String email;
    public PantryAdapter(ArrayList<Pantry_Item> pantryList){
        this.pantryList = pantryList;

    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PantryAdapter.MyViewHolder holder, int position) {
        String name = pantryList.get(position).getName();
        holder.nameView.setText(name);
        String total = String.valueOf(pantryList.get(position).getTotal());
        String max = String.valueOf(pantryList.get(position).getMax());
        holder.outOfView.setText(total+"/"+ max);
        String nutrition = pantryList.get(position).getNutrition();
        holder.nutritionView.setText(nutrition);
        holder.minusTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                email = email.replace(".","");
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference().child(email);
                int new_total = Integer.parseInt(pantryList.get(holder.getPosition()).getTotal())-1;
                String name = pantryList.get(holder.getPosition()).getName();
                String str_total = String.valueOf(new_total);
                if(new_total>=0) {
                    myRef.child(name).child("total").setValue(str_total);
                }
                //foodList.remove(holder.getAdapterPosition());
                //notifyItemRemoved(holder.getAdapterPosition());
                //notifyItemRangeChanged(holder.getAdapterPosition(), foodList.size());
            }
        });
        holder.minusMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                mAuth = FirebaseAuth.getInstance();
                email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                email = email.replace(".","");
                myRef = database.getReference().child(email);
                int new_max = Integer.parseInt(pantryList.get(holder.getPosition()).getMax())-1;
                String name = pantryList.get(holder.getPosition()).getName();
                String str_max = String.valueOf(new_max);
                if(new_max>=1) {
                    myRef.child(name).child("max").setValue(str_max);
                }
                if (new_max == 0){
                    myRef.child(name).removeValue();
                }
                //foodList.remove(holder.getAdapterPosition());
                //notifyItemRemoved(holder.getAdapterPosition());
                //notifyItemRangeChanged(holder.getAdapterPosition(), foodList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return pantryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView, outOfView, nutritionView;
        private Button minusTotal, minusMax;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.food_label_tv);
            outOfView = itemView.findViewById(R.id.pantry_count_tv);
            nutritionView= itemView.findViewById(R.id.food_tv);
            minusMax= itemView.findViewById(R.id.minus_max);
            minusTotal = itemView.findViewById(R.id.minus_total);


        }
    }
}

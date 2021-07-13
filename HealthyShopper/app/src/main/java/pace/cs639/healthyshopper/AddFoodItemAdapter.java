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
import java.util.List;
import java.util.Objects;

public class AddFoodItemAdapter extends RecyclerView.Adapter<AddFoodItemAdapter.ShoppingItemViewHolder>{

    private ArrayList<SL_FoodItem> foodList;
    //Create the View Holder
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private String email;

    public AddFoodItemAdapter(ArrayList<SL_FoodItem> foodList){
        this.foodList = foodList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ShoppingItemViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_item, parent, false);
        return new ShoppingItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AddFoodItemAdapter.ShoppingItemViewHolder holder, int position) {
        String name = foodList.get(position).getName();
        holder.nameView.setText(name);
        String qty = String.valueOf(foodList.get(position).getQty());
        holder.qtyView.setText("x"+qty);
        String cal = foodList.get(position).getCal();
        holder.calView.setText(cal);
        //OnClickListener implemented here in order to delete appropriate card when its delete button is pressed.
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               foodList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), foodList.size());
            }
        });
        holder.add_to_pantryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
                email = email.replace(".","");
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference().child(email);
                int qty = foodList.get(holder.getPosition()).getQty();
               String name = foodList.get(holder.getPosition()).getName();
               myRef.child(name).child("total").setValue(String.valueOf(qty));




            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ShoppingItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView, qtyView, calView;
        private Button delBtn;
        private Button add_to_pantryBtn;

        public ShoppingItemViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.SL_FoodNameView);
            qtyView = itemView.findViewById(R.id.SL_QtyView);
            calView = itemView.findViewById(R.id.SL_CalView);
            delBtn = itemView.findViewById(R.id.SL_DeleteBtn);
            calView = itemView.findViewById(R.id.SL_CalView);
           add_to_pantryBtn = itemView.findViewById(R.id.SL_AddtoPantryBtn);
        }
    }
}

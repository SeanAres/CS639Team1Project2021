package pace.cs639.healthyshopper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddFoodItemAdapter extends RecyclerView.Adapter<AddFoodItemAdapter.ShoppingItemViewHolder>{

    private ArrayList<SL_FoodItem> foodList;
    //Create the View Holder

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
        //OnClickListener implemented here in order to delete appropriate card when its delete button is pressed.
        holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), foodList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ShoppingItemViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView, qtyView;
        private Button delBtn;

        public ShoppingItemViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.SL_FoodNameView);
            qtyView = itemView.findViewById(R.id.SL_QtyView);
            delBtn = itemView.findViewById(R.id.SL_DeleteBtn);
        }
    }
}

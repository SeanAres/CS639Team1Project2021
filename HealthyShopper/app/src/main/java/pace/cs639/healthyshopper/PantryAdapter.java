package pace.cs639.healthyshopper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.MyViewHolder> {
    private ArrayList<Pantry_Item> pantryList;
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

    }

    @Override
    public int getItemCount() {
        return pantryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameView, outOfView, nutritionView;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.food_label_tv);
            outOfView = itemView.findViewById(R.id.pantry_count_tv);
            nutritionView= itemView.findViewById(R.id.food_tv);

        }
    }
}

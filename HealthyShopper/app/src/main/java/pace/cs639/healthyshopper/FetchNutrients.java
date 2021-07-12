package pace.cs639.healthyshopper;

import android.os.AsyncTask;
import android.widget.TextView;





import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;



public class FetchNutrients extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mNutrientTextView;



    FetchNutrients(TextView nutrientText) {
        this.mNutrientTextView = new WeakReference<>(nutrientText);

    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            String nutrients = null;
            JSONObject jsonObject = new JSONObject(s);
            JSONArray foodArray = jsonObject.getJSONArray("foods");
            JSONObject food = foodArray.getJSONObject(0);
            nutrients = food.getString(  "lowercaseDescription") + "\n";
            JSONArray nutrientsArray = food.getJSONArray("foodNutrients");

 


            JSONObject nutrient = nutrientsArray.getJSONObject(0);
                try {
                    nutrients = nutrients + "Protein" + ": " + nutrient.getString("value") + ", ";
                    nutrient = nutrientsArray.getJSONObject(1);
                    nutrients = nutrients + "Fat" + ": " + nutrient.getString("value") + ", ";
                    nutrient = nutrientsArray.getJSONObject(2) ;
                    nutrients = nutrients + "Carbs" + ": " + nutrient.getString("value") + ", ";
                    nutrient = nutrientsArray.getJSONObject(3);
                    nutrients = nutrients + "Calories" + ": " + nutrient.getString("value") + " ";
                }catch (Exception e){
                    e.printStackTrace();
                }



            if (nutrients != null) {
                mNutrientTextView.get().setText(nutrients);



            }
            else {
                mNutrientTextView.get().setText("No Results, check spelling!");
            }

        } catch (JSONException e) {
            e.printStackTrace();
            mNutrientTextView.get().setText("No Results, check spelling!");
        }

    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getNutrientsInfo(strings[0]);
    }
}
